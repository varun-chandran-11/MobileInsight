package com.varun.mobile.insight.controller;

import com.varun.mobile.insight.dto.UserCreateRequest;
import com.varun.mobile.insight.dto.UserUpdateRequest;
import com.varun.mobile.insight.model.UserDetail;
import com.varun.mobile.insight.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("Varun");
        request.setLastName("Chandy");
        request.setEmail("john.doe@example.com");

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName("Varun");
        userDetail.setLastName("Chandy");
        userDetail.setEmail("john.doe1@example.com");
        userDetail.set_id("665d482d8565ff1723ad3d8b");
        userDetail.setPassword("Test@123");

        when(userService.createUser(any(UserCreateRequest.class))).thenReturn(userDetail);

        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Varun\",\"lastName\":\"Chan\",\"email\":\"varun.chan@gmail.com\",\"password\":\"Test@123\"}")) // JSON representation of UserCreateRequest
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setFirstName("Varun");
        request.setLastName("Chan");
        request.setEmail("varun.chan@dispostable.com");
        request.setUserId("665d482d8565ff1723ad3d8b");

        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName("Varun");
        userDetail.setLastName("Chandy");
        userDetail.setEmail("varun.chandy@dispostable.com");
        userDetail.set_id("665d482d8565ff1723ad3d8b");
        userDetail.setPassword("Test@123");

        when(userService.updateUserDetails(any(UserUpdateRequest.class))).thenReturn(userDetail);

        mockMvc.perform(post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"665d482d8565ff1723ad3d8b\",\"firstName\":\"Varun\",\"lastName\":\"Chandy\",\"email\":\"varun.chandy@gmail.com\"}")) // JSON representation of UserUpdateRequest
                .andExpect(status().isOk());
    }
}
