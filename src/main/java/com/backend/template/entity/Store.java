package com.backend.template.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String businessNum;

    @Column(nullable = false, columnDefinition = "POINT")
    private Point location;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(length = 255)
    private String operationTime;
}