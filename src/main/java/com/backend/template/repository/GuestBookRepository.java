package com.backend.template.repository;

import com.backend.template.entity.GuestBook;
import com.backend.template.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
}
