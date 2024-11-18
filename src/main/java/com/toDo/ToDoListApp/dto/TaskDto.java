package com.toDo.ToDoListApp.dto;

import com.toDo.ToDoListApp.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @Id
    private ObjectId id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDate;
}
