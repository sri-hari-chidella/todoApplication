package com.example.toDo.service;

import com.example.toDo.controller.TaskController;
import com.example.toDo.model.Status;
import com.example.toDo.model.Task;
import com.example.toDo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final Logger logger;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        logger = LoggerFactory.getLogger(TaskController.class);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        Page<Task> allTasks = taskRepository.findAll(pageable);
        logger.info(String.valueOf(allTasks));
        return allTasks;
    }

    @Override
    public Page<Task> findByStatus(Status status, Pageable pageable) {
        return taskRepository.findByStatus(status, pageable);
    }

    @Override
    public Task findById(int id) throws Exception{
        return taskRepository.findById(id).orElseThrow(() -> new Exception("Task could not be created"));
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(int id, Task task) throws Exception {
        return null;
    }

    @Override
    public void deleteTask(int id) throws Exception {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
