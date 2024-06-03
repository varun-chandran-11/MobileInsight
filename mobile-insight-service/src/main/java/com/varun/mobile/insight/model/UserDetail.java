package com.varun.mobile.insight.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.varun.mobile.insight.common.Views;
import com.varun.mobile.insight.util.MIEncoder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_details")
public class UserDetail {

    //Primary key, auto generated by MongoDB
    @JsonView(Views.Public.class)
    private String _id;

    //First name of the customer
    @JsonView(Views.Public.class)
    private String firstName;

    //Last name of the customer
    @JsonView(Views.Public.class)
    private String lastName;

    //Email of the customer
    @JsonView(Views.Public.class)
    @Indexed(unique = true)
    private String email;

    //Used for the login to our system
    @JsonView(Views.Internal.class)
    private String password;

    public UserDetail(String firstName, String lastName, String email, String password) {
        this.firstName = MIEncoder.getInstance().encode(firstName);
        this.lastName = MIEncoder.getInstance().encode(lastName);
        this.email = MIEncoder.getInstance().encode(email);
        this.password = MIEncoder.getInstance().encode(password);
    }

    public UserDetail() {
        // empty constructor
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return MIEncoder.getInstance().decode(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = MIEncoder.getInstance().encode(firstName);
    }

    public String getLastName() {
        return MIEncoder.getInstance().decode(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = MIEncoder.getInstance().encode(lastName);
    }

    public String getEmail() {
        return MIEncoder.getInstance().decode(email);
    }

    public void setEmail(String email) {
        this.email = MIEncoder.getInstance().encode(email);
    }

    public String getPassword() {
        return MIEncoder.getInstance().decode(password);
    }

    public void setPassword(String password) {
        this.password = MIEncoder.getInstance().encode(password);
    }
}