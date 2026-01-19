package com.example.organaiser.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int ID;
    @Column
    private int memberID;
    @Column
    private int eventID;
    @Column
    private String status;

    public Attendance(int memberID, int eventID, String status){
        this.memberID = memberID;
        this.eventID = eventID;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
