package com.backend.template.service;

import com.backend.template.base.authorization.TokenService;
import com.backend.template.dto.StoreResponseDto;
import com.backend.template.entity.User;
import com.backend.template.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final GeometryFactory geometryFactory = new GeometryFactory(new org.locationtech.jts.geom.PrecisionModel(), 4326);

    public List<StoreResponseDto> findByPoint(double lat, double lon) {
        Point userLocation = geometryFactory.createPoint(new Coordinate(lat, lon));
        return new ArrayList<>();
    }

    public List<StoreResponseDto> findByPointAndCategory(double lat, double lon, String category) {
        Point userLocation = geometryFactory.createPoint(new Coordinate(lat, lon));
        return new ArrayList<>();
    }
}
