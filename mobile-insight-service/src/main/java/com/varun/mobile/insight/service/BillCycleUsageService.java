package com.varun.mobile.insight.service;

import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillCycleUsageService {

    List<BillingCycle> getBillingCycleHistory(String userId, String mdn, int page, int size) throws BillingHistoryException;

    List<DailyUsage> getCurrentCycleUsage(String userId, String mdn, int page, int size) throws CycleUsageException;

}