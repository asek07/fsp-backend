package io.asek.fspProjectboard.service;

import io.asek.fspProjectboard.exceptions.ProjectAlreadyExistsException;
import io.asek.fspProjectboard.exceptions.ProjectNotFoundException;
import io.asek.fspProjectboard.model.Project;
import io.asek.fspProjectboard.model.ProjectTask;
import io.asek.fspProjectboard.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectTaskService projectTaskService;

    //get
    public Project getProjectById(Long id) {

        boolean exists = projectRepository.existsById(id);

        if (!exists) {
            throw new ProjectNotFoundException(String.format("Project with id %d does not exist.", id));
        }

        Project project = projectRepository.findProjectById(id);

        List<ProjectTask> projectTasks = projectTaskService.getAllTasksByProjectId(id);

        project.setProjectTasks(projectTasks);

        return project;
    }

    //add
    public String addProject(Project project) {

//        boolean exists = projectRepository.existsById(project.getId());
//
//        if (exists) {
//            throw new ProjectAlreadyExistsException(String.format("Project with id %d already exists.", project.getId()));
//        }

        Project newProject = new Project();

        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());

        projectRepository.save(newProject);

        return String.format("Successfully saved '%s'", project.getName());
    }

    //delete
    public String deleteProjectById(Long id) {

        boolean exists = projectRepository.existsById(id);

        if (!exists) {
            throw new ProjectNotFoundException(String.format("Project with id %d does not exist.", id));
        }

//        find all the tasks that have the same id as input
//        loop through and delete every one of them
//        then delete the project after all the tasks have been deleted


        return "";
    }

    //update
    public String updateProject(Project project) {

        boolean exists = projectRepository.existsById(project.getId());

        if (!exists) {
            throw new ProjectNotFoundException(String.format("Project with id %d does not exist.",project.getId()));
        }

        projectRepository.save(project);

        return "Successfully updated project info.";
    }
}
