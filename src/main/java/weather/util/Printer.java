package weather.util;

import weather.dto.WeatherDTO;


public class Printer {

    public static void showResult(WeatherDTO forecast) {
        System.out.println(
                "\n" +
                "current: " + forecast.getTemperature() + "\n" +
                "feels like: " + forecast.getFeelsLike() +"\n" +
                "wind speed: " + forecast.getWindSpeed()
        );
    }

}
