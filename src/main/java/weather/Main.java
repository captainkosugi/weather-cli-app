package weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();


        Coordinates coordinates = new Coordinates();
        JsonNode node = mapper.readTree(coordinates.getLocationInfo("New-York, USA"));
        System.out.println(node.get("response")
                .get("GeoObjectCollection")
                .get("featureMember")
                .get(0)
                .get("GeoObject")
                .get("Point")
                .get("pos").asText());
    }
}