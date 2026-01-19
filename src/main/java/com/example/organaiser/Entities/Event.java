package com.example.organaiser.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int ID;
    @Column
    private LocalDateTime dateAndTime;
    @Column
    private String name;
    @Column
    private String eventDescription;
    @Column
    private int clubID;
}
