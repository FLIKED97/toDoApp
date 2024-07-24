package com.example.To_Do.List.Application.repository;

import com.example.To_Do.List.Application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
