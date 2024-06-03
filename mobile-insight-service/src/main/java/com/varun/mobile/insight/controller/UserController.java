package com.varun.mobile.insight.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.dto.UserCreateRequest;
import com.varun.mobile.insight.dto.UserUpdateRequest;
import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdateException;
import com.varun.mobile.insight.model.UserDetail;
import com.varun.mobile.insight.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    Logger logger = Logger.getLogger(UserController.class.getName());

    //using constructor injection for testability and maintainability
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @JsonView(Views.Public.class)
    public ResponseEntity<UserDetail> createUser(@RequestBody UserCreateRequest request) throws UserCreationException {
        logger.log(Level.INFO, "Inside controller create method.");
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/update")
    @JsonView(Views.Public.class)
    public ResponseEntity<UserDetail> updateUser(@RequestBody UserUpdateRequest request) throws UserUpdateException {
        logger.log(Level.INFO, "Inside controller update method.");
        return ResponseEntity.ok(userService.updateUserDetails(request));
    }

}