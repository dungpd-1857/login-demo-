package com.example.loginjooto.data.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("private_account")
    private Boolean privateAccount;

    @SerializedName("language")
    private String language;

    @SerializedName("confirmed_email")
    private Boolean confirmedEmail;

    @SerializedName("status")
    private String status;

    @SerializedName("default_notification_time")
    private String defaultNotificationTime;

    @SerializedName("reminder_time")
    private Integer reminderTime;

    @SerializedName("privacy")
    private String privacy;

    @SerializedName("authentication_token")
    private String authenticationToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPrivateAccount() {
        return privateAccount;
    }

    public void setPrivateAccount(Boolean privateAccount) {
        this.privateAccount = privateAccount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getConfirmedEmail() {
        return confirmedEmail;
    }

    public void setConfirmedEmail(Boolean confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefaultNotificationTime() {
        return defaultNotificationTime;
    }

    public void setDefaultNotificationTime(String defaultNotificationTime) {
        this.defaultNotificationTime = defaultNotificationTime;
    }

    public Integer getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Integer reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
