package cars.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cars.api.model.Car;

public interface CarsRepository extends JpaRepository<Car, Long> {
    
}
