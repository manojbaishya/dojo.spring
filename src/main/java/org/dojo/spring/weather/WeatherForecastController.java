package org.dojo.spring.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
public class WeatherForecastController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

    private static final String[] SUMMARIES = {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild",
            "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
    };

    private static final Random random = new Random();

    @GetMapping(value = "/weatherforecast")
    public List<WeatherForecast> getWeatherForecast() {
        logger.info("Requesting weather forecast.");

        return IntStream.rangeClosed(1, 5).mapToObj(index -> new WeatherForecast(LocalDate.now().plusDays(index),
                    random.nextInt(75) - 20,
                                SUMMARIES[random.nextInt(SUMMARIES.length)])).toList();
    }
}
