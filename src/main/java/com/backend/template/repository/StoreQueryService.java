package com.backend.template.repository;

import com.backend.template.dto.StoreResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {
    private final JPAQueryFactory qf;
}

