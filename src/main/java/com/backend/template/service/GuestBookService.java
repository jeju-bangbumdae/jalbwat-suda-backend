package com.backend.template.service;

import com.backend.template.entity.GuestBook;
import com.backend.template.repository.GuestBookQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestBookService {
    private final GuestBookQueryService guestBookQueryService;

    public List<GuestBook> getGuestBooksByStoreId(Integer storeId) {
        return guestBookQueryService.findGuestBooksByStoreId(storeId);
    }

    public List<GuestBook> getLatestGuestBooks() {
        List<GuestBook> latestGuestBooks = guestBookQueryService.findLatestGuestBooks();
        System.out.println("1111113123213");
        return latestGuestBooks;
    }

    public List<GuestBook> getGuestBooksByUserId(Integer userId) {
        return guestBookQueryService.findGuestBooksByUserId(userId);
    }
}
