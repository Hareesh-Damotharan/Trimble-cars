package com.trimble.cars.service;

import com.trimble.cars.model.Car;
import com.trimble.cars.repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepo;

    public CarService(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    // Register a new car
    public Car registerCar(Car car) {
        car.setStatus("Ideal");
        return carRepo.save(car);
    }

    public Car getCar(Long id) {
        return carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    // Update the status of the car
    public Car updateCarStatus(Long carId, String status) {
        Car car = getCar(carId);
        car.setStatus(status);
        return carRepo.save(car);
    }
}
