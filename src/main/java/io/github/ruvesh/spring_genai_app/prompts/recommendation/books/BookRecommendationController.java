package io.github.ruvesh.spring_genai_app.prompts.recommendation.books;

import io.github.ruvesh.spring_genai_app.data.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Recommendations", description = "Engineered prompts which will recommend books based on user's inputs")
@RestController
@RequestMapping("/prompts/recommendation/books")
class BookRecommendationController {

    private final BookRecommendationService service;

    BookRecommendationController(@Qualifier("openAIBookRecommendationService") BookRecommendationService service) {
        this.service = service;
    }

    @PostMapping("")
    @Operation(summary = "Get book recommendations based on provided criteria")
    ResponseEntity<Response> getBookRecommendations(@io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Book Recommendation request with provided criteria",
      content = @Content(mediaType = "application/json",
              schema = @Schema(contentSchema = BookRequest.class),
              examples = @ExampleObject(value = """
                          {
                              "author": "Author's name (Optional)",
                              "genre": "Genre of books (Optional)",
                              "yearOfPublication": "Year in yyyy format (Optional)",
                              "language": "Language of book (Optional)"
                          }
                      """)
      )
    ) @RequestBody @Valid BookRequest request){
        return ResponseEntity.ok(service.getBookRecommendation(request));
    }
}
