package com.toDo.ToDoListApp.controller;

import com.toDo.ToDoListApp.dto.UserDto;
import com.toDo.ToDoListApp.service.UserDetailsServiceImpl;
import com.toDo.ToDoListApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private static AuthenticationManager authenticationManager;
    private final DefaultAuthenticationEventPublisher authenticationEventPublisher;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto) {
        try {
            userService.saveNewUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUserName());
            return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
        } catch (UsernameNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
