package io.asek.fspProjectboard.repositories;

import io.asek.fspProjectboard.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, String> {

   ProjectTask findProjectTaskById(Long id);

}
