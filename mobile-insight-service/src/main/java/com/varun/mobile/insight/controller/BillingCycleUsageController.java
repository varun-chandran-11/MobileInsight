package com.varun.mobile.insight.controller;

import com.varun.mobile.insight.repository.BillingCycleRepository;
import com.varun.mobile.insight.repository.DailyUsageRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("billing-cycle")
public class BillingCycleUsageController {

    Logger logger = Logger.getLogger(BillingCycleUsageController.class.getName());

    private final BillingCycleRepository cycleRepository;
    private final DailyUsageRepository usageRepository;

    //using constructor injection for testability and maintainability
    public BillingCycleUsageController(BillingCycleRepository cycleRepository, DailyUsageRepository usageRepository) {
        this.cycleRepository = cycleRepository;
        this.usageRepository = usageRepository;
    }





}
