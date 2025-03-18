package com.trimble.cars.service;

import com.trimble.cars.model.Car;
import com.trimble.cars.model.Lease;
import com.trimble.cars.repository.LeaseRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaseService {
    private final LeaseRepository leaseRepo;
    private final CarService carService;
    private static final int MAX_ACTIVE_LEASES = 2; // Maximum active leases per customer

    public LeaseService(LeaseRepository leaseRepo, CarService carService) {
        this.leaseRepo = leaseRepo;
        this.carService = carService;
    }

    // Start a lease on a car
    public Lease startLease(Long carId, String leasedBy) {
        long activeLeases = leaseRepo.findByLeasedBy(leasedBy)
                .stream().filter(l -> l.getLeaseEnd() == null).count();
        if (activeLeases >= MAX_ACTIVE_LEASES) {
            throw new RuntimeException("Maximum active leases reached for user: " + leasedBy);
        }
        Car car = carService.getCar(carId);
        if (!"Ideal".equalsIgnoreCase(car.getStatus())) {
            throw new RuntimeException("Car is not available for lease.");
        }
        Lease lease = new Lease(car, leasedBy);
        leaseRepo.save(lease);
        carService.updateCarStatus(carId, "On Lease");
        return lease;
    }

    // End a lease
    public Lease endLease(Long leaseId) {
        Lease lease = leaseRepo.findById(leaseId)
                .orElseThrow(() -> new RuntimeException("Lease not found"));
        if (lease.getLeaseEnd() != null) {
            throw new RuntimeException("Lease already ended.");
        }
        lease.setLeaseEnd(LocalDateTime.now());
        leaseRepo.save(lease);
        carService.updateCarStatus(lease.getCar().getId(), "Ideal");
        return lease;
    }

    public List<Lease> getLeasesForCar(Long carId) {
        return leaseRepo.findByCarId(carId);
    }

    public List<Lease> getLeasesForCustomer(String leasedBy) {
        return leaseRepo.findByLeasedBy(leasedBy);
    }
}
