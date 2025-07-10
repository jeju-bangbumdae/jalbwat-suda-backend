package com.backend.template.service;

import com.backend.template.entity.Question;
import com.backend.template.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public Question getRandomOneQuestion() {
        List<Question> allQuestions = questionRepository.findAll();
        if (allQuestions.isEmpty()) {
            throw new IllegalArgumentException("질문목록이 비어있습니다.");
        }
        int randomIndex = random.nextInt(allQuestions.size());
        return allQuestions.get(randomIndex);
    }
}
