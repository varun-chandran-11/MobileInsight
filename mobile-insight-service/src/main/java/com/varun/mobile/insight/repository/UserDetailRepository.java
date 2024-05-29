package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.models.UserDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailRepository extends MongoRepository<UserDetail, String> {


}