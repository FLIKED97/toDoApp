package com.example.To_Do.List.Application.service;

import com.example.To_Do.List.Application.models.Task;
import com.example.To_Do.List.Application.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findByUserId(int id) {
        return taskRepository.findByUserId(id);
    }
}
