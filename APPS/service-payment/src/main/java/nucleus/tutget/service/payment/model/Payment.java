package nucleus.tutget.service.payment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import javax.persistence.Table;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    private String transactionId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String address2;
    private String state;
    private String nameOnCard;
    private String expiration;
    private String country;
    private long creditCardNum;
    private int postal;
    private String paymentMethod;
    private int cvv;
    private String listingId;



    public Payment (){
        
    }

    public Payment(String transactionId, String firstName, String lastName, String email, String address, int postal, String paymentMethod, String address2, long creditCardNum, int cvv, String country, String state, String nameOnCard, String expiration, String listingId) {
        this.transactionId = transactionId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.postal = postal;
        this.paymentMethod = paymentMethod;
        this.address2 = address2;
        this.creditCardNum = creditCardNum;
        this.cvv = cvv;
        this.country = country;
        this.state = state;
        this.nameOnCard = nameOnCard;
        this.expiration = expiration;
        this.listingId = listingId;

    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
   
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
   
    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(long creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    @PrePersist
    public void generateTransactionId() {
        String uuid_string = UUID.randomUUID().toString().replaceAll("-","");
        BigInteger big = new BigInteger(uuid_string, 16);
        String same_uuid_shorter_string = big.toString(36);
        transactionId = same_uuid_shorter_string ;
    }
}

