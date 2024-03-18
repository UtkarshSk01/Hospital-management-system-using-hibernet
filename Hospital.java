package com.hospitalmanagement;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hospitals")
public class Hospital {
    private int id;
    private String name;
    private String address;

    // Constructor
    public Hospital() {
        this.name = name;
        this.address = address;
    }


	// Getter and Setter methods for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
