package com.backend.template.service;

import com.backend.template.dto.CreateGuestBookRequestDto;
import com.backend.template.entity.GuestBook;
import com.backend.template.entity.Question;
import com.backend.template.entity.Store;
import com.backend.template.entity.User;
import com.backend.template.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestBookService {
    private final GuestBookQueryService guestBookQueryService;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final QuestionRepository questionRepository;
    private final GuestBookRepository guestBookRepository;

    @Transactional
    public void create(Long userId, CreateGuestBookRequestDto request) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Question question = questionRepository.findById(request.getQuestionId()).orElseThrow(() ->
                new IllegalArgumentException("질문을 찾을 수 없습니다."));
        Store store = storeRepository.findById(request.getQuestionId()).orElseThrow(() ->
                new IllegalArgumentException("상점을 찾을 수 없습니다."));

        GuestBook newGuestBook = GuestBook.builder()
                .content(request.getContent())
                .answer(request.getAnswer())
                .question(question)
                .user(user)
                .store(store)
                .build();
        guestBookRepository.save(newGuestBook);
    }

    @Transactional(readOnly = true)
    public List<GuestBook> getGuestBooksByStoreId(Integer storeId) {
        return guestBookQueryService.findGuestBooksByStoreId(storeId);
    }

    @Transactional(readOnly = true)
    public List<GuestBook> getLatestGuestBooks() {
        List<GuestBook> latestGuestBooks = guestBookQueryService.findLatestGuestBooks();
        return latestGuestBooks;
    }

    @Transactional(readOnly = true)
    public List<GuestBook> getGuestBooksByUserId(Integer userId) {
        return guestBookQueryService.findGuestBooksByUserId(userId);
    }
}
