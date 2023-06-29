package com.example.carapi.controller;

import com.example.carapi.dto.CarDto;
import com.example.carapi.model.CarModel;
import com.example.carapi.service.CarService;
import com.example.carapi.util.errors.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarModel>> getAllCars() {
        try {
            List<CarModel> cars = carService.getAllCars();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
        try{
            CarModel carModel = carService.getCarById(id);
            return ResponseEntity.ok(carModel);
        } catch (CarNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
        Optional<CarModel> first = carService.getAllCars().stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            return ResponseEntity.ok(first.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<CarModel>> getCarsByColor(@PathVariable String color) {
        try {
            List<CarModel> cars = carService.getCarsByColor(color);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PatchMapping("/{id}/color")
//    public ResponseEntity<CarModel> updateByColor(@PathVariable Long id, @RequestParam String color){
//        try {
//            CarModel updateCar = carService.updateCarColor(id, color);
//            return ResponseEntity.ok(updateCar);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PatchMapping("/{id}/color")
    public ResponseEntity<CarModel> updateByColor(@PathVariable Long id, CarDto carDto) {
        try {
            CarModel updateCar = carService.updateCarColor(id, carDto);
            return ResponseEntity.ok(updateCar);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        try {
            CarDto addedCarDto = carService.addCar(carDto);
            return ResponseEntity.ok(addedCarDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModel> updateCar(@RequestBody CarModel car) {
        try {
            CarModel updateCar = carService.updateCar(car);
            return ResponseEntity.ok(updateCar);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        try {
            carService.deleteCar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}


