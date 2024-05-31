package com.varun.mobile.insight.service;

import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdationException;
import com.varun.mobile.insight.model.UserDetail;

public interface UserService {

    UserDetail createUser(UserDetail userDetail) throws UserCreationException;

    UserDetail updateUser(UserDetail userDetail) throws UserUpdationException;
}