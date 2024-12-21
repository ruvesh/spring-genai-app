package io.github.ruvesh.spring_genai_app.service.impl;

import io.github.ruvesh.spring_genai_app.data.entity.PromptHistory;
import io.github.ruvesh.spring_genai_app.data.repository.PromptHistoryRepository;
import io.github.ruvesh.spring_genai_app.service.PromptHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PromptHistoryServiceImpl implements PromptHistoryService {

    private final PromptHistoryRepository repository;

    public PromptHistoryServiceImpl(PromptHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public PromptHistory savePromptHistory(String requestId, String prompt, String response) {
        PromptHistory promptHistory = PromptHistory.init(requestId, prompt, response, LocalDateTime.now());
        log.info("Saving prompt history to database: {}", promptHistory);
        return repository.save(promptHistory);
    }
}
