package com.varun.mobile.insight.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.service.BillingCycleUsageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("billing-cycle")
public class BillingCycleUsageController {

    private final BillingCycleUsageService billingCycleUsageService;
    Logger logger = Logger.getLogger(BillingCycleUsageController.class.getName());

    //using constructor injection for testability and maintainability
    public BillingCycleUsageController(BillingCycleUsageService billingCycleUsageService) {
        this.billingCycleUsageService = billingCycleUsageService;
    }

    @GetMapping("/history")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<BillingCycle>> getCycleHistory(@RequestParam String userId, @RequestParam String mdn) throws BillingHistoryException {
        logger.log(Level.INFO, "Inside billing cycle controller history method.");
        return ResponseEntity.ok(billingCycleUsageService.getBillingCycleHistory(userId, mdn));
    }

    @GetMapping("/daily-usage")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<DailyUsage>> getCurrentCycleDailyUsage(@RequestParam String mdn, @RequestParam String userId) throws CycleUsageException {
        logger.log(Level.INFO, "Inside billing cycle controller daily usage method.");
        return ResponseEntity.ok(billingCycleUsageService.getCurrentCycleUsage(userId, mdn));
    }

}