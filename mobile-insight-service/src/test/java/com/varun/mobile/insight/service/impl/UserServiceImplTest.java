package com.varun.mobile.insight.service.impl;

import com.varun.mobile.insight.dto.UserCreateRequest;
import com.varun.mobile.insight.dto.UserUpdateRequest;
import com.varun.mobile.insight.exception.UserCreationException;
import com.varun.mobile.insight.exception.UserUpdateException;
import com.varun.mobile.insight.model.UserDetail;
import com.varun.mobile.insight.repository.UserDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserDetailRepository userDetailRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_Success() throws UserCreationException {
        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        UserDetail userDetail = new UserDetail("John", "Doe", "john.doe@example.com", "password");

        when(userDetailRepository.insert(any(UserDetail.class))).thenReturn(userDetail);

        UserDetail result = userService.createUser(request);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    public void testCreateUser_DuplicateKeyException() {
        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        when(userDetailRepository.insert(any(UserDetail.class))).thenThrow(DuplicateKeyException.class);

        assertThrows(UserCreationException.class, () -> userService.createUser(request));
    }

    @Test
    public void testUpdateUser_Success() throws UserUpdateException {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUserId("123");
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setEmail("jane.doe@example.com");

        UserDetail userDetail = new UserDetail("John", "Doe", "john.doe@example.com", "password");
        userDetail.set_id("123");

        when(userDetailRepository.findBy_id("123")).thenReturn(Optional.of(userDetail));
        when(userDetailRepository.save(any(UserDetail.class))).thenReturn(userDetail);

        UserDetail result = userService.updateUserDetails(request);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("jane.doe@example.com", result.getEmail());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUserId("123");
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setEmail("jane.doe@example.com");

        when(userDetailRepository.findBy_id("123")).thenReturn(Optional.empty());

        assertThrows(UserUpdateException.class, () -> userService.updateUserDetails(request));
    }

    @Test
    public void testUpdateUser_DuplicateKeyException() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUserId("123");
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setEmail("jane.doe@example.com");

        UserDetail userDetail = new UserDetail("John", "Doe", "john.doe@example.com", "password");
        userDetail.set_id("123");

        when(userDetailRepository.findBy_id("123")).thenReturn(Optional.of(userDetail));
        when(userDetailRepository.save(any(UserDetail.class))).thenThrow(DuplicateKeyException.class);

        assertThrows(UserUpdateException.class, () -> userService.updateUserDetails(request));
    }
}
