package com.example.To_Do.List.Application.repository;

import com.example.To_Do.List.Application.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUserId(int userId);

}
