package com.varun.mobile.insight.service.impl;

import com.mongodb.MongoException;
import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.repository.BillingCycleRepository;
import com.varun.mobile.insight.repository.DailyUsageRepository;
import com.varun.mobile.insight.service.BillingCycleUsageService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.varun.mobile.insight.common.Constants.*;

@Service
public class BillingCycleUsageServiceImpl implements BillingCycleUsageService {

    private final BillingCycleRepository billingCycleRepository;
    private final DailyUsageRepository dailyUsageRepository;
    Logger logger = Logger.getLogger(BillingCycleUsageServiceImpl.class.getName());

    public BillingCycleUsageServiceImpl(BillingCycleRepository billingCycleRepository, DailyUsageRepository dailyUsageRepository) {
        this.billingCycleRepository = billingCycleRepository;
        this.dailyUsageRepository = dailyUsageRepository;
    }

    @Override
    public List<BillingCycle> getBillingCycleHistory(String userId, String mdn) throws BillingHistoryException {
        logger.log(Level.INFO, "In service layer getBillingCycleHistory method.");
        try {
            List<BillingCycle> resultList = billingCycleRepository.findAll(userId, mdn);
        } catch (DataAccessException e) {
            // Handle Spring Data access exceptions
            e.printStackTrace();
            throw new BillingHistoryException(MESSAGE_BILLING_DATA_FAIL, e);
        } catch (MongoException e) {
            // Handle MongoDB-specific exceptions
            e.printStackTrace();
            throw new BillingHistoryException(MESSAGE_BILLING_MONGO_FAIL, e);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            throw new BillingHistoryException(MESSAGE_UNEXPECTED_ERROR, e);
        }

        return List.of();
    }

    @Override
    public List<DailyUsage> getCurrentCycleUsage(String userId, String mdn) throws CycleUsageException {

        //get the billing cycle as per current date
        Date today = new Date();
        BillingCycle billingCycle;
        try {
            Optional<BillingCycle> currentCycle = billingCycleRepository.findItemByUserIdAndMdnAndDate(userId, mdn, today);
            if(currentCycle.isEmpty()) {
                throw new CycleUsageException("No current cycle found for user " + userId + " and mdn " + mdn);
            }

            billingCycle = currentCycle.get();
        } catch(Exception e) {
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_UNEXPECTED_ERROR, e);
        }

        //get the usage details with usedId and mdn between the billing cycle dates
        try {
            return dailyUsageRepository.findUsageByUserIdMdnAndDateRange(userId, mdn, billingCycle.getStartDate(), billingCycle.getEndDate());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_UNEXPECTED_ERROR, e);
        }
    }

}