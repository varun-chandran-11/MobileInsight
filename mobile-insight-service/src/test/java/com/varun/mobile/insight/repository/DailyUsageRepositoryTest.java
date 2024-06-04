package com.varun.mobile.insight.repository;

import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.util.MIEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class DailyUsageRepositoryTest {

    private final String userId = "66595f7f832f0e6c0e31d75c";
    private final String mdn = "1234567891";
    @Autowired
    private DailyUsageRepository dailyUsageRepository;

    @BeforeEach
    public void setUp() {
        // dailyUsageRepository.deleteAll();

        DailyUsage usage1 = new DailyUsage();
        usage1.setUserId(userId);
        usage1.setMdn(mdn);
        usage1.setUsedInMb(560.98);
        usage1.setUsageDate(new Date(1622505600000L)); // 1st June 2021
        //dailyUsageRepository.save(usage1);

        DailyUsage usage2 = new DailyUsage();
        usage2.setUserId(userId);
        usage2.setMdn(mdn);
        usage2.setUsedInMb(1023.87);
        usage2.setUsageDate(new Date(1625097600000L)); // 1st July 2021
        //dailyUsageRepository.save(usage2);
    }

    @Test
    public void testFindItemByUserIdAndMdnAndUsageDateBetween() {

        Pageable pageable = PageRequest.of(0, 10);
        Date fromDate = new Date(1622419200000L); // 31st May 2021
        Date toDate = new Date(1625184000000L); // 2nd July 2021

        Page<DailyUsage> result = dailyUsageRepository.findItemByUserIdAndMdnAndUsageDateBetween(userId, MIEncoder.getInstance().encode(mdn), fromDate, toDate, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(2);
    }

}