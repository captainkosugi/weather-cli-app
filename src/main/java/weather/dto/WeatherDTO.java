package weather.dto;

public class WeatherDTO {

    private final float temperature;
    private final float feelsLike;
    private final float windSpeed;

    public WeatherDTO(float temperature, float feelsLike, float windSpeed) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.windSpeed = windSpeed;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getWindSpeed() {
        return windSpeed;
    }
}
