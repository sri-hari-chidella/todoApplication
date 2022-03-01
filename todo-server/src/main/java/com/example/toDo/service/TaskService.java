package com.example.toDo.service;

import com.example.toDo.model.Status;
import com.example.toDo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Page<Task> findAll(Pageable pageable);
    Page<Task> findByStatus(Status status, Pageable pageable);
    Task findById(int id) throws Exception;
    Task createTask(Task task) throws Exception;
    Task updateTask(int id, Task task) throws Exception;
    void deleteTask(int id) throws Exception;
}
