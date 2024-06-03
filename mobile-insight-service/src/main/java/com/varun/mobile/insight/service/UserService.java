package com.varun.mobile.insight.service;

import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdateException;
import com.varun.mobile.insight.model.UserDetail;

public interface UserService {

    UserDetail createUser(UserDetail userDetail) throws UserCreationException;

    UserDetail updateUserDetails(UserDetail userDetail) throws UserUpdateException;
}