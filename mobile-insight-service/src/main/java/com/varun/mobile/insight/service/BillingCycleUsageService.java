package com.varun.mobile.insight.service;

import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;

import java.util.List;

public interface BillingCycleUsageService {

    List<BillingCycle> getBillingCycleHistory(String userId, String mdn) throws BillingHistoryException;

    List<DailyUsage> getCurrentCycleUsage(String userId, String mdn) throws CycleUsageException;

}