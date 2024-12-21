package io.github.ruvesh.spring_genai_app.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PROMPT_HISTORY")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "init")
public class PromptHistory {

    @Id
    @Column(name= "REQUEST_ID", updatable = false, length = 36)
    private String requestId;

    @Column(name = "PROMPT", nullable = false, updatable = false, length = 500)
    private String prompt;

    @Column(name = "RESPONSE", updatable = false, length = 1024)
    private String response;

    @Column(name = "PROMPT_TIMESTAMP", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime promptTimestamp;
}
