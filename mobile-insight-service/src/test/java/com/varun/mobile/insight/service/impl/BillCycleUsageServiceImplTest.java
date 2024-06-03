package com.varun.mobile.insight.service.impl;

import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.repository.BillingCycleRepository;
import com.varun.mobile.insight.repository.DailyUsageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BillCycleUsageServiceImplTest {

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
        List<BillingCycle> billingCycles = Arrays.asList(new BillingCycle(), new BillingCycle());

        when(billingCycleRepository.findAll(anyString(), anyString())).thenReturn(billingCycles);

        List<BillingCycle> result = billingCycleUsageService.getBillingCycleHistory("66595f7f832f0e6c0e31d75c", "4379892179");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCurrentCycleUsage_Success() throws CycleUsageException {
        BillingCycle billingCycle = new BillingCycle();
        billingCycle.setStartDate(new Date());
        billingCycle.setEndDate(new Date());
        List<DailyUsage> dailyUsages = Arrays.asList(new DailyUsage(), new DailyUsage());

        when(billingCycleRepository.findItemByUserIdAndMdnAndDate(anyString(), anyString(), any(Date.class))).thenReturn(Optional.of(billingCycle));
        when(dailyUsageRepository.findUsageByUserIdMdnAndDateRange(anyString(), anyString(), any(Date.class), any(Date.class))).thenReturn(dailyUsages);

        List<DailyUsage> result = billingCycleUsageService.getCurrentCycleUsage("66595f7f832f0e6c0e31d75c", "4379892179");

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetCurrentCycleUsage_CycleUsageException() {
        when(billingCycleRepository.findItemByUserIdAndMdnAndDate(anyString(), anyString(), any(Date.class))).thenReturn(Optional.empty());

        assertThrows(CycleUsageException.class, () -> billingCycleUsageService.getCurrentCycleUsage("66595f7f832f0e6c0e31d75c", "4379892179"));
    }
}