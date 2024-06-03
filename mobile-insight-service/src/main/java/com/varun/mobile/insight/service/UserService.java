package com.varun.mobile.insight.service;

import com.varun.mobile.insight.dto.UserCreateRequest;
import com.varun.mobile.insight.dto.UserUpdateRequest;
import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdateException;
import com.varun.mobile.insight.model.UserDetail;

public interface UserService {

    UserDetail createUser(UserCreateRequest request) throws UserCreationException;

    UserDetail updateUserDetails(UserUpdateRequest request) throws UserUpdateException;
}