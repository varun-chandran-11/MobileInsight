package com.varun.mobile.insight.controller;

import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdateException;
import com.varun.mobile.insight.model.UserDetail;
import com.varun.mobile.insight.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.varun.mobile.insight.common.Constants.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @PostMapping("/create")
    public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetail) throws UserCreationException {
        try {
            UserDetail newUserEntry = new UserDetail(userDetail.getFirstName(), userDetail.getLastName(), userDetail.getEmail(), userDetail.getPassword());
            newUserEntry = userDetailRepository.save(newUserEntry);
            return ResponseEntity.ok(newUserEntry);
        } catch(Exception e) {
            throw new UserCreationException(MESSAGE_USER_CREATE_FAIL, e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<UserDetail> updateUser(@RequestBody UserDetail userDetail) throws UserUpdateException {
        try {
            UserDetail entry = userDetailRepository.findBy_id(userDetail.get_id());
            if(null == entry) {
                throw new UserUpdateException(MESSAGE_USER_NOT_FOUND);
            }
            entry.setFirstName(userDetail.getFirstName());
            entry.setLastName(userDetail.getLastName());
            entry.setEmail(userDetail.getEmail());
            userDetailRepository.save(entry);
            return ResponseEntity.ok(entry);
        } catch(Exception e) {
            StringBuilder sb = new StringBuilder(MESSAGE_USER_UPDATE_FAIL);
            sb.append(Character.SPACE_SEPARATOR);
            sb.append(userDetail.get_id());
            throw new UserUpdateException(sb.toString(), e);
        }
    }

}
