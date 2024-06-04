package com.varun.mobile.insight.controller;

import com.varun.mobile.insight.dto.BillingRequest;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.service.BillCycleUsageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillCycleUsageControllerTest {

    @Mock
    private BillCycleUsageService billCycleUsageService;

    @InjectMocks
    private BillCycleUsageController billCycleUsageController;

    private final String userId = "66595f7f832f0e6c0e31d75c";
    private final String mdn = "4379892179";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCycleHistory() throws Exception {
        BillingRequest request = new BillingRequest();
        request.setUserId(userId);
        request.setMdn(mdn);
        int page = 0;
        int size = 5;

        BillingCycle cycle1 = new BillingCycle();
        BillingCycle cycle2 = new BillingCycle();
        List<BillingCycle> billingCycles = Arrays.asList(cycle1, cycle2);

        when(billCycleUsageService.getBillingCycleHistory(request.getUserId(), request.getMdn(), page, size)).thenReturn(billingCycles);
        ResponseEntity<List<BillingCycle>> response = billCycleUsageController.getCycleHistory(request, page, size);
        assertEquals(billingCycles, response.getBody());
        verify(billCycleUsageService, times(1)).getBillingCycleHistory(request.getUserId(), request.getMdn(), page, size);

    }

    @Test
    public void testGetCurrentCycleDailyUsage() throws Exception {
        BillingRequest request = new BillingRequest();
        request.setUserId(userId);
        request.setMdn(mdn);
        int page = 0;
        int size = 5;

        DailyUsage usage1 = new DailyUsage();
        usage1.setUsageDate(new Date());
        usage1.setUserId(userId);
        usage1.setMdn(mdn);
        usage1.setUsedInMb(500.43);
        DailyUsage usage2 = new DailyUsage();
        usage2.setUsageDate(new Date());
        usage2.setUserId(userId);
        usage2.setMdn(mdn);
        usage2.setUsedInMb(550.43);
        List<DailyUsage> dailyUsages = Arrays.asList(usage1, usage2);

        when(billCycleUsageService.getCurrentCycleUsage(request.getUserId(), request.getMdn(), page, size)).thenReturn(dailyUsages);
        ResponseEntity<List<DailyUsage>> response = billCycleUsageController.getCurrentCycleDailyUsage(request, page, size);

        assertEquals(dailyUsages, response.getBody());
        verify(billCycleUsageService, times(1)).getCurrentCycleUsage(request.getUserId(), request.getMdn(), page, size);
    }
}