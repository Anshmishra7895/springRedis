package com.example.springRedis.service;

import com.example.springRedis.entity.Weather;
import com.example.springRedis.repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;

    @Cacheable(value = "weather", key = "#city")
    public Weather getWeather(String city){
        return weatherRepo.findByCity(city);
    }

    public List<Weather> getWeathers(){
        return weatherRepo.findAll();
    }

    public Weather postWeather(Weather weather){
        return weatherRepo.save(weather);
    }

    @CacheEvict(value = "weather", key = "#city")
    public void deleteWeather(Long id){
        Weather weather = weatherRepo.findById(id).orElse(null);
        weatherRepo.delete(weather);
    }

    @CachePut(value = "weather", key = "#city")
    public Weather updateWeather(String city, String forecast){
        Weather oldWeather = weatherRepo.findByCity(city);
        oldWeather.setForecast(forecast);
        Weather savedWeather = weatherRepo.save(oldWeather);
        return savedWeather;
    }

}
