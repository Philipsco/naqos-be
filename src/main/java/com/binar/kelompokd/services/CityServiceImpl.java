package com.binar.kelompokd.services;

import com.binar.kelompokd.models.entity.City;
import com.binar.kelompokd.repos.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Integer id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City update(Integer id, City city) {
        City city1 = cityRepository.findById(id).get();

        city1.setCity(city.getCity());

        return cityRepository.save(city1);
    }

    @Override
    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
