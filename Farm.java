import java.util.ArrayList;
import java.util.List;

public class Farm {
    String farmerId;
    String farmerName;
    String farmId;
    String location;
    double area;
    List<GeoPoint> boundary = new ArrayList<>();
    List<FarmMarker> markers = new ArrayList<>();
    List<Canal> canals = new ArrayList<>();

    public Farm(String farmerId, String farmerName, String farmId, String location, double area) {
        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.farmId = farmId;
        this.location = location;
        this.area = area;
    }

    public void addBoundaryPoint(double lat, double lng) {
        boundary.add(new GeoPoint(lat, lng));
    }

    public void addMarker(String type, String label, double lat, double lng) {
        markers.add(new FarmMarker(type, label, lat, lng));
    }

    public void addCanal(Canal canal) {
        canal.length = lengthKm(canal.coordinates);
        canals.add(canal);
    }

    private static double lengthKm(List<GeoPoint> points) {
        double total = 0;
        for (int i = 1; i < points.size(); i++) {
            total += haversineKm(points.get(i - 1), points.get(i));
        }
        return Math.round(total * 100.0) / 100.0;
    }

    private static double haversineKm(GeoPoint a, GeoPoint b) {
        double r = 6371.0;
        double dLat = Math.toRadians(b.lat - a.lat);
        double dLng = Math.toRadians(b.lng - a.lng);
        double h = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(a.lat)) * Math.cos(Math.toRadians(b.lat))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        return 2 * r * Math.asin(Math.sqrt(h));
    }
}
