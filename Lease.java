package com.trimble.cars.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    
    private String leasedBy;   // End customer identifier
    private LocalDateTime leaseStart;
    private LocalDateTime leaseEnd;  // Null if lease is active

    public Lease() { }

    public Lease(Car car, String leasedBy) {
        this.car = car;
        this.leasedBy = leasedBy;
        this.leaseStart = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
    public String getLeasedBy() { return leasedBy; }
    public void setLeasedBy(String leasedBy) { this.leasedBy = leasedBy; }
    public LocalDateTime getLeaseStart() { return leaseStart; }
    public void setLeaseStart(LocalDateTime leaseStart) { this.leaseStart = leaseStart; }
    public LocalDateTime getLeaseEnd() { return leaseEnd; }
    public void setLeaseEnd(LocalDateTime leaseEnd) { this.leaseEnd = leaseEnd; }
}
