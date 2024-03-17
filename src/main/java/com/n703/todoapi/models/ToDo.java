package com.n703.todoapi.models;

import com.n703.todoapi.dtos.request.ToDoRequestDTO;
import com.n703.todoapi.dtos.request.ToDoUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean isComplete;
    private Boolean isActive;

    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public ToDo(ToDoRequestDTO dto) {
        this.title = dto.title();
        this.description = dto.description();
        this.isComplete = false;
        this.isActive = true;
    }

    public void update(ToDoUpdateRequestDTO dto) {
        if (dto.title() != null) {
            this.title = dto.title();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }

        this.isComplete = dto.isComplete();
    }

    public void deleteLogical() {
        this.isActive = false;
    }
}
