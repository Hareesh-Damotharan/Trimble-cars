package com.trimble.cars.model;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String owner;   // Car owner or registrant
    private String model;
    private String status;  // "Ideal", "On Lease", "On Service"

    public Car() { }

    public Car(String owner, String model) {
        this.owner = owner;
        this.model = model;
        this.status = "Ideal"; // Default status when a car is registered
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
