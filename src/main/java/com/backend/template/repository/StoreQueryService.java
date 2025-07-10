package com.backend.template.repository;

import com.backend.template.dto.StoreResponseDto;
import com.backend.template.entity.QStore;
import com.backend.template.entity.QUser;
import com.backend.template.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {
    private final JPAQueryFactory qf;

    public Store findBy(Point point) {
        QStore store = QStore.store;
        QUser user = QUser.user;

    }
}

