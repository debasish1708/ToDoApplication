package com.toDo.ToDoListApp.controller;

import com.toDo.ToDoListApp.dto.TaskDto;
import com.toDo.ToDoListApp.dto.UserDto;
import com.toDo.ToDoListApp.entity.Task;
import com.toDo.ToDoListApp.entity.User;
import com.toDo.ToDoListApp.mapper.TaskMapper;
import com.toDo.ToDoListApp.mapper.UserMapper;
import com.toDo.ToDoListApp.service.TaskService;
import com.toDo.ToDoListApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    @GetMapping
    public List<?> getTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findUserByUsername(userName);
        List<TaskDto> tasks = user.getTasks().stream().map(TaskMapper::mapToTaskDto).toList();
        if(!tasks.isEmpty()){
            return tasks;
        }
        return null;
    }

    @PostMapping
    public void addTask(@RequestBody TaskDto myTask) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            taskService.saveTask(myTask, userName);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }



    @PutMapping("id/{myId}")
    public List<TaskDto> updateTask(@PathVariable ObjectId myId, @RequestBody TaskDto myTask) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findUserByUsername(userName);
        UserDto userDto = UserMapper.mapToUserDto(user);
        List<TaskDto> list = userDto.getTasks()
                .stream()
                .filter(x -> x.getId().equals(myId))
                .toList();

        if(!list.isEmpty()){
           Optional<Task> task= taskService.findById(myId);
           if(task.isPresent()){
               Task old = task.get();
                old.setTitle(!myTask.getTitle().isEmpty()?myTask.getTitle():old.getTitle());
                old.setDescription(!myTask.getDescription().isEmpty()?myTask.getDescription():old.getDescription());
                old.setStatus(myTask.getStatus()!=null?myTask.getStatus():old.getStatus());
               TaskDto updatedTasks = TaskMapper.mapToTaskDto(old);
               taskService.saveTask(updatedTasks,userName);
           }
        }

        return userDto.getTasks();
    }


    @DeleteMapping("id/{myId}")
    public void deleteTask(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        taskService.deleteTaskById(myId,userName);
    }
}
