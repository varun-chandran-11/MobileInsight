package com.varun.mobile.insight.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

    @JsonView(Views.Internal.class)
    @DocumentReference(lookup = "{'_id' : ?#{#self.userId} }")
    private UserDetail userDetail;

    public BillingCycle(String mdn, Date startDate, Date endDate, String userId) {
        this.mdn = mdn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
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