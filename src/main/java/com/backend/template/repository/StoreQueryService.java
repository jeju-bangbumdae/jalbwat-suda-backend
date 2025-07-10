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

        NumberExpression<Double> distance = calculateDistance(userLat, userLng, store.latitude, store.longitude);

        return qf.selectFrom(store)
                .leftJoin(store.user, user).fetchJoin()
                .orderBy(distance.asc())
                .limit(15)
                .fetch();
    }

    public List<Store> findNearestStoresByCategory(double userLat, double userLng, String category) {
        QStore store = QStore.store;
        QUser user = QUser.user;

        // 하버사인 공식을 사용한 거리 계산
        NumberExpression<Double> distance = calculateDistance(userLat, userLng, store.latitude, store.longitude);

        return qf.selectFrom(store)
                .where(store.category.eq(category))
                .leftJoin(store.user, user).fetchJoin()
                .orderBy(distance.asc())
                .limit(15)
                .fetch();
    }

    /**
     * 하버사인 공식을 사용하여 두 지점 간의 거리를 계산합니다.
     * @param userLat 사용자 위도
     * @param userLng 사용자 경도
     * @param storeLat 매장 위도 (Entity 필드)
     * @param storeLng 매장 경도 (Entity 필드)
     * @return 거리 (km)
     */
    private NumberExpression<Double> calculateDistance(double userLat, double userLng,
                                                       NumberExpression<Double> storeLat,
                                                       NumberExpression<Double> storeLng) {

        double earthRadius = 6371.0;
        NumberExpression<Double> latDiff = storeLat.subtract(userLat)
                .multiply(Math.PI / 180.0);

        NumberExpression<Double> lngDiff = storeLng.subtract(userLng)
                .multiply(Math.PI / 180.0);
        double userLatRad = userLat * Math.PI / 180.0;

        NumberExpression<Double> storeLatRad = storeLat.multiply(Math.PI / 180.0);
        NumberExpression<Double> a =
                Expressions.numberTemplate(Double.class,
                        "POWER(SIN({0} / 2), 2) + COS({1}) * COS({2}) * POWER(SIN({3} / 2), 2)",
                        latDiff, userLatRad, storeLatRad, lngDiff);

        NumberExpression<Double> c =
                Expressions.numberTemplate(Double.class,
                        "2 * ATAN2(SQRT({0}), SQRT(1 - {0}))", a);

        return Expressions.numberTemplate(Double.class, "{0} * {1}", earthRadius, c);
    }
}