package nucleus.tutget.service.profile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.math.BigInteger;
import java.sql.Date;
import java.util.UUID;

@Entity
public class Profile {
    @Id
    private String id;
    private String acadLvl;
    private String userID;
    private String password;
    private String userType;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String billingAddress;


    public Boolean getAuthenticateStatus() {
        return authenticateStatus;
    }

    public void setAuthenticateStatus(Boolean authenticateStatus) {
        this.authenticateStatus = authenticateStatus;
    }

    private Boolean authenticateStatus;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    private String postalCode;
    private String description;
    //profile pic

    public Profile() {
        this.authenticateStatus = false;
    }

    public Profile(String id, String acadLvl, String userID, String password, String userType, String firstName, String lastName, String phoneNumber, String billingAddress, String description) {
        this.id = id;
        this.acadLvl = acadLvl;
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.description = description;
        this.authenticateStatus = false;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcadLvl() {
        return acadLvl;
    }

    public void setAcadLvl(String acadLvl) {
        this.acadLvl = acadLvl;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @PrePersist
    public void generateId() {
        String uuid_string = UUID.randomUUID().toString().replaceAll("-","");
        BigInteger big = new BigInteger(uuid_string, 16);
        String same_uuid_shorter_string = big.toString(36);
        id = same_uuid_shorter_string ;
    }





}