package io.asek.fspProjectboard.repositories;


import io.asek.fspProjectboard.model.Project;
import io.asek.fspProjectboard.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

//    List<ProjectTask> getAllTasksByProjectId(Long id);
    Project findProjectById(Long id);

    @Override
    void deleteById(Long id);
}
