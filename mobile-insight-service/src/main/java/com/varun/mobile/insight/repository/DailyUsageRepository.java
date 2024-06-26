package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.DailyUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {

    @Query("{ 'userId' : ?0, 'mdn' : ?1, 'usageDate' : { $gte: ?2, $lte: ?3 } }")
    Page<DailyUsage> findItemByUserIdAndMdnAndUsageDateBetween(String userId, String mdn, Date from, Date to, Pageable page);

}