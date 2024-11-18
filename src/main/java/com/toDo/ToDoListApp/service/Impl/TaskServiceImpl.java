package com.toDo.ToDoListApp.service.Impl;

import com.toDo.ToDoListApp.dto.TaskDto;
import com.toDo.ToDoListApp.dto.UserDto;
import com.toDo.ToDoListApp.entity.Task;
import com.toDo.ToDoListApp.entity.User;
import com.toDo.ToDoListApp.mapper.TaskMapper;
import com.toDo.ToDoListApp.mapper.UserMapper;
import com.toDo.ToDoListApp.repository.TaskRepository;
import com.toDo.ToDoListApp.repository.UserRepository;
import com.toDo.ToDoListApp.service.TaskService;
import com.toDo.ToDoListApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void saveTask(TaskDto taskDto, String userName) {
        try {
            User user = userService.findUserByUsername(userName);
            Task task = TaskMapper.mapToTask(taskDto);
            task.setDueDate(LocalDateTime.now());
            Task save = taskRepository.save(task);
            user.getTasks().add(save);
            UserDto userDto = UserMapper.mapToUserDto(user);
            userService.saveUser(userDto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void saveTask(TaskDto task) {
        Task task1 = TaskMapper.mapToTask(task);
        taskRepository.save(task1);
    }

    @Override
    public void EditTask(ObjectId myId, TaskDto taskDto) {

    }

    @Transactional
    @Override
    public void deleteTask(ObjectId id,String userName) {

    }

    @Override
    public List<TaskDto> getTasks() {
        return taskRepository.findAll().stream().map(TaskMapper::mapToTaskDto).toList();
    }

    @Transactional
    @Override
    public void deleteTaskById(ObjectId id, String userName) {
        try {
            User user = userService.findUserByUsername(userName);
            boolean removed = user.getTasks().removeIf(task -> task.getId().equals(id));
            if(removed){
                UserDto userDto = UserMapper.mapToUserDto(user);
                userService.saveUser(userDto);
                taskRepository.deleteById(id);
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public Optional<Task> findById(ObjectId id) {
        return taskRepository.findById(id);
    }
}
