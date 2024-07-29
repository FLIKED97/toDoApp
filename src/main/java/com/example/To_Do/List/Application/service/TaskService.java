package com.example.To_Do.List.Application.service;

import com.example.To_Do.List.Application.models.Task;
import com.example.To_Do.List.Application.models.User;
import com.example.To_Do.List.Application.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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


    public void save(Task task, User user) {
        task.setUser(user);
        task.setAddTime(new Date());

        taskRepository.save(task);
    }

    public Optional<Task> findByTaskId(int id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}
