package com.trimble.cars.service;

import com.trimble.cars.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Test
    public void testRegisterCar() {
        Car car = new Car("Alice", "Honda Civic");
        Car savedCar = carService.registerCar(car);
        assertNotNull(savedCar.getId());
        assertEquals("Honda Civic", savedCar.getModel());
        assertEquals("Ideal", savedCar.getStatus());
    }
}
