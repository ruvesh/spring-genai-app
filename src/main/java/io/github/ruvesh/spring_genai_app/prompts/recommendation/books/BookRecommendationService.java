package io.github.ruvesh.spring_genai_app.prompts.recommendation.books;

import io.github.ruvesh.spring_genai_app.data.dto.Response;

interface BookRecommendationService {

    Response getBookRecommendation(BookRequest request);
}
