package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private String temperature;
    private String humidity;
    private String windSpeed;

//    // Getters and Setters
//    public String getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(String temperature) {
//        this.temperature = temperature;
//    }
//
//    public String getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(String humidity) {
//        this.humidity = humidity;
//    }
//
//    public String getWindSpeed() {
//        return windSpeed;
//    }
//
//    public void setWindSpeed(String windSpeed) {
//        this.windSpeed = windSpeed;
//    }
}