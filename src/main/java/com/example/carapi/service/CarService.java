package com.example.carapi.service;

import com.example.carapi.dto.CarDto;
import com.example.carapi.model.CarModel;
import com.example.carapi.repository.CarRepository;
import com.example.carapi.util.errors.CarNotFoundException;
import com.example.carapi.util.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarModel> getAllCars() {
        return carRepository.findAll();
    }

    public CarModel getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

//    public Optional<CarModel> getCarById(Long id) {
//        return carRepository.findById(id);
//    }

    public List<CarModel> getCarsByColor(String color) {
        return carRepository.listCarsByColor(color);
    }

//    public CarModel updateCarColor(Long id, String color){
//        CarModel car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));
//        car.setColor(color);
//        return carRepository.save(car);
//    }

    public CarModel updateCarColor(Long id, CarDto carDto){
        CarModel car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));
        car.setColor(carDto.getColor());
        return carRepository.save(car);
    }

    public CarDto addCar(CarDto carDto) {
        CarModel carModel = CarMapper.toCarModel(carDto);
        CarModel addedCar = carRepository.save(carModel);
        return CarMapper.toCarDto(addedCar);
    }

    public CarModel updateCar(CarModel car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
