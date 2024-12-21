package io.github.ruvesh.spring_genai_app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("GenAI Spring App")
                        .description("""
                                A GenAI application using Spring AI.
                                
                                
                                You may need to create an API Key from the API providers like OpenAI.
                                Then, add the OPEN_AI_API_KEY to your environment variables or the application.yml directly.
                                But, please do not commit the key as that is a security risk.
                                
                                
                                The models are generally paid features of the platform.
                                This application uses the gpt-4o model of OpenAI which is a paid feature.
                                """
                        )
                        .contact(new Contact().name("Ruvesh").url("https://ruvesh.github.io"))
                        .version("1.0")
                );
    }

    @Bean
    public GroupedOpenApi genericPromptsGroup(){
        String[] packagesToScan = { "io.github.ruvesh.spring_genai_app.prompts.generic" };
        return GroupedOpenApi.builder().group("generic").packagesToScan(packagesToScan).build();
    }

    @Bean
    public GroupedOpenApi recommendationPromptsGroup(){
        String[] packagesToScan = { "io.github.ruvesh.spring_genai_app.prompts.recommendation.books" };
        return GroupedOpenApi.builder().group("recommendation").packagesToScan(packagesToScan).build();
    }
}
