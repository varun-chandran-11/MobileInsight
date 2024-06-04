package com.varun.mobile.insight.service.impl;

import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.repository.BillingCycleRepository;
import com.varun.mobile.insight.repository.DailyUsageRepository;
import com.varun.mobile.insight.util.MIEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BillCycleUsageServiceImplTest {

    private final String userId = "66595f7f832f0e6c0e31d75c";
    private final String mdn = "1234567891";
    private final int page = 0;
    private final int size = 5;
    @Mock
    private BillingCycleRepository billingCycleRepository;
    @Mock
    private DailyUsageRepository dailyUsageRepository;
    @InjectMocks
    private BillCycleUsageServiceImpl billingCycleUsageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBillingCycleHistory_Success() throws BillingHistoryException {
        PageRequest pageRequest = PageRequest.of(page, size);

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


        List<BillingCycle> billingCycles = Arrays.asList(billingCycle1, billingCycle2);
        Page<BillingCycle> billingCyclePage = new PageImpl<>(billingCycles);
        when(billingCycleRepository.findAll(userId, MIEncoder.getInstance().encode(mdn), pageRequest)).thenReturn(billingCyclePage);

        List<BillingCycle> result = billingCycleUsageService.getBillingCycleHistory(userId, mdn, page, size);

        assertNotNull(result);
        assertEquals(billingCyclePage.getContent(), result);
    }

    @Test
    void testGetBillingCycleHistoryThrowsException() {


        when(billingCycleRepository.findAll(userId, MIEncoder.getInstance().encode(mdn), PageRequest.of(page, size)))
                .thenThrow(new DataAccessException("...") {
                });

        assertThrows(BillingHistoryException.class, () -> {
            billingCycleUsageService.getBillingCycleHistory(userId, mdn, page, size);
        });
    }

    @Test
    public void testGetCurrentCycleUsage_CycleUsageException() {

        when(billingCycleRepository.findItemByUserIdAndMdnAndDate(anyString(), anyString(), any(Date.class))).thenReturn(Optional.empty());
        assertThrows(CycleUsageException.class, () -> billingCycleUsageService.getCurrentCycleUsage(userId, mdn, page, size));
    }
}