package weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Weather {

    private final ResourceBundle bundle = ResourceBundle.getBundle("application");
    private final String WEATHER_URL = bundle.getString("url.weather");
    private final String WEATHER_API = bundle.getString("api.weather");
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String getWeatherInfo(Map<String, Float> coordinates) throws IOException {

        Request request = new Request.Builder()
                .url(WEATHER_URL
                + "?api_key=" + WEATHER_API
                + "&lat=" + coordinates.get("longitude")
                + "&lon=" + coordinates.get("latitude"))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }

    }


    public Map<String, Float> getWeatherForecast(String weatherInfo) throws JsonProcessingException {
        Map<String, Float> forecast = new HashMap<>();

        JsonNode node = mapper.readTree(weatherInfo);
        String currentWeather = node.get("data")
                .get("currently")
                .get("temperature")
                .asText();

        String feelsLikeWeather = node.get("data")
                .get("currently")
                .get("apparentTemperature")
                .asText();

        String windSpeed = node.get("data")
                .get("currently")
                .get("windSpeed")
                .asText();

        forecast.put("currentWeather", Float.parseFloat(currentWeather));
        forecast.put("feelsLikeWeather", Float.parseFloat(feelsLikeWeather));
        forecast.put("windSpeed", Float.parseFloat(windSpeed));

        return forecast;
    }


}
