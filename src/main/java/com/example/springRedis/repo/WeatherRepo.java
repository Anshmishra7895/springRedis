package com.example.springRedis.repo;

import com.example.springRedis.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepo extends JpaRepository<Weather, Long> {

    Weather findByCity(String city);

}
