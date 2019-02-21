package io.asek.fspProjectboard.controllers;

import io.asek.fspProjectboard.model.Project;
import io.asek.fspProjectboard.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/project")
@CrossOrigin
@SuppressWarnings("unchecked")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //GET
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity getProject(@PathVariable Long id) {

        Project p = projectService.getProjectById(id);

        return new ResponseEntity(p, HttpStatus.OK);
}

    //ADD
    @PostMapping(value = "/addProject", headers = "Accept=*/*", produces = "application/json", consumes="application/json")
    public ResponseEntity addProject(@RequestBody Project project) {

        String addProject = projectService.addProject(project);

        return new ResponseEntity(addProject, HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping(value = "/update")
    public ResponseEntity updateProjectInfo(@RequestBody Project project) {

        String updateProject = projectService.updateProject(project);

        return new ResponseEntity(updateProject, HttpStatus.OK);

    }

    //DELETE

}
