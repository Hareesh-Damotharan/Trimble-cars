package com.trimble.cars.controller;

import com.trimble.cars.model.Car;
import com.trimble.cars.model.Lease;
import com.trimble.cars.service.CarService;
import com.trimble.cars.service.LeaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    private final CarService carService;
    private final LeaseService leaseService;

    public CarController(CarService carService, LeaseService leaseService) {
        this.carService = carService;
        this.leaseService = leaseService;
    }

    // Car owner registers a car
    @PostMapping("/car/register")
    public ResponseEntity<Car> registerCar(@RequestBody Car car) {
        Car newCar = carService.registerCar(car);
        return ResponseEntity.ok(newCar);
    }

    // Get car details by id
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        Car car = carService.getCar(id);
        return ResponseEntity.ok(car);
    }

    // Get lease history for a car
    @GetMapping("/car/{id}/leases")
    public ResponseEntity<List<Lease>> getCarLeaseHistory(@PathVariable Long id) {
        List<Lease> leases = leaseService.getLeasesForCar(id);
        return ResponseEntity.ok(leases);
    }

    // End customer starts a lease
    @PostMapping("/lease/start")
    public ResponseEntity<Lease> startLease(@RequestParam Long carId, @RequestParam String leasedBy) {
        Lease lease = leaseService.startLease(carId, leasedBy);
        return ResponseEntity.ok(lease);
    }

    // Admin: Get all cars
    @GetMapping("/admin/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    // Admin: End a lease
    @PostMapping("/admin/lease/end")
    public ResponseEntity<Lease> endLease(@RequestParam Long leaseId) {
        Lease lease = leaseService.endLease(leaseId);
        return ResponseEntity.ok(lease);
    }

    // Admin: Get leases for a customer
    @GetMapping("/admin/leases")
    public ResponseEntity<List<Lease>> getCustomerLeases(@RequestParam String leasedBy) {
        List<Lease> leases = leaseService.getLeasesForCustomer(leasedBy);
        return ResponseEntity.ok(leases);
    }
}
