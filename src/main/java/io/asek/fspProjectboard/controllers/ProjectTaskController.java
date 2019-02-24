package io.asek.fspProjectboard.controllers;

import io.asek.fspProjectboard.model.ProjectTask;
import io.asek.fspProjectboard.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/project_tasks")
@CrossOrigin
@SuppressWarnings("unchecked")
public class ProjectTaskController {

//    Controllers should be referred to as routing mechanisms,
//    reduce complex login in controllers, leave that to service/repository

    @Autowired(required = true)
    private ProjectTaskService projectTaskService;

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity getProjectTask(@PathVariable long id) {

        ProjectTask p = projectTaskService.getProjectTaskById(id);

        return new ResponseEntity(p, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllTasks", produces = "application/json")
    public ResponseEntity getAllTasks() {

        List<ProjectTask> tasks = projectTaskService.getAllProjectTasks();

        return new ResponseEntity(tasks, HttpStatus.OK);
    }

//  TODO
//    create two separate end points for UPDATE and POST (add)
    @PostMapping(value = "/{projectId}/add", headers = "Accept=*/*", produces = "application/json", consumes="application/json")
    public ResponseEntity saveProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                                  @PathVariable Long projectId) {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
        }

        projectTask.setProjectId(projectId);

        projectTaskService.saveProjectTask(projectTask);

        return new ResponseEntity("Saved successfully.", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteTask/{id}")
    public ResponseEntity deleteProjectTask(@PathVariable Long id) {

        String deleteTask = projectTaskService.deleteProjectTaskById(id);

        return new ResponseEntity(deleteTask, HttpStatus.OK);
    }

}
