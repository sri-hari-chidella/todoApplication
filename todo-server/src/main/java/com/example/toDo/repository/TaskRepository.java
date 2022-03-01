package com.example.toDo.repository;

import com.example.toDo.model.Status;
import com.example.toDo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer>, JpaRepository<Task, Integer> {
    Page<Task> findByStatus(Status status, Pageable pageable);
}
