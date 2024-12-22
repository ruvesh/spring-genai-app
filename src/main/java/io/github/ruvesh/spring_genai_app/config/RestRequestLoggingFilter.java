package io.github.ruvesh.spring_genai_app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@Slf4j
public class RestRequestLoggingFilter {

    @Bean
    public CommonsRequestLoggingFilter loggingFilter(){
        var filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setBeforeMessagePrefix("REQUEST RECEIVED: [");
        filter.setAfterMessagePrefix("REQUEST COMPLETED: [");
        return filter;
    }
}
