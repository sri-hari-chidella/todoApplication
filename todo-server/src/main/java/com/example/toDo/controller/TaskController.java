package com.example.toDo.controller;

import com.example.toDo.model.Task;
import com.example.toDo.service.TaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController()
@RequestMapping("api/task")
public class TaskController {
    private final TaskServiceImpl taskService;
    private final Logger logger;
    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
        logger = LoggerFactory.getLogger(TaskController.class);
    }

    @PostMapping()
    public ResponseEntity createTask(@RequestBody Task task) throws Exception {
        logger.info("creating Task");
        return new ResponseEntity(taskService.createTask(task), HttpStatus.CREATED  );
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestBody Task task) throws Exception {
        return new ResponseEntity(taskService.createTask(task),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteTask(@RequestParam int id) throws Exception {
        taskService.deleteTask(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity getTaskById(@PathVariable int id) throws Exception {
        return new ResponseEntity(taskService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTasks(@PageableDefault Pageable pageable){
        logger.info("Getting all tasks");
        return new ResponseEntity(taskService.findAll(pageable), HttpStatus.OK);
    }
}
