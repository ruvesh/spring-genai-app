package io.github.ruvesh.spring_genai_app.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodExecutionTimer {

    @Around("@annotation(io.github.ruvesh.spring_genai_app.annotation.Timer)")
    public Object logMethodExecutionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = point.getSignature().getDeclaringTypeName();
        String methodName = point.getSignature().getName();
        log.info("{}.{} method execution started", className, methodName);
        Object process = point.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("{}.{} method execution completed. Time taken: {}ms", className, methodName, timeTaken);
        return process;
    }
}
