package org.dojo.spring.weather;

import java.time.LocalDate;

public class WeatherForecast {
    public WeatherForecast() { }
    public WeatherForecast(LocalDate date, int temperatureC, String summary) {
        this.date = date;
        this.temperatureC = temperatureC;
        this.summary = summary;
    }

    private LocalDate date;
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    private int temperatureC;
    public int getTemperatureC() { return temperatureC; }
    public void setTemperatureC(int temperatureC) { this.temperatureC = temperatureC; }
    public int getTemperatureF() { return 32 + (int) (temperatureC / 0.5556); }

    private String summary;
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
