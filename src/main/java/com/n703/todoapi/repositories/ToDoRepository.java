package com.n703.todoapi.repositories;

import com.n703.todoapi.models.ToDo;
import com.n703.todoapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByUserAndIsActive(User user, boolean isActive);
}
