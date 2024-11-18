package com.toDo.ToDoListApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    private ObjectId id;
    private String userName;
    private String password;
    @DBRef
    private List<TaskDto> tasks=new ArrayList<>();
}
