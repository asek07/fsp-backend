package io.asek.fspProjectboard.repositories;

import io.asek.fspProjectboard.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    ProjectTask findProjectTaskById(Long id);
    List<ProjectTask> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends ProjectTask> S save(S s);

    @Override
    Optional<ProjectTask> findById(Long aLong);
}
