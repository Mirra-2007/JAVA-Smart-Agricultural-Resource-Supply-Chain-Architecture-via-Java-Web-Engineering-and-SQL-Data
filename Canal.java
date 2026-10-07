import java.util.ArrayList;
import java.util.List;

public class Canal {
    String id;
    String name;
    String type;
    String waterStatus;
    double length;
    List<GeoPoint> coordinates = new ArrayList<>();

    public Canal(String id, String name, String type, String waterStatus) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.waterStatus = waterStatus;
    }

    public void addPoint(double lat, double lng) {
        coordinates.add(new GeoPoint(lat, lng));
    }
}
