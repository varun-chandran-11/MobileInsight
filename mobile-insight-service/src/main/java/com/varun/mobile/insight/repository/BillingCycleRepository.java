package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.BillingCycle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillingCycleRepository extends MongoRepository<BillingCycle, String> {

    @Query("{userId:'?0', mdn:'?1', 'startDate' : { $lte: ?2 }, 'endDate' : { $gte: ?2 }}")
    Optional<BillingCycle> findItemByUserIdAndMdnAndDate(String userId, String mdn, Date currentDate);

    @Query(value = "{userId:'?0', mdn:'?1'}", fields = "{'_id' : 1, 'startDate' : 1, 'endDate' : 1}")
    List<BillingCycle> findAll(String userId, String mdn);

}