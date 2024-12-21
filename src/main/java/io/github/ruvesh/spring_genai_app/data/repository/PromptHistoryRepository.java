package io.github.ruvesh.spring_genai_app.data.repository;

import io.github.ruvesh.spring_genai_app.data.entity.PromptHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptHistoryRepository extends JpaRepository<PromptHistory, String> {

}
