package com.varun.mobile.insight.service.impl;

import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdateException;
import com.varun.mobile.insight.model.UserDetail;
import com.varun.mobile.insight.repository.UserDetailRepository;
import com.varun.mobile.insight.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.varun.mobile.insight.common.StringConstants.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailRepository userDetailRepository;
    Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    //using constructor injection for testability and maintainability
    public UserServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserDetail createUser(UserDetail userDetail) throws UserCreationException {
        logger.log(Level.INFO, "In service layer createUser method.");
        UserDetail newUserEntry = new UserDetail(userDetail.getFirstName(), userDetail.getLastName(),
                userDetail.getEmail(), userDetail.getPassword());
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
    public UserDetail updateUserDetails(UserDetail userDetail) throws UserUpdateException {
        logger.log(Level.INFO, "In service layer updateUser method.");
        UserDetail user = findUserById(userDetail.get_id());
        return saveUserDetails(user);
    }

    /**
     *
     * This method is used to retrieve the user from collection using userId
     *
     * @param userId - id of user details table
     * @return - UserDetails for the given userId
     * @throws UserUpdateException
     */
    private UserDetail findUserById(String userId) throws UserUpdateException {
        logger.log(Level.INFO, "Finding user by ID.");
        try {
            Optional<UserDetail> entryInDB = userDetailRepository.findBy_id(userId);
            if (entryInDB.isEmpty()) {
                throw new UserUpdateException(MESSAGE_USER_NOT_FOUND);
            }
            return entryInDB.get();
        } catch (DuplicateKeyException e) {
            throw new UserUpdateException(MESSAGE_USER_UPDATE_FAIL_KEY, e);
        } catch (Exception e) {
            throw new UserUpdateException(MESSAGE_UNEXPECTED_ERROR, e);
        }
    }

    /**
     *
     * This method is used to save the details to the collection
     *
     * @param userDetail
     * @return - update user details.
     * @throws UserUpdateException
     */
    private UserDetail saveUserDetails(UserDetail userDetail) throws UserUpdateException {
        try {
            logger.log(Level.INFO, "Updating user details. Calling repository save method.");
            userDetail.setFirstName(userDetail.getFirstName());
            userDetail.setLastName(userDetail.getLastName());
            userDetail.setEmail(userDetail.getEmail());
            return userDetailRepository.save(userDetail);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw new UserUpdateException(MESSAGE_USER_UPDATE_FAIL_KEY, e);
        } catch (Exception e) {
            e.printStackTrace();
            String msgString = MESSAGE_USER_UPDATE_FAIL + " " + userDetail.get_id();
            throw new UserUpdateException(msgString, e);
        }
    }
}