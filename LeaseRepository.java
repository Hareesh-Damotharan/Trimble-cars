package com.trimble.cars.repository;

import com.trimble.cars.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
    List<Lease> findByLeasedBy(String leasedBy);
    List<Lease> findByCarId(Long carId);
}
