package com.toDo.ToDoListApp.entity;

import com.toDo.ToDoListApp.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private ObjectId id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Status status;
    private LocalDateTime dueDate;
}
