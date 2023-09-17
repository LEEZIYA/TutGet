package nucleus.tutget.service.listing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.math.BigInteger;
import java.sql.Date;
import java.util.UUID;

@Entity
public class Listing {

    @Id
    private String id;
    private String acadLvl;
    private String acadSubject;
    private String postalCode;
    private Date postDate;
    private String dayOfWeek;
    private int frequency;
    private Date startDate;
    private int hourlyRate;
    private String description;
    private int[] selectedHour;
    private int[] selectedMin;
    private String selectedHourNum;

    public Listing(){

    }

    public Listing(String id, String acadLvl, String acadSubject, String postalCode, Date postDate, String dayOfWeek, int frequency, Date startDate, int hourlyRate, String description, int[] selectedHour, int[] selectedMin, String selectedHourNum) {
        this.id = id;
        this.acadLvl = acadLvl;
        this.acadSubject = acadSubject;
        this.postalCode = postalCode;
        this.postDate = postDate;
        this.dayOfWeek = dayOfWeek;
        this.frequency = frequency;
        this.startDate = startDate;
        this.hourlyRate = hourlyRate;
        this.description = description;
        this.selectedHour = selectedHour;
        this.selectedMin = selectedMin;
        this.selectedHourNum = selectedHourNum;
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

    public String getAcadSubject() {
        return acadSubject;
    }

    public void setAcadSubject(String acadSubject) {
        this.acadSubject = acadSubject;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getSelectedHour() {
        return selectedHour;
    }

    public void setSelectedHour(int[] selectedHour) {
        this.selectedHour = selectedHour;
    }

    public int[] getSelectedMin() {
        return selectedMin;
    }

    public void setSelectedMin(int[] selectedMin) {
        this.selectedMin = selectedMin;
    }

    public String getSelectedHourNum() {
        return selectedHourNum;
    }

    public void setSelectedHourNum(String selectedHourNum) {
        this.selectedHourNum = selectedHourNum;
    }

    @PrePersist
    public void generateId() {
        String uuid_string = UUID.randomUUID().toString().replaceAll("-","");
        BigInteger big = new BigInteger(uuid_string, 16);
        String same_uuid_shorter_string = big.toString(36);
        id = same_uuid_shorter_string ;
    }
}
