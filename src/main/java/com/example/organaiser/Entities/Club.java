package com.example.organaiser.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int ID;
    @Column
    private String name;
    @Column
    private String clubDescriprion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubDescriprion() {
        return clubDescriprion;
    }

    public void setClubDescriprion(String clubDescriprion) {
        this.clubDescriprion = clubDescriprion;
    }
}
