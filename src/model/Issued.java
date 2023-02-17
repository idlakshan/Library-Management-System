package model;

import java.util.ArrayList;

public class Issued {
    private String id;
    private String memId;
    private String issuedDate;
    private String issuedTime;
    private ArrayList<IssuedDetails> details;

    public Issued() {
    }

    public Issued(String id, String memId, String issuedDate, String issuedTime, ArrayList<IssuedDetails> details) {
        this.id = id;
        this.memId = memId;
        this.issuedDate = issuedDate;
        this.issuedTime = issuedTime;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }

    public ArrayList<IssuedDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<IssuedDetails> details) {
        this.details = details;
    }
}
