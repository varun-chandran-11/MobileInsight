package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.util.MIEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class BillingCycleRepositoryTest {

    private final String userId = "66595f7f832f0e6c0e31d75c";
    private final String mdn = "1234567891";
    @Autowired
    private BillingCycleRepository billingCycleRepository;

    @BeforeEach
    public void setUp() {

        //billingCycleRepository.deleteAll();
    }

    @Test
    public void testFindItemByUserIdAndMdnAndDate() {

        Date currentDate = new Date();

        /*BillingCycle billingCycle = new BillingCycle();
        billingCycle.setUserId(userId);
        billingCycle.setMdn(mdn);
        billingCycle.setStartDate(new Date(currentDate.getTime() - 1000000));
        billingCycle.setEndDate(new Date(currentDate.getTime() + 1000000));

        billingCycleRepository.save(billingCycle);*/

        Optional<BillingCycle> result = billingCycleRepository.findItemByUserIdAndMdnAndDate(userId, MIEncoder.getInstance().encode(mdn), currentDate);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
        assertEquals(mdn, result.get().getMdn());
    }

    @Test
    public void testFindAll() {
        /*
        BillingCycle billingCycle1 = new BillingCycle();
        billingCycle1.setUserId(userId);
        billingCycle1.setMdn(mdn);
        billingCycle1.setStartDate(new Date(System.currentTimeMillis() - 2000000));
        billingCycle1.setEndDate(new Date(System.currentTimeMillis() - 1000000));

        BillingCycle billingCycle2 = new BillingCycle();
        billingCycle2.setUserId(userId);
        billingCycle2.setMdn(mdn);
        billingCycle2.setStartDate(new Date(System.currentTimeMillis() - 1000000));
        billingCycle2.setEndDate(new Date(System.currentTimeMillis() + 1000000));

        billingCycleRepository.save(billingCycle1);
        billingCycleRepository.save(billingCycle2); */

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<BillingCycle> result = billingCycleRepository.findAll(userId, MIEncoder.getInstance().encode(mdn), pageRequest);
        List<BillingCycle> resultList = result.getContent();

        assertEquals(3, result.getTotalElements());
        assertEquals(3, result.getContent().size());

        resultList.forEach(entry -> {
            assertNull(entry.getUserId());
            assertNotNull(entry.getEndDate());
            assertNotNull(entry.getStartDate());
        });
    }
}