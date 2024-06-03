package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.util.MIEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class BillingCycleRepositoryTest {

    @Autowired
    private BillingCycleRepository billingCycleRepository;

    private BillingCycle billingCycle1;

    @BeforeEach
    public void setUp() {

        billingCycle1 = new BillingCycle();
        billingCycle1.setUserId("66595f7f832f0e6c0e31d75c");
        billingCycle1.setMdn("4379892179");
        billingCycle1.setStartDate(new Date(System.currentTimeMillis() - 100000000L)); // 10 days ago
        billingCycle1.setEndDate(new Date(System.currentTimeMillis() + 100000000L)); // 10 days in the future
        /*
        mongoTemplate.save(billingCycle1);
        */
    }

    @Test
    public void testFindItemByUserIdAndMdnAndDate() {
        Date currentDate = new Date();
        Optional<BillingCycle> result = billingCycleRepository.findItemByUserIdAndMdnAndDate("66595f7f832f0e6c0e31d75c", MIEncoder.encoder.encode("4379892179"), currentDate);

        assertTrue(result.isPresent());
        assertEquals(billingCycle1.getUserId(), result.get().getUserId());
        assertEquals(billingCycle1.getMdn(), result.get().getMdn());
    }

    @Test
    public void testFindAll() {
        List<BillingCycle> result = billingCycleRepository.findAll("66595f7f832f0e6c0e31d75c", MIEncoder.encoder.encode("4379892179"));

        assertNotNull(result);
        assertEquals(3, result.size());
    }
}