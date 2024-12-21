package io.github.ruvesh.spring_genai_app.prompts.generic;

import io.github.ruvesh.spring_genai_app.data.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Generic Prompts", description = "Generic Prompts which return a text based response from the LLM")
@RestController
@RequestMapping("/prompts/generic")
class GenericPromptsController {

    private final GenericPromptsService promptsService;

    GenericPromptsController(@Qualifier("genericOpenAIPromptsService") GenericPromptsService promptsService) {
        this.promptsService = promptsService;
    }

    @GetMapping("")
    @Operation(summary = "Generate a random greeting for the user with AI")
    ResponseEntity<Response> greetingEndpoint(){
        return ResponseEntity.ok(promptsService.greetUserWithAI());
    }

    @PostMapping("/ask")
    @Operation(summary = "Ask any question to the AI")
    ResponseEntity<Response> askAnything(@io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Prompt to AI",
      content = @Content(mediaType = "application/json",
                            schema = @Schema(contentSchema = GenericPromptsRequest.class),
                            examples = @ExampleObject(value = "{\"prompt\": \"Your prompt here\"}")
      )
    ) @RequestBody @Valid GenericPromptsRequest request){
        return ResponseEntity.ok(promptsService.askAnythingToAI(request));
    }

}
