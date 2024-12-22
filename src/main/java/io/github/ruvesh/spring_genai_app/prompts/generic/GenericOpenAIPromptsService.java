package io.github.ruvesh.spring_genai_app.prompts.generic;

import io.github.ruvesh.spring_genai_app.data.dto.Response;
import io.github.ruvesh.spring_genai_app.service.PromptHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Slf4j
@Service("genericOpenAIPromptsService")
class GenericOpenAIPromptsService implements GenericPromptsService {

    private final ChatClient chatClient;

    private final PromptHistoryService promptHistoryService;

    GenericOpenAIPromptsService(ChatClient.Builder chatClientBuilder, PromptHistoryService promptHistoryService) {
        this.chatClient = chatClientBuilder.build();
        this.promptHistoryService = promptHistoryService;
    }

    @Override
    public Response greetUserWithAI() {
        long startTime = System.currentTimeMillis();
        log.info("greetUserWithAI request started");

        String requestId = UUID.randomUUID().toString();

        String prompt = REQUEST_CONTEXT.concat("The time is ").concat(LocalTime.now().toString()).concat(". Based on time of the day, greet and welcome me to the platform, and mention some random trivia about AI.");
        log.info("Prompt sent to OpenAI completions api: {}", prompt);

        String responseText = chatClient.prompt()
                .user(prompt)
                .call()
                .content()
                ;

        promptHistoryService.savePromptHistory(requestId, prompt, responseText);

        log.info("Time taken to complete greetUserWithAI request {}ms",  (System.currentTimeMillis() - startTime));
        return new Response(requestId, responseText);
    }

    @Override
    public Response askAnythingToAI(GenericPromptsRequest request) {
        long startTime = System.currentTimeMillis();
        log.info("askAnythingToAI request started");

        String requestId = request.getRequestId().toString();

        String finalPrompt = REQUEST_CONTEXT.concat(request.getPrompt());
        log.info("Prompt sent to OpenAI completions api: {}", finalPrompt);

        String responseText = chatClient.prompt()
                .user(finalPrompt)
                .call()
                .content();

        promptHistoryService.savePromptHistory(requestId, finalPrompt, responseText);

        log.info("Time taken to complete askAnythingToAI request {}ms", (System.currentTimeMillis() - startTime));
        return new Response(requestId, responseText);
    }
}
