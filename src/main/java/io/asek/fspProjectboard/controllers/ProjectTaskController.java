package io.asek.fspProjectboard.controllers;

import io.asek.fspProjectboard.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/project")
@CrossOrigin
@SuppressWarnings("unchecked")
public class ProjectTaskController {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @GetMapping(value = "/get")
    public String getProject() {
        return "hitting properly";
    }
}
