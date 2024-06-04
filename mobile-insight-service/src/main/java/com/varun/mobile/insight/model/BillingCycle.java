package com.varun.mobile.insight.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.util.MIEncoder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("billing_cycle")
public class BillingCycle {

    //Primary key, auto generated by MongoDB
    @JsonView(Views.Public.class)
    private String _id;

    //Phone number of a customer
    @JsonView(Views.Internal.class)
    private String mdn;

    //Start date of a billing cycle
    @JsonView(Views.Public.class)
    private Date startDate;

    //End date of a billing cycle
    @JsonView(Views.Public.class)
    private Date endDate;

    //foreign key to the id of the user details collection
    @JsonView(Views.Internal.class)
    private String userId;

    public BillingCycle(String mdn, Date startDate, Date endDate, String userId) {
        this.mdn = MIEncoder.getInstance().encode(mdn);
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public BillingCycle() {
        //empty constructor
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMdn() {
        return MIEncoder.getInstance().decode(mdn);
    }

    public void setMdn(String mdn) {
        this.mdn = MIEncoder.getInstance().encode(mdn);
        ;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}