package weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.api.ApiClient;
import weather.dto.CoordinatesDTO;
import weather.dto.WeatherDTO;

import java.io.IOException;
import java.util.ResourceBundle;

public class Weather {

    private final ResourceBundle bundle = ResourceBundle.getBundle("application");
    private final String WEATHER_URL = bundle.getString("url.weather");
    private final String WEATHER_API = bundle.getString("api.weather");
    private final ApiClient client;
    private final ObjectMapper mapper;

    public Weather(ApiClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public String getWeatherInfo(CoordinatesDTO coordinates) throws IOException {

        String url = WEATHER_URL
                + "?api_key=" + WEATHER_API
                + "&lat=" + coordinates.getLatitude()
                + "&lon=" + coordinates.getLongitude();


        return client.get(url);
    }


    public WeatherDTO getWeatherForecast(String weatherInfo) throws JsonProcessingException {
        JsonNode node = mapper.readTree(weatherInfo);
        JsonNode currentlyNode = node.at("/data/currently");

        float temperature = currentlyNode.path("temperature").floatValue();
        float feelsLike = currentlyNode.path("apparentTemperature").floatValue();
        float windSpeed = currentlyNode.path("windSpeed").floatValue();
        return new WeatherDTO(temperature, feelsLike, windSpeed);
    }


}
