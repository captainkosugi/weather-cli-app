package weather;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

public class Weather {

    private final ResourceBundle bundle = ResourceBundle.getBundle("application");
    private final String WEATHER_URL = bundle.getString("url.weather");
    private final String WEATHER_API = bundle.getString("api.weather");
    private final OkHttpClient client = new OkHttpClient();

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



}
