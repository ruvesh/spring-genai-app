package io.github.ruvesh.spring_genai_app.prompts.recommendation.books;

import io.github.ruvesh.spring_genai_app.annotation.Timer;
import io.github.ruvesh.spring_genai_app.data.dto.Response;
import io.github.ruvesh.spring_genai_app.service.PromptHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("openAIBookRecommendationService")
@Slf4j
class OpenAIBookRecommendationService implements BookRecommendationService {

    private final ChatClient chatClient;

    private final PromptHistoryService promptHistoryService;

    OpenAIBookRecommendationService(PromptHistoryService promptHistoryService, ChatClient.Builder chatClientBuilder) {
        this.promptHistoryService = promptHistoryService;
        this.chatClient = chatClientBuilder.build();
    }

    @Timer
    @Override
    public Response getBookRecommendation(BookRequest request) {
        String requestId = request.getRequestId().toString();
        String prompt = prepareBookRecommendationPromptForProvidedCriteria(request);
        log.info("Prompt sent to OpenAI completions api: {}", prompt);

        List<Book> books = chatClient.prompt()
                        .user(prompt)
                        .call()
                        .entity(new ParameterizedTypeReference<>() {});
        promptHistoryService.savePromptHistory(requestId, prompt, CollectionUtils.isEmpty(books) ? List.of().toString() : books.toString());
        return new Response(requestId, books);
    }

    private String prepareBookRecommendationPromptForProvidedCriteria(BookRequest request){
        StringBuilder promptBuilder = new StringBuilder("Please recommend a few books");
        if(StringUtils.isNotBlank(request.getAuthor()))
            promptBuilder.append(" written by ").append(request.getAuthor().trim());
        if(StringUtils.isNotBlank(request.getGenre()))
            promptBuilder.append(" of the genre ").append(request.getGenre().trim());
        if(request.getYearOfPublication() != null)
            promptBuilder.append(" published in the year ").append(request.getYearOfPublication().toString());
        if(StringUtils.isNotBlank(request.getLanguage()))
            promptBuilder.append(" written in the ").append(request.getLanguage().trim()).append(" language");
        return promptBuilder.append(".").toString();
    }

}
