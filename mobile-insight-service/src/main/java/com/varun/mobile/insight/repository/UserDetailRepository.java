package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.UserDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserDetailRepository extends MongoRepository<UserDetail, String> {

    @Query(fields = "{'_id' : 1, 'firstName' : 1, 'lastName' : 1, 'email' : 1}")
    Optional<UserDetail> findBy_id(String id);
}