package com.n703.todoapi.controllers;

import com.n703.todoapi.dtos.request.ToDoRequestDTO;
import com.n703.todoapi.dtos.request.ToDoUpdateRequestDTO;
import com.n703.todoapi.dtos.response.ToDoResponseDTO;
import com.n703.todoapi.helpers.GetUserFromTokenHelper;
import com.n703.todoapi.services.ToDoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;
    private final GetUserFromTokenHelper getUserFromTokenHelper;

    public ToDoController(ToDoService toDoService, GetUserFromTokenHelper getUserFromTokenHelper) {
        this.toDoService = toDoService;
        this.getUserFromTokenHelper = getUserFromTokenHelper;
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponseDTO>> getAll(HttpServletRequest request) {
        var user = getUserFromTokenHelper.getUserIdFromToken(request);
        var dtos = toDoService.getAll(user);

        return ResponseEntity.ok(dtos);
    }


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ToDoRequestDTO dto, HttpServletRequest request) {
        var user = getUserFromTokenHelper.getUserIdFromToken(request);
        var todo = toDoService.save(dto, user);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(todo.id()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoResponseDTO> update(@RequestBody ToDoUpdateRequestDTO dto, @PathVariable Long id) {
        var todo = toDoService.update(dto, id);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        toDoService.logicalDelete(id);
        return ResponseEntity.noContent().build();
    }
}
