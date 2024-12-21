package io.github.ruvesh.spring_genai_app.service;

import io.github.ruvesh.spring_genai_app.data.entity.PromptHistory;

public interface PromptHistoryService {
    PromptHistory savePromptHistory(String requestId, String prompt, String response);
}
