package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.BillingCycle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BillingCycleRepository extends MongoRepository<BillingCycle, String> {


    @Query("{userId:'?0', mdn:'?1'}")
    BillingCycle findItemByUserIdAndMdn(String userId, String mdn);

    @Query(value="{userId:'?0', mdn:'?1'}", fields="{'_id' : 1, 'startDate' : 1, 'endDate' : 1}")
    List<BillingCycle> findAll(String userId, String mdn);
}