package io.github.ruvesh.spring_genai_app.prompts.generic;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
class GenericPromptsRequest {
    private final UUID requestId = UUID.randomUUID();
    @NotBlank(message = "Please enter a prompt.")
    @Size(max = 200, message = "Please shorten the prompt. Max allowed length is 200 characters.")
    private String prompt;
}
