package weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import weather.api.ApiClient;
import weather.dto.CoordinatesDTO;
import weather.dto.WeatherDTO;
import weather.service.Coordinates;
import weather.service.Weather;
import weather.util.Printer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

       Scanner scanner = new Scanner(System.in);
       ApiClient client = new ApiClient();
       ObjectMapper mapper = new ObjectMapper();


       Coordinates coordinates = new Coordinates(client, mapper);
       Weather weather = new Weather(client, mapper);

       while (true) {
           try {
               System.out.print("Enter your location(example: Berlin/Germany) or 'exit': ");
               String location = scanner.nextLine();
               if (location.equalsIgnoreCase("exit")) {
                   System.out.println("Bye Bye!");
                   break;
               }

               String locationInfo = coordinates.getLocationInfo(location);
               CoordinatesDTO coordinatesDTO = coordinates.getCoordinates(locationInfo);

               String  weatherInfo = weather.getWeatherInfo(coordinatesDTO);
               WeatherDTO forecast = weather.getWeatherForecast(weatherInfo);

               Printer.showResult(forecast);
           } catch (IOException e) {
               System.err.println(e.getMessage());
           } catch (IllegalArgumentException e) {
               System.err.println(e.getMessage());
           }
       }

       scanner.close();

    }
}