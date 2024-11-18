package com.toDo.ToDoListApp.mapper;

import com.toDo.ToDoListApp.dto.TaskDto;
import com.toDo.ToDoListApp.dto.UserDto;
import com.toDo.ToDoListApp.entity.Task;
import com.toDo.ToDoListApp.entity.User;
import java.util.List;

public class UserMapper {
    public static User mapToUser(UserDto userDto) {
        List<Task> list = userDto.getTasks().stream().map(TaskMapper::mapToTask).toList();
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassword(),
                list
        );
    }

    public static UserDto mapToUserDto(User user) {
        List<TaskDto> list = user.getTasks().stream().map(TaskMapper::mapToTaskDto).toList();
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                list
        );
    }
}
