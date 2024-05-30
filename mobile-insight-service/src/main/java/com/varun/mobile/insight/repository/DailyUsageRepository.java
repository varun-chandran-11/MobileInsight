package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.DailyUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {

    @Query(fields="{'usageDate' : 1, 'usedInMb' : 1}")
    List<DailyUsage> findByUserIdAndMdn(String userId, String mdn);

}