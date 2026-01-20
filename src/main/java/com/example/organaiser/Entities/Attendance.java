package com.example.organaiser.Entities;

import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int ID;
    @Column
    private int memberId;
    @Column
    private int eventId;
    @Column
    private String status;

    public Attendance(int memberID, int eventID, String status){
        this.memberId = memberID;
        this.eventId = eventID;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
