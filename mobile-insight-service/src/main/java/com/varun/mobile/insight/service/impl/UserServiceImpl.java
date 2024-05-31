package com.varun.mobile.insight.service.impl;

import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdationException;
import com.varun.mobile.insight.model.UserDetail;
import com.varun.mobile.insight.repository.UserDetailRepository;
import com.varun.mobile.insight.service.UserService;
import com.varun.mobile.insight.util.MIEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.varun.mobile.insight.common.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public UserDetail createUser(UserDetail userDetail) throws UserCreationException {
        logger.log(Level.INFO, "In service layer createUser method.");
        MIEncoder encoder = MIEncoder.getInstance();
        UserDetail newUserEntry = new UserDetail(userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getEmail(), encoder.encode(userDetail.getPassword()));
        try {
            logger.log(Level.INFO, "Calling repository insert method.");
            return userDetailRepository.insert(newUserEntry);
        } catch (DuplicateKeyException e) {
            throw new UserCreationException(MESSAGE_USER_CREATE_FAIL_KEY, e);
        } catch (Exception e) {
            throw new UserCreationException(MESSAGE_USER_CREATE_FAIL, e);
        }
    }

    @Override
    public UserDetail updateUser(UserDetail userDetail) throws UserUpdationException {
        logger.log(Level.INFO, "In service layer updateUser method.");
        try {
            logger.log(Level.INFO, "Finding user by ID.");
            UserDetail entry = userDetailRepository.findBy_id(userDetail.get_id());
            if(null == entry) {
                throw new UserUpdationException(MESSAGE_USER_NOT_FOUND);
            }
            logger.log(Level.INFO, "Updating user details. Calling repository save method.");
            entry.setFirstName(userDetail.getFirstName());
            entry.setLastName(userDetail.getLastName());
            entry.setEmail(userDetail.getEmail());
            return userDetailRepository.save(entry);
        } catch(DuplicateKeyException e) {
            throw new UserUpdationException(MESSAGE_USER_UPDATE_FAIL_KEY, e);
        } catch (Exception e) {
            String msgString = MESSAGE_USER_UPDATE_FAIL + " " + userDetail.get_id();
            throw new UserUpdationException(msgString, e);
        }
    }
}