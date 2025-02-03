package weather.dto;

public class CoordinatesDTO {

    private final float longitude;
    private final float latitude;

    public CoordinatesDTO(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
