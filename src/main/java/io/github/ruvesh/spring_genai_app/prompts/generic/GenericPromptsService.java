package io.github.ruvesh.spring_genai_app.prompts.generic;

import io.github.ruvesh.spring_genai_app.data.dto.Response;

interface GenericPromptsService {

    String REQUEST_CONTEXT = "Please avoid markdown/HTML and regular expressions in the responses to the following prompts and respond only with single-line plain text in not more than 100 words. ";

    Response greetUserWithAI();
    Response askAnythingToAI(GenericPromptsRequest request);
}
