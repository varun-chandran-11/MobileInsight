package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
public class DailyUsageRepositoryTest {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private UserDetail userDetail;

    @BeforeEach
    public void setUp() {
        userDetailRepository.deleteAll();

        userDetail = new UserDetail();
        userDetail.setFirstName("Varun");
        userDetail.setLastName("Nair");
        userDetail.setEmail("varun.chandran.11@gmail.com");
        userDetail.setPassword("password");

        mongoTemplate.save(userDetail);
    }

    @Test
    public void testFindById() {
        Optional<UserDetail> result = userDetailRepository.findBy_id(userDetail.get_id());

        assertTrue(result.isPresent());
        assertEquals(userDetail.get_id(), result.get().get_id());
        assertEquals("Varun", result.get().getFirstName());
        assertEquals("Nair", result.get().getLastName());
        assertEquals("varun.chandran.11@gmail.com", result.get().getEmail());
    }

}