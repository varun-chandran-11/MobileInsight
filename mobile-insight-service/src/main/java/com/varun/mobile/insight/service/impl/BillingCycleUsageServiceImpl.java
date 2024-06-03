package com.varun.mobile.insight.service.impl;

import com.mongodb.MongoException;
import com.varun.mobile.insight.exception.BillingHistoryException;
import com.varun.mobile.insight.exception.CycleUsageException;
import com.varun.mobile.insight.model.BillingCycle;
import com.varun.mobile.insight.model.DailyUsage;
import com.varun.mobile.insight.repository.BillingCycleRepository;
import com.varun.mobile.insight.repository.DailyUsageRepository;
import com.varun.mobile.insight.service.BillingCycleUsageService;
import com.varun.mobile.insight.util.MIEncoder;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.varun.mobile.insight.common.StringConstants.*;

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
            return billingCycleRepository.findAll(userId, MIEncoder.getInstance().encode(mdn));
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
    }

    @Override
    public List<DailyUsage> getCurrentCycleUsage(String userId, String mdn) throws CycleUsageException {
        logger.log(Level.INFO, "In service layer getCurrentCycleUsage method.");
        //get the billing cycle as per current date
        Date today = new Date();
        BillingCycle billingCycle = getBillingCycle(userId, mdn, today);

        //get the usage details with usedId and mdn between the billing cycle dates
        return getDailyUsage(userId, mdn, billingCycle.getStartDate(), billingCycle.getEndDate());
    }

    /**
     *
     * @param userId - primary key of user detail table
     * @param mdn - phone number
     * @param date - date to filter
     * @return - Billing cycle as per the given arguments
     * @throws CycleUsageException
     */
    private BillingCycle getBillingCycle(String userId, String mdn, Date date) throws CycleUsageException {
        logger.log(Level.INFO, "In service layer getBillingCycle method.");
        try {
            logger.log(Level.INFO, "Calling repo to get current cycle.");
            Optional<BillingCycle> currentCycle = billingCycleRepository.findItemByUserIdAndMdnAndDate(userId, MIEncoder.getInstance().encode(mdn), date);
            if(currentCycle.isEmpty()) {
                throw new CycleUsageException("No current cycle found for user %s" + userId + " and mdn %s." + mdn);
            }
            logger.log(Level.INFO, "Successfully retrieved current cycle.");
            return currentCycle.get();
        } catch (DataAccessException e) {
            // Handle Spring Data access exceptions
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_BILLING_DATA_FAIL, e);
        } catch (MongoException e) {
            // Handle MongoDB-specific exceptions
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_BILLING_MONGO_FAIL, e);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_UNEXPECTED_ERROR, e);
        }
    }

    /**
     * This method calls the repo and get the details.
     * @param userId - primary key of user table
     * @param mdn - phone number
     * @param startDate - start date of cycle
     * @param endDate - end date of cycle
     * @return - daily usage list
     * @throws CycleUsageException
     */
    private List<DailyUsage> getDailyUsage(String userId, String mdn, Date startDate, Date endDate) throws CycleUsageException {
        logger.log(Level.INFO, "Inside getDailyUsage method of service layer.");
        try {
            logger.log(Level.INFO, "Calling repo to get usage details.");
            return dailyUsageRepository.findUsageByUserIdMdnAndDateRange(userId, MIEncoder.getInstance().encode(mdn), startDate, endDate);
        } catch (DataAccessException e) {
            // Handle Spring Data access exceptions
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_BILLING_DATA_FAIL, e);
        } catch (MongoException e) {
            // Handle MongoDB-specific exceptions
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_BILLING_MONGO_FAIL, e);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            throw new CycleUsageException(MESSAGE_UNEXPECTED_ERROR, e);
        }
    }

}