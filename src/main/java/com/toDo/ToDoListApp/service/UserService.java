package com.toDo.ToDoListApp.service;

import com.toDo.ToDoListApp.dto.UserDto;
import com.toDo.ToDoListApp.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto UserDto);
    void saveNewUser(UserDto UserDto) throws IllegalArgumentException;
    List<UserDto> getUsers();
    void deleteByUsername(String username);
    User findUserByUsername(String username);
}
