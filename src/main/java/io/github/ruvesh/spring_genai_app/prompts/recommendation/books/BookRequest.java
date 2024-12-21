package io.github.ruvesh.spring_genai_app.prompts.recommendation.books;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private final UUID requestId = UUID.randomUUID();
    @Size(max = 30, message = "Author name is too long. Max allowed is 30 characters.")
    private String author;
    @Size(max = 30, message = "Genre is too long. Max allowed is 30 characters.")
    private String genre;
    @PastOrPresent(message = "We can't recommend books which haven't been published yet. Please enter past or current year of publication.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private Year yearOfPublication;
    @Size(max = 30, message = "Language is too long. Max allowed is 30 characters.")
    private String language;
}
