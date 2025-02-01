package weather;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Coordinates coordinates = new Coordinates();
        String location = coordinates.getLocationInfo("Moscow, Russia");
        System.out.println(coordinates.getCoordinates(location));
    }
}