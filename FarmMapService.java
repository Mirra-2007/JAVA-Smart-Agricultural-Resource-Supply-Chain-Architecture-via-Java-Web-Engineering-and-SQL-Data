import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FarmMapService {

    private final Map<String, Farm> demoFarms = new LinkedHashMap<>();

    public FarmMapService() {
        seedDemoFarms();
    }

    public Farm getFarmMap(String farmerId) {
        if (farmerId == null) {
            return null;
        }
        String key = farmerId.trim().toUpperCase();

        Farm farm = loadFromDatabase(key);
        if (farm != null) {
            return farm;
        }

        farm = demoFarms.get(key);
        if (farm != null) {
            return farm;
        }

        if (key.matches("FARMER_[A-Z]")) {
            return generateDemoFarm(key);
        }
        return null;
    }

    public List<Farm> listFarms() {
        List<Farm> farms = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            Farm farm = getFarmMap("FARMER_" + c);
            if (farm != null) {
                farms.add(farm);
            }
        }
        return farms;
    }

    private Farm loadFromDatabase(String farmerId) {
        return null;
    }

    private void seedDemoFarms() {
        demoFarms.put("FARMER_A", buildFarmerAFarm());
        demoFarms.put("FARMER_B", buildFarmerBFarm());
        demoFarms.put("FARMER_C", buildFarmerCFarm());
    }

    private Farm buildFarmerAFarm() {
        Farm farm = new Farm("FARMER_A", "Farmer A", "FARM-001",
                "Agricultural Sector A, Koyambedu Belt", 12.5);

        farm.addBoundaryPoint(13.0850, 80.2685);
        farm.addBoundaryPoint(13.0855, 80.2730);
        farm.addBoundaryPoint(13.0830, 80.2740);
        farm.addBoundaryPoint(13.0810, 80.2725);
        farm.addBoundaryPoint(13.0805, 80.2680);

        farm.addMarker("entrance", "Main Farm Entrance", 13.0827, 80.2681);
        farm.addMarker("waterSource", "Borewell Water Source", 13.0848, 80.2683);
        farm.addMarker("canalStart", "North Canal Intake", 13.0851, 80.2685);
        farm.addMarker("junction", "Junction J1 (North / East Canal)", 13.0854, 80.2732);
        farm.addMarker("junction", "Junction J2 (East / South Canal)", 13.0812, 80.2732);
        farm.addMarker("junction", "Junction J3 (South / West Canal)", 13.0805, 80.2682);

        Canal north = new Canal("CANAL-001", "North Irrigation Canal", "Primary", "Active");
        north.addPoint(13.0851, 80.2685);
        north.addPoint(13.0853, 80.2700);
        north.addPoint(13.0855, 80.2718);
        north.addPoint(13.0854, 80.2732);
        farm.addCanal(north);

        Canal east = new Canal("CANAL-002", "East Distribution Canal", "Secondary", "Active");
        east.addPoint(13.0854, 80.2732);
        east.addPoint(13.0840, 80.2737);
        east.addPoint(13.0828, 80.2740);
        east.addPoint(13.0812, 80.2732);
        farm.addCanal(east);

        Canal south = new Canal("CANAL-003", "South Field Channel", "Distribution", "Maintenance");
        south.addPoint(13.0812, 80.2732);
        south.addPoint(13.0808, 80.2715);
        south.addPoint(13.0806, 80.2695);
        south.addPoint(13.0805, 80.2682);
        farm.addCanal(south);

        Canal west = new Canal("CANAL-004", "West Feeder Line", "Secondary", "Active");
        west.addPoint(13.0851, 80.2685);
        west.addPoint(13.0835, 80.2682);
        west.addPoint(13.0820, 80.2680);
        west.addPoint(13.0805, 80.2682);
        farm.addCanal(west);

        return farm;
    }

    private Farm buildFarmerBFarm() {
        Farm farm = new Farm("FARMER_B", "Farmer B", "FARM-002",
                "Plains Patch B, Sriperumbudur Sector", 9.8);

        farm.addBoundaryPoint(12.9705, 79.9698);
        farm.addBoundaryPoint(12.9710, 79.9735);
        farm.addBoundaryPoint(12.9688, 79.9744);
        farm.addBoundaryPoint(12.9668, 79.9728);
        farm.addBoundaryPoint(12.9665, 79.9695);

        farm.addMarker("entrance", "Main Farm Entrance", 12.9684, 79.9696);
        farm.addMarker("waterSource", "Canal Intake Reservoir", 12.9703, 79.9697);
        farm.addMarker("canalStart", "North Main Canal Intake", 12.9706, 79.9698);
        farm.addMarker("junction", "Junction J1 (North / East Canal)", 12.9709, 79.9733);
        farm.addMarker("junction", "Junction J2 (East / South Canal)", 12.9675, 79.9737);

        Canal north = new Canal("CANAL-101", "North Main Canal", "Primary", "Active");
        north.addPoint(12.9706, 79.9698);
        north.addPoint(12.9708, 79.9715);
        north.addPoint(12.9709, 79.9733);
        farm.addCanal(north);

        Canal east = new Canal("CANAL-102", "East Lift Canal", "Secondary", "Active");
        east.addPoint(12.9709, 79.9733);
        east.addPoint(12.9698, 79.9740);
        east.addPoint(12.9686, 79.9744);
        east.addPoint(12.9675, 79.9737);
        farm.addCanal(east);

        Canal south = new Canal("CANAL-103", "South Drain Channel", "Field", "Reduced Flow");
        south.addPoint(12.9675, 79.9737);
        south.addPoint(12.9670, 79.9720);
        south.addPoint(12.9667, 79.9702);
        south.addPoint(12.9666, 79.9696);
        farm.addCanal(south);

        return farm;
    }

    private Farm buildFarmerCFarm() {
        Farm farm = new Farm("FARMER_C", "Farmer C", "FARM-003",
                "Eco-Belt Patch C, Tiruvallur Ridge", 15.2);

        farm.addBoundaryPoint(13.1488, 79.9092);
        farm.addBoundaryPoint(13.1494, 79.9135);
        farm.addBoundaryPoint(13.1465, 79.9148);
        farm.addBoundaryPoint(13.1444, 79.9128);
        farm.addBoundaryPoint(13.1442, 79.9090);

        farm.addMarker("entrance", "Main Farm Entrance", 13.1466, 79.9091);
        farm.addMarker("waterSource", "Hill Spring Water Source", 13.1486, 79.9091);
        farm.addMarker("canalStart", "North Ridge Canal Intake", 13.1489, 79.9093);
        farm.addMarker("junction", "Junction J1 (North / East Canal)", 13.1493, 79.9133);
        farm.addMarker("junction", "Junction J2 (East / South Canal)", 13.1450, 79.9144);

        Canal north = new Canal("CANAL-201", "North Ridge Canal", "Primary", "Active");
        north.addPoint(13.1489, 79.9093);
        north.addPoint(13.1492, 79.9112);
        north.addPoint(13.1493, 79.9133);
        farm.addCanal(north);

        Canal east = new Canal("CANAL-202", "East Reserve Canal", "Secondary", "Active");
        east.addPoint(13.1493, 79.9133);
        east.addPoint(13.1478, 79.9142);
        east.addPoint(13.1464, 79.9148);
        east.addPoint(13.1450, 79.9144);
        farm.addCanal(east);

        Canal south = new Canal("CANAL-203", "South Paddy Channel", "Distribution", "Active");
        south.addPoint(13.1450, 79.9144);
        south.addPoint(13.1446, 79.9125);
        south.addPoint(13.1443, 79.9105);
        south.addPoint(13.1442, 79.9092);
        farm.addCanal(south);

        return farm;
    }

    private Farm generateDemoFarm(String farmerId) {
        char letter = farmerId.charAt(farmerId.length() - 1);
        int idx = letter - 'A';
        String farmerName = "Farmer " + letter;

        double cl = 12.95 + (idx % 6) * 0.035;
        double cg = 80.10 + (idx / 6) * 0.045;

        Farm farm = new Farm(farmerId, farmerName, String.format("FARM-%03d", idx + 1),
                "Agricultural Sector " + letter, Math.round((8.0 + (idx % 9) * 1.3) * 10.0) / 10.0);

        farm.addBoundaryPoint(cl + 0.0025, cg - 0.0024);
        farm.addBoundaryPoint(cl + 0.0027, cg + 0.0025);
        farm.addBoundaryPoint(cl - 0.0003, cg + 0.0029);
        farm.addBoundaryPoint(cl - 0.0025, cg + 0.0011);
        farm.addBoundaryPoint(cl - 0.0024, cg - 0.0026);

        farm.addMarker("entrance", "Main Farm Entrance", cl - 0.0001, cg - 0.0025);
        farm.addMarker("waterSource", "Borewell Water Source", cl + 0.0024, cg - 0.0024);
        farm.addMarker("canalStart", "Primary Canal Intake", cl + 0.0025, cg - 0.0023);
        farm.addMarker("junction", "Junction J1 (North / East Canal)", cl + 0.0027, cg + 0.0025);
        farm.addMarker("junction", "Junction J2 (East / South Canal)", cl - 0.0004, cg + 0.0029);

        String[] statuses = {"Active", "Maintenance", "Reduced Flow"};

        Canal north = new Canal("CANAL-" + (300 + idx * 10 + 1),
                "North Ridge Canal", "Primary", statuses[idx % statuses.length]);
        north.addPoint(cl + 0.0025, cg - 0.0023);
        north.addPoint(cl + 0.0026, cg + 0.0001);
        north.addPoint(cl + 0.0027, cg + 0.0025);
        farm.addCanal(north);

        Canal east = new Canal("CANAL-" + (300 + idx * 10 + 2),
                "East Distribution Canal", "Secondary", statuses[(idx + 1) % statuses.length]);
        east.addPoint(cl + 0.0027, cg + 0.0025);
        east.addPoint(cl + 0.0015, cg + 0.0028);
        east.addPoint(cl + 0.0002, cg + 0.0029);
        east.addPoint(cl - 0.0004, cg + 0.0029);
        farm.addCanal(east);

        Canal south = new Canal("CANAL-" + (300 + idx * 10 + 3),
                "South Return Channel", "Distribution", statuses[(idx + 2) % statuses.length]);
        south.addPoint(cl - 0.0004, cg + 0.0029);
        south.addPoint(cl - 0.0012, cg + 0.0022);
        south.addPoint(cl - 0.0020, cg + 0.0016);
        south.addPoint(cl - 0.0024, cg + 0.0011);
        farm.addCanal(south);

        Canal west = new Canal("CANAL-" + (300 + idx * 10 + 4),
                "West Feeder Line", "Field", statuses[(idx + 3) % statuses.length]);
        west.addPoint(cl + 0.0025, cg - 0.0023);
        west.addPoint(cl + 0.0012, cg - 0.0025);
        west.addPoint(cl - 0.0005, cg - 0.0025);
        west.addPoint(cl - 0.0024, cg - 0.0026);
        farm.addCanal(west);

        return farm;
    }
}
