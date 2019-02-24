package io.asek.fspProjectboard.service;

import io.asek.fspProjectboard.exceptions.ProjectTaskNotFoundException;
import io.asek.fspProjectboard.model.ProjectTask;
import io.asek.fspProjectboard.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired(required = true)
    private ProjectTaskRepository projectTaskRepository;

    //save or update
    public void saveProjectTask(ProjectTask projectTask) {

        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus("TO_DO");
        }

         projectTaskRepository.save(projectTask);
    }

    //get single item
    public ProjectTask getProjectTaskById(Long id) {

        boolean exists = projectTaskRepository.existsById(id);

        if (!exists) {
              throw new ProjectTaskNotFoundException(String.format("id=%d not found", id));
        }

        ProjectTask projectTask = projectTaskRepository.findProjectTaskById(id);

        return projectTask;
    }

    //get all items based on project id
    public List<ProjectTask> getAllTasksByProjectId(Long id) {

        List<ProjectTask> allTasks = getAllProjectTasks();
        List<ProjectTask> projectList = new ArrayList<ProjectTask>();

        for(ProjectTask p : allTasks) {
            if (p.getProjectId() == id) {
               projectList.add(p);
            }
        }

        return projectList;
    }

    //get all items
    public List<ProjectTask> getAllProjectTasks() {

        List<ProjectTask> projectTasksList = projectTaskRepository.findAll();

        return projectTasksList;
    }

    //delete item
    public String deleteProjectTaskById(Long id) {

        boolean exists = projectTaskRepository.existsById(id);

        if (!exists) {
            throw new ProjectTaskNotFoundException(String.format("Task with id=%d does not exist.", id));
        }

        projectTaskRepository.deleteById(id);

        return String.format("Deleted id=%d successfully.", id);
    }
}
