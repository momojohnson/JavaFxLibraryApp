package com.momo.datamodel;

import java.time.LocalDateTime;

public class Issue {

    private String isbn; // isbn number
    private String memberId; // member id
    private LocalDateTime dateTime; // Date and time book was issue
    private int renewalCount; // number of time book was renewed

    // return book isbn number
    public String getIsbn() {
        return isbn;
    }
    // sets book isbn number
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // return member id
    public String getMemberId() {
        return memberId;
    }

    // set members id
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    // return date and time book was issue
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // sets date and time of the book was issued
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // return renewal count.
    public int getRenewalCount() {
        return renewalCount;
    }

    // sets renewal count
    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }
}
