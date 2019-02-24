package io.asek.fspProjectboard.repositories;


import io.asek.fspProjectboard.model.Project;
import io.asek.fspProjectboard.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

//    List<ProjectTask> getAllTasksByProjectId(Long id);
    Project findProjectById(Long id);

    @Override
    void deleteById(Long id);
}
