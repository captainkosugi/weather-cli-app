package weather;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Weather weather = new Weather();
        Coordinates coordinates = new Coordinates();
        String location = coordinates.getLocationInfo("Berlin, Germany");
        Map<String, Float> info = coordinates.getCoordinates(location);
        String w = weather.getWeatherInfo(info);
        Map<String, Float> forecats = weather.getWeatherForecast(w);
        Printer.showResult(forecats);
    }
}