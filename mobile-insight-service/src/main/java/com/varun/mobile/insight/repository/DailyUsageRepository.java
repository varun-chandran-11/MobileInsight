package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.models.DailyUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {

    @Query(value="{userId:'?0', mdn:'?1'}", fields="{'usageDate' : 1, 'usedInMb' : 1}")
    List<DailyUsage> findAll(String userId, String mdn);

}