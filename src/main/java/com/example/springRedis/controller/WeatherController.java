package com.example.springRedis.controller;

import com.example.springRedis.entity.Weather;
import com.example.springRedis.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping
    public ResponseEntity<Weather> saveWeather(@RequestBody Weather weather){
        Weather savedWeather = weatherService.postWeather(weather);
        return ResponseEntity.ok(savedWeather);
    }

    @GetMapping("/{city}")
    public ResponseEntity<String> getWeatherByCity(@PathVariable  String city){
        Weather weather = weatherService.getWeather(city);
        return new ResponseEntity<>(weather.getForecast(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getAllWeather(){
        List<Weather> weathers = weatherService.getWeathers();
        return new ResponseEntity<>(weathers, HttpStatus.OK);
    }

    @PutMapping("/{city}")
    public ResponseEntity<Weather> updateWeather(@PathVariable String city,
                                                 @RequestBody String forecast){
        Weather weather = weatherService.updateWeather(city, forecast);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeather(@PathVariable Long id){
        weatherService.deleteWeather(id);
        return ResponseEntity.ok("Weather deleted !");
    }

}
