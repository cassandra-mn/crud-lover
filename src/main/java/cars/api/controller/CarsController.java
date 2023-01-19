package cars.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cars.api.dto.CarsDTO;
import cars.api.model.Car;
import cars.api.repository.CarsRepository;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    @Autowired
    private CarsRepository repository;

    @GetMapping
    public List<Car> getCars() {
        return repository.findAll();
    }

    @PostMapping
    public void createCar(@RequestBody CarsDTO request) {
        repository.save(new Car(request));
    }

    @PutMapping("/{id}")
    public void updataCar(@PathVariable Long id, @RequestBody CarsDTO request) {
        repository.findById(id).map(car -> {
            car.setModelo(request.modelo());
            car.setFabricante(request.fabricante());
            car.setDataFabricacao(request.dataFabricacao());
            car.setValor(request.valor());
            car.setAnoModelo(request.anoModelo());

            return repository.save(car);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}       
