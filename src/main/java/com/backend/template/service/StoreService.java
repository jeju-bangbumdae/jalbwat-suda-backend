package com.backend.template.service;

import com.backend.template.dto.StoreResponseDto;
import com.backend.template.dto.UserResponseDto;
import com.backend.template.entity.Store;
import com.backend.template.entity.User;
import com.backend.template.repository.GuestBookQueryService;
import com.backend.template.repository.StoreQueryService;
import com.backend.template.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreQueryService storeQueryService;
    private final StoreRepository storeRepository;
    private final GuestBookQueryService guestBookQueryService;

    public StoreResponseDto findById(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상점이 존재하지 않습니다."));
        Long guestBookCount = guestBookQueryService.countGuestBooksByStoreId(store.getId());
        User user = store.getUser();
        return StoreResponseDto.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .category(store.getCategory())
                .user(new UserResponseDto(user.getId(), user.getEmail(), user.getName(), user.getActor()))
                .lat(String.valueOf(store.getLatitude()))
                .lon(String.valueOf(store.getLongitude()))
                .operationTime(store.getOperationTime())
                .phone(store.getPhone())
                .guestBookCount(guestBookCount.intValue())
                .build();
    }

    public List<StoreResponseDto> findByPoint(double lat, double lon) {
        List<Store> nearestStores = storeQueryService.findNearestStores(lat, lon);

        List<StoreResponseDto> stores = new ArrayList<>();
        for (Store nearestStore : nearestStores) {
            Long guestBookCount = guestBookQueryService.countGuestBooksByStoreId(nearestStore.getId());
            User user = nearestStore.getUser();
            stores.add(StoreResponseDto.builder()
                    .id(nearestStore.getId())
                    .name(nearestStore.getName())
                    .address(nearestStore.getAddress())
                    .category(nearestStore.getCategory())
                    .user(new UserResponseDto(user.getId(), user.getEmail(), user.getName(), user.getActor()))
                    .lat(String.valueOf(nearestStore.getLatitude()))
                    .lon(String.valueOf(nearestStore.getLongitude()))
                    .operationTime(nearestStore.getOperationTime())
                    .phone(nearestStore.getPhone())
                    .guestBookCount(guestBookCount.intValue())
                    .build());
        }

        return stores;
    }

    public List<StoreResponseDto> findByPointAndCategory(double lat, double lon, String category) {
        List<Store> nearestStores = storeQueryService.findNearestStoresByCategory(lat, lon, category);

        List<StoreResponseDto> stores = new ArrayList<>();
        for (Store nearestStore : nearestStores) {
            Long guestBookCount = guestBookQueryService.countGuestBooksByStoreId(nearestStore.getId());
            User user = nearestStore.getUser();
            stores.add(StoreResponseDto.builder()
                    .id(nearestStore.getId())
                    .name(nearestStore.getName())
                    .address(nearestStore.getAddress())
                    .category(nearestStore.getCategory())
                    .user(new UserResponseDto(user.getId(), user.getEmail(), user.getName(), user.getActor()))
                    .lat(String.valueOf(nearestStore.getLatitude()))
                    .lon(String.valueOf(nearestStore.getLongitude()))
                    .operationTime(nearestStore.getOperationTime())
                    .phone(nearestStore.getPhone())
                    .guestBookCount(guestBookCount.intValue())
                    .build());
        }

        return stores;
    }
}
