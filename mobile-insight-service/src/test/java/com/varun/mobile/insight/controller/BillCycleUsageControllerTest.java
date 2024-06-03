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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BillCycleUsageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BillCycleUsageService billCycleUsageService;

    @InjectMocks
    private BillCycleUsageController billCycleUsageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(billCycleUsageController).build();
    }

    @Test
    public void testGetCycleHistory() throws Exception {
        BillingRequest request = new BillingRequest();
        request.setUserId("66595f7f832f0e6c0e31d75c");
        request.setMdn("4379892179");

        BillingCycle cycle1 = new BillingCycle();
        BillingCycle cycle2 = new BillingCycle();
        List<BillingCycle> billingCycles = Arrays.asList(cycle1, cycle2);

        when(billCycleUsageService.getBillingCycleHistory(anyString(), anyString())).thenReturn(billingCycles);

        mockMvc.perform(get("/billing-cycle/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mdn\":\"4379892179\",\"userId\":\"66595f7f832f0e6c0e31d75c\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCurrentCycleDailyUsage() throws Exception {
        BillingRequest request = new BillingRequest();
        request.setUserId("66595f7f832f0e6c0e31d75c");
        request.setMdn("4379892179");

        DailyUsage usage1 = new DailyUsage();
        usage1.setUsageDate(new Date());
        usage1.setUserId("66595f7f832f0e6c0e31d75c");
        usage1.setMdn("4379892179");
        usage1.setUsedInMb(500.43);
        DailyUsage usage2 = new DailyUsage();
        usage2.setUsageDate(new Date());
        usage2.setUserId("66595f7f832f0e6c0e31d75c");
        usage2.setMdn("4379892179");
        usage2.setUsedInMb(550.43);
        List<DailyUsage> dailyUsages = Arrays.asList(usage1, usage2);

        when(billCycleUsageService.getCurrentCycleUsage(anyString(), anyString())).thenReturn(dailyUsages);

        mockMvc.perform(get("/billing-cycle/daily-usage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mdn\":\"4379892179\",\"userId\":\"66595f7f832f0e6c0e31d75c\"}"))
                .andExpect(status().isOk());
    }
}