package com.example.uroos.shopeat.activities.profile;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserProfile {

    private String userAge = "";
    private String userEmail = "";
    private String userName = "";
    private String udid = "";

    public UserProfile(){
    }


    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userEmail", userEmail);                     //0
        result.put("userAge", userAge);                             //1
        result.put("userName", userName);                         //2
        result.put("createdAt", ServerValue.TIMESTAMP);              //3
        result.put("udid", udid);                                //4
        return result;
    }

}