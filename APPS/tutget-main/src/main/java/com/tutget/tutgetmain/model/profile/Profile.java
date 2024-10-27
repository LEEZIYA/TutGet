package com.tutget.tutgetmain.model.profile;


public record Profile (
    String id,
    String acadLvl,
    String userID,
    String password,
    String userType,
    String firstName,
    String lastName,
    String phoneNumber,
    String billingAddress,
    String postalCode,
    String description,
    String authenticateStatus
) { }


//    private String id;
//    private String acadLvl;
//    private String userID;
//    private String password;
//    private String userType;
//    private String firstName;
//    private String lastName;
//    private String phoneNumber;
//    private String billingAddress;
//
//    private String postalCode;
//    private String description;
//    private Boolean authenticateStatus;
//
//
//    public Boolean getAuthenticateStatus() {
//        return authenticateStatus;
//    }
//
//    public void setAuthenticateStatus(Boolean authenticateStatus) {
//        this.authenticateStatus = authenticateStatus;
//    }
//
//
//
//
//    public Profile() {
//        this.authenticateStatus = false;
//    }
//
//    public Profile(String id, String acadLvl, String userID, String password, String userType, String firstName, String lastName, String phoneNumber, String billingAddress, String postalCode, String description) {
//        this.id = id;
//        this.acadLvl = acadLvl;
//        this.userID = userID;
//        this.password = password;
//        this.userType = userType;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.billingAddress = billingAddress;
//        this.postalCode = postalCode;
//        this.description = description;
//        this.authenticateStatus = false;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAcadLvl() {
//        return acadLvl;
//    }
//
//    public void setAcadLvl(String acadLvl) {
//        this.acadLvl = acadLvl;
//    }
//
//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUserType() {
//        return userType;
//    }
//
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getBillingAddress() {
//        return billingAddress;
//    }
//
//    public void setBillingAddress(String billingAddress) {
//        this.billingAddress = billingAddress;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    @Override
//    public String toString() {
//        return "Profile{" +
//          "id='" + id + '\'' +
//          ", acadLvl='" + acadLvl + '\'' +
//          ", userID='" + userID + '\'' +
//          ", password='" + password + '\'' +
//          ", userType='" + userType + '\'' +
//          ", firstName='" + firstName + '\'' +
//          ", lastName='" + lastName + '\'' +
//          ", phoneNumber='" + phoneNumber + '\'' +
//          ", billingAddress='" + billingAddress + '\'' +
//          ", postalCode='" + postalCode + '\'' +
//          ", description='" + description + '\'' +
//          ", authenticateStatus=" + authenticateStatus +
//          '}';
//    }
//}