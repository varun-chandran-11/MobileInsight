package com.varun.mobile.insight.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdationException;
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
    public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetail) throws UserCreationException {
        logger.log(Level.INFO, "Inside controller create method.");
        return ResponseEntity.ok(userService.createUser(userDetail));
    }

    @PostMapping("/update")
    @JsonView(Views.Public.class)
    public ResponseEntity<UserDetail> updateUser(@RequestBody UserDetail userDetail) throws UserUpdationException {
        logger.log(Level.INFO, "Inside controller update method.");
        return ResponseEntity.ok(userService.updateUser(userDetail));
    }

}