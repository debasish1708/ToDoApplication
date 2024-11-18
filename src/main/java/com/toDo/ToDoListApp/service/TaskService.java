package com.toDo.ToDoListApp.service;

import com.toDo.ToDoListApp.dto.TaskDto;
import com.toDo.ToDoListApp.entity.Task;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void saveTask(TaskDto task, String userName);
    void saveTask(TaskDto task);
    void EditTask(ObjectId myId, TaskDto taskDto);
    void deleteTask(ObjectId id, String userName);
    List<TaskDto> getTasks();
    void deleteTaskById(ObjectId id,String userName);
    Optional<Task> findById(ObjectId id);
}
