package com.backend.template.repository;

import com.backend.template.entity.QStore;
import com.backend.template.entity.QUser;
import com.backend.template.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {
    private final JPAQueryFactory qf;

    public List<Store> findNearestStores(double userLat, double userLng) {
        QStore store = QStore.store;
        QUser user = QUser.user;

        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "(6371 * acos(cos(radians({0})) * cos(radians({1})) * cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1}))))",
                userLat, store.latitude, store.longitude, userLng
        );

        return qf.selectFrom(store)
                .leftJoin(store.user, user).fetchJoin()
                .orderBy(distance.asc())
                .limit(15)
                .fetch();
    }

    public List<Store> findNearestStoresByCategory(double userLat, double userLng, String category) {
        QStore store = QStore.store;
        QUser user = QUser.user;

        NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
                "(6371 * acos(cos(radians({0})) * cos(radians({1})) * cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1}))))",
                userLat, store.latitude, store.longitude, userLng
        );

        return qf.selectFrom(store)
                .where(store.category.eq(category))
                .leftJoin(store.user, user).fetchJoin()
                .orderBy(distance.asc())
                .limit(15)
                .fetch();
    }
}
