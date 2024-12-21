package io.github.ruvesh.spring_genai_app.advice;

import io.github.ruvesh.spring_genai_app.data.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidRequest(MethodArgumentNotValidException exception, HttpServletRequest request){
        Map<String, String> fieldErrorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                    fieldErrorMap.put(fieldError.getField(),
                    fieldError.getDefaultMessage())
                );
        log.error("Bad Request. Details of invalid fields are: {}", fieldErrorMap);
        return new ErrorResponse(
                request.getRequestURI(),
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                Map.of("fieldErrors", fieldErrorMap)
        );
    }

    /**
     * Catch-all handler for unhandled exceptions
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception, HttpServletRequest request){
        log.error("An Unknown Exception occurred with message: {}", exception.getLocalizedMessage(), exception);
        return new ErrorResponse(
                request.getRequestURI(),
                LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getLocalizedMessage());
    }
}
