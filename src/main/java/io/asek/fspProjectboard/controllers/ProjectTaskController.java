package io.asek.fspProjectboard.controllers;

import io.asek.fspProjectboard.DTO.ProjectTaskDTO;
import io.asek.fspProjectboard.model.ProjectTask;
import io.asek.fspProjectboard.repositories.ProjectTaskRepository;
import io.asek.fspProjectboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/project")
@CrossOrigin
@SuppressWarnings("unchecked")
public class ProjectTaskController {

//    Controllers should be referred to as routing mechanisms,
//    reduce complex login in controllers, leave that to service/repository

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectTaskService projectTaskService;

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity getProjectTask(@PathVariable long id) {

        ProjectTask p = projectTaskService.getProjectTaskById(id);

        return new ResponseEntity(p, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity getAllTasks() {

        List<ProjectTask> tasks = projectTaskService.getAllProjectTasks();

        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping(value = "/addTask", headers = "Accept=*/*", produces = "application/json", consumes="application/json")
    public ResponseEntity addTask(@RequestBody ProjectTask p) {

        //Add a new project task
        //projectTaskRepository.save(p);

        ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(p);
        return new ResponseEntity(newPT, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteTask/{id}")
    public ResponseEntity deleteProjectTask(@PathVariable Long id) {

        boolean projectTaskExists = projectTaskRepository.existsById(id);

        if(!projectTaskExists) {
            return new ResponseEntity(String.format("Cannot find project task with id=%d", id), HttpStatus.OK);
        }

        projectTaskRepository.deleteById(id);
        return new ResponseEntity("Deleted successfully.", HttpStatus.OK);
    }

    @PutMapping(value = "update/{id}", headers = "Accept=*/*", produces = "application/json", consumes="application/json")
    public ResponseEntity updateProjectTask(@PathVariable Long id, @RequestBody ProjectTaskDTO projectTaskDTO) {

        boolean projectTaskExists = projectTaskRepository.existsById(id);

        if(!projectTaskExists) {
            return new ResponseEntity(String.format("Cannot find project task with id=%d", id), HttpStatus.OK);
        }

        ProjectTask p = projectTaskRepository.findProjectTaskById(id);
        p.setSummary(projectTaskDTO.getSummary());
        p.setStatus(projectTaskDTO.getStatus());
        p.setDescription(projectTaskDTO.getDescription());

        projectTaskRepository.save(p);
        return new ResponseEntity("Updated successfully.", HttpStatus.OK);
    }

}
