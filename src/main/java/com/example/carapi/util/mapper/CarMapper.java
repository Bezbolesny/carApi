package com.example.carapi.util.mapper;

import com.example.carapi.dto.CarDto;
import com.example.carapi.model.CarModel;

public class CarMapper {

    public static CarModel toCarModel(CarDto carDto){
        CarModel carModel = new CarModel();
        carModel.setMark(carDto.getMark());
        carModel.setModel(carDto.getModel());
        carModel.setColor(carDto.getColor());
        return carModel;
    }

    public static CarDto toCarDto(CarModel carModel){
        CarDto carDto = new CarDto();
        carDto.setMark(carDto.getMark());
        carDto.setModel(carDto.getModel());
        carDto.setColor(carDto.getColor());
        return carDto;
    }

}
