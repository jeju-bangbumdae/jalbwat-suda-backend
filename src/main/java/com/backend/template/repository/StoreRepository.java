package com.backend.template.repository;

import com.backend.template.entity.Question;
import com.backend.template.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
