package io.github.ruvesh.spring_genai_app.data.dto;

public record ErrorResponse(String path, String timestamp, int status, Object errorDetails) {
}
