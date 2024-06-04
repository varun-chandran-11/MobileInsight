package com.varun.mobile.insight.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.dto.BillingRequest;
import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.service.BillCycleUsageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("billing-cycle")
public class BillCycleUsageController {

    private final BillCycleUsageService billCycleUsageService;
    Logger logger = Logger.getLogger(BillCycleUsageController.class.getName());

    //using constructor injection for testability and maintainability
    public BillCycleUsageController(BillCycleUsageService billCycleUsageService) {
        this.billCycleUsageService = billCycleUsageService;
    }

    @GetMapping("/history")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<BillingCycle>> getCycleHistory(BillingRequest request,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size) throws BillingHistoryException {
        logger.log(Level.INFO, "Inside billing cycle controller history method.");
        return ResponseEntity.ok(billCycleUsageService.getBillingCycleHistory(request.getUserId(), request.getMdn(), page, size));
    }

    @GetMapping("/daily-usage")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<DailyUsage>> getCurrentCycleDailyUsage(BillingRequest request,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "5") int size) throws CycleUsageException {
        logger.log(Level.INFO, "Inside billing cycle controller daily usage method.");
        return ResponseEntity.ok(billCycleUsageService.getCurrentCycleUsage(request.getUserId(), request.getMdn(), page, size));
    }

}