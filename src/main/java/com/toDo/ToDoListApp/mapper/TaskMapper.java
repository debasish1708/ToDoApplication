package com.toDo.ToDoListApp.mapper;

import com.toDo.ToDoListApp.dto.TaskDto;
import com.toDo.ToDoListApp.entity.Task;

public class TaskMapper {
    public static Task mapToTask(TaskDto taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getStatus(),
                taskDto.getDueDate()
        );
    }

    public static TaskDto mapToTaskDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate()
        );
    }
}
