package com.varun.mobile.insight.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.util.MIEncoder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("daily_usage")
public class DailyUsage {

    //Primary key, auto generated by MongoDB
    @JsonView(Views.Public.class)
    private String _id;

    //Phone number associated with the usage
    @JsonView(Views.Public.class)
    private String mdn;

    //Customer who owns this phone number, foreign key to user collection
    @JsonView(Views.Public.class)
    private String userId;

    //ISODate("2023-10-25T00:00:00.000-0400")
    @JsonView(Views.Public.class)
    private Date usageDate;

    //Usage of that day from 10-25 00:00:00 EST to 10-25 23:59:59 EST
    @JsonView(Views.Public.class)
    private Double usedInMb;

    public DailyUsage(String mdn, String userId, Date usageDate, Double usedInMb) {
        this.mdn = MIEncoder.getInstance().encode(mdn);;
        this.userId = userId;
        this.usageDate = usageDate;
        this.usedInMb = usedInMb;
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
        this.mdn = MIEncoder.getInstance().encode(mdn);;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }

    public Double getUsedInMb() {
        return usedInMb;
    }

    public void setUsedInMb(Double usedInMb) {
        this.usedInMb = usedInMb;
    }

}