package com.toDo.ToDoListApp.service.Impl;

import com.toDo.ToDoListApp.dto.UserDto;
import com.toDo.ToDoListApp.entity.User;
import com.toDo.ToDoListApp.mapper.UserMapper;
import com.toDo.ToDoListApp.repository.UserRepository;
import com.toDo.ToDoListApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final static PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public void saveUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void saveNewUser(UserDto userDto) throws IllegalArgumentException {
        try {
            User user = UserMapper.mapToUser(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }


    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToUserDto).toList();
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
