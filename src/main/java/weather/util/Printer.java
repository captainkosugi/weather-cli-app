package weather;

import java.util.Map;

public class Printer {

    public static void showResult(Map<String, Float> forecast) {
        System.out.println(
                "current: " + forecast.get("currentWeather") + "\n" +
                "feels like: " + forecast.get("feelsLikeWeather") +"\n" +
                "wind speed: " + forecast.get("windSpeed")
        );
    }

}
