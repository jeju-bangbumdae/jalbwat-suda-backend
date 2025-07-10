package com.backend.template.repository;

import com.backend.template.entity.GuestBook;
import com.backend.template.entity.QGuestBook;
import com.backend.template.entity.QStore;
import com.backend.template.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestBookQueryService {
    private final JPAQueryFactory qf;

    public Long countGuestBooksByStoreId(Integer storeId) {
        QGuestBook guestBook = QGuestBook.guestBook;
        return qf.select(guestBook.count())
                .from(guestBook)
                .where(guestBook.store.id.eq(storeId))
                .fetchOne();
    }

    public List<GuestBook> findGuestBooksByStoreId(Integer storeId) {
        QGuestBook guestBook = QGuestBook.guestBook;
        QStore store = QStore.store;
        QUser user = QUser.user;

        return qf.selectFrom(guestBook)
                .leftJoin(guestBook.store, store).fetchJoin() // Eagerly fetch store
                .leftJoin(guestBook.user, user).fetchJoin()   // Eagerly fetch user
                .where(guestBook.store.id.eq(storeId))
                .orderBy(guestBook.createdAt.desc()) // Assuming createdAt from BaseEntity
                .fetch();
    }

    public List<GuestBook> findLatestGuestBooks() {
        QGuestBook guestBook = QGuestBook.guestBook;
        QStore store = QStore.store;
        QUser user = QUser.user;

        return qf.selectFrom(guestBook)
                .leftJoin(guestBook.store, store).fetchJoin()
                .leftJoin(guestBook.user, user).fetchJoin()
                .orderBy(guestBook.createdAt.desc())
                .fetch();
    }

    public List<GuestBook> findGuestBooksByUserId(Integer userId) {
        QGuestBook guestBook = QGuestBook.guestBook;
        QStore store = QStore.store;
        QUser user = QUser.user;

        return qf.selectFrom(guestBook)
                .leftJoin(guestBook.store, store).fetchJoin()
                .leftJoin(guestBook.user, user).fetchJoin()
                .where(guestBook.user.id.eq(userId))
                .orderBy(guestBook.createdAt.desc())
                .fetch();
    }
}

