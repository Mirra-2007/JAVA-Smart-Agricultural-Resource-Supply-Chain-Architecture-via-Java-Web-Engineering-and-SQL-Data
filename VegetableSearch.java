import java.util.ArrayList;
import java.util.Scanner;

class Vegetable {
    String name;
    String botanical;
    double basePrice; // per 0.5 kg
    String farmer;
    String land;

    public Vegetable(String name, String botanical, double basePrice, String farmer, String land) {
        this.name = name;
        this.botanical = botanical;
        this.basePrice = basePrice;
        this.farmer = farmer;
        this.land = land;
    }
}

public class VegetableSearch {

    // Linear Search Implementation updated to use ArrayList<Vegetable>
    public static void linearSearch(ArrayList<Vegetable> inventory, String query) {
        boolean found = false;
        String searchTerm = query.toLowerCase().trim();

        System.out.println("\n--- Search Results for: \"" + query + "\" ---");

        // Loop using inventory.size() and inventory.get(i)
        for (int i = 0; i < inventory.size(); i++) {
            Vegetable veg = inventory.get(i);
            
            // Linear search checks if query matches common name, botanical name, or farmer name
            if (veg.name.toLowerCase().contains(searchTerm) || 
                veg.botanical.toLowerCase().contains(searchTerm) || 
                veg.farmer.toLowerCase().contains(searchTerm)) {
                
                System.out.println("------------------------------------------------");
                System.out.println("Vegetable Name : " + veg.name);
                System.out.println("Botanical Name : " + veg.botanical);
                System.out.println("Base Price     : ₹" + veg.basePrice + " / 0.5 kg");
                System.out.println("Farmer         : " + veg.farmer);
                System.out.println("Land Location  : " + veg.land);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching vegetables found in the inventory.");
        }
        System.out.println("------------------------------------------------\n");
    }

    public static void main(String[] args) {
        // Array containing all vegetable items from the HTML database
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        
        vegetables.add(new Vegetable("Carrot", "Daucus carota", 25, "Farmer A", "Alpine Patch A"));
        vegetables.add(new Vegetable("Beetroot", "Beta vulgaris", 28, "Farmer B", "Plains Patch B"));
        vegetables.add(new Vegetable("Okra", "Abelmoschus esculentus", 20, "Farmer C", "Eco-Belt Patch C"));
        vegetables.add(new Vegetable("Eggplant", "Solanum melongena", 22, "Farmer D", "South Node D"));
        vegetables.add(new Vegetable("Potato", "Solanum tuberosum", 18, "Farmer E", "Valley Patch E"));
        vegetables.add(new Vegetable("Tomato", "Solanum lycopersicum", 24, "Farmer F", "Agri Sector F"));
        vegetables.add(new Vegetable("Onion", "Allium cepa", 19, "Farmer G", "Belt Patch G"));
        vegetables.add(new Vegetable("Garlic", "Allium sativum", 75, "Farmer H", "Valley Slope H"));
        vegetables.add(new Vegetable("Ginger", "Zingiber officinale", 65, "Farmer I", "Shared Boundary I"));
        vegetables.add(new Vegetable("Spinach", "Spinacia oleracea", 15, "Farmer J", "Wetland Patch J"));
        vegetables.add(new Vegetable("Cauliflower", "Brassica oleracea var. botrytis", 35, "Farmer K", "Polyhouse Patch K"));
        vegetables.add(new Vegetable("Cabbage", "Brassica oleracea var. capitata", 22, "Farmer K", "Polyhouse Patch K"));
        vegetables.add(new Vegetable("Broccoli", "Brassica oleracea var. italica", 80, "Farmer L", "High Tech Farm L"));
        vegetables.add(new Vegetable("Green Peas", "Pisum sativum", 40, "Farmer M", "Ridge Patch M"));
        vegetables.add(new Vegetable("Radish", "Raphanus sativus", 17, "Farmer N", "Fields Patch N"));
        vegetables.add(new Vegetable("Sweet Potato", "Ipomoea batatas", 26, "Farmer O", "Sand Basin O"));
        vegetables.add(new Vegetable("Green Bell Pepper", "Capsicum annuum", 38, "Farmer P", "Hydro Node P"));
        vegetables.add(new Vegetable("Yellow Bell Pepper", "Capsicum annuum", 70, "Farmer P", "Environment Block P"));
        vegetables.add(new Vegetable("Red Bell Pepper", "Capsicum annuum", 70, "Farmer P", "Environment Block P"));
        vegetables.add(new Vegetable("Bitter Gourd", "Momordica charantia", 24, "Farmer Q", "Riverbed Block Q"));
        vegetables.add(new Vegetable("Bottle Gourd", "Lagenaria siceraria", 18, "Farmer R", "Basin Patch R"));
        vegetables.add(new Vegetable("Ridge Gourd", "Luffa acutangula", 22, "Farmer R", "Basin Patch R"));
        vegetables.add(new Vegetable("Snake Gourd", "Trichosanthes cucumerina", 21, "Farmer S", "Orchard Patch S"));
        vegetables.add(new Vegetable("Pumpkin", "Cucurbita moschata", 16, "Farmer T", "West Plains T"));
        vegetables.add(new Vegetable("Ash Gourd", "Benincasa hispida", 19, "Farmer T", "Plain Node T"));
        vegetables.add(new Vegetable("Cucumber", "Cucumis sativus", 20, "Farmer U", "Green Belt U"));
        vegetables.add(new Vegetable("Ivy Gourd", "Coccinia grandis", 25, "Farmer V", "Agro Node V"));
        vegetables.add(new Vegetable("Moringa Drumstick", "Moringa oleifera", 45, "Farmer W", "Semi-Arid Zone W"));
        vegetables.add(new Vegetable("Elephant Foot Yam", "Amorphophallus paeoniifolius", 32, "Farmer X", "Regenerative Sector X"));
        vegetables.add(new Vegetable("Taro Root", "Colocasia esculenta", 30, "Farmer X", "Valley Edge X"));
        vegetables.add(new Vegetable("Green Chilli", "Capsicum frutescens", 26, "Farmer Y", "Drylands Patch Y"));
        vegetables.add(new Vegetable("Lemon", "Citrus limon", 40, "Farmer Z", "Orchards Patch Z"));
        vegetables.add(new Vegetable("Coriander Leaves", "Coriandrum sativum", 15, "Farmer A", "Outskirts Patch A"));
        vegetables.add(new Vegetable("Mint Leaves", "Mentha spicata", 14, "Farmer A", "Outskirts Patch A"));
        vegetables.add(new Vegetable("Curry Leaves", "Murraya koenigii", 12, "Farmer B", "Dry Basin B"));
        vegetables.add(new Vegetable("Spring Onion", "Allium fistulosum", 28, "Farmer C", "High Slopes C"));
        vegetables.add(new Vegetable("Button Mushroom", "Agaricus bisporus", 55, "Farmer D", "Dark Compound D"));
        vegetables.add(new Vegetable("Baby Corn", "Zea mays", 42, "Farmer E", "Fields Patch E"));
        vegetables.add(new Vegetable("Sweet Corn", "Zea mays convar. saccharata", 30, "Farmer E", "Matrix Block E"));
        vegetables.add(new Vegetable("Green Zucchini", "Cucurbita pepo", 48, "Farmer F", "Tech Roof F"));
        vegetables.add(new Vegetable("Yellow Zucchini", "Cucurbita pepo", 52, "Farmer F", "Tech Roof F"));
        vegetables.add(new Vegetable("Iceberg Lettuce", "Lactuca sativa", 60, "Farmer G", "Greenhouse Patch G"));
        vegetables.add(new Vegetable("Asparagus", "Asparagus officinalis", 140, "Farmer L", "Mountain Matrix L"));
        vegetables.add(new Vegetable("Celery", "Apium graveolens", 45, "Farmer G", "Cold Base G"));
        vegetables.add(new Vegetable("Turnip", "Brassica rapa subsp. rapa", 29, "Farmer H", "Slopes Patch H"));
        vegetables.add(new Vegetable("Knol Khol", "Brassica oleracea var. gongylodes", 32, "Farmer H", "Upper Rim H"));
        vegetables.add(new Vegetable("Raw Banana", "Musa paradisiaca", 15, "Farmer I", "Flood Sector I"));
        vegetables.add(new Vegetable("Banana Flower", "Musa paradisiaca flos", 25, "Farmer I", "Flood Sector I"));
        vegetables.add(new Vegetable("Banana Stem", "Musa paradisiaca caulis", 20, "Farmer J", "River Node J"));
        vegetables.add(new Vegetable("Broad Beans", "Vicia faba", 27, "Farmer K", "Hills North K"));
        vegetables.add(new Vegetable("Cluster Beans", "Cyamopsis tetragonoloba", 22, "Farmer K", "Base Cluster K"));
        vegetables.add(new Vegetable("Chayote", "Sechium edule", 24, "Farmer M", "Edge Valley M"));
        vegetables.add(new Vegetable("Red Spinach", "Amaranthus cruentus", 16, "Farmer J", "Red Patch J"));
        vegetables.add(new Vegetable("Carrot", "Daucus carota", 24, "Farmer B", "North Slope B"));
        vegetables.add(new Vegetable("Carrot", "Daucus carota", 26, "Farmer C", "Highlands C"));
        vegetables.add(new Vegetable("Tomato", "Solanum lycopersicum", 22, "Farmer A", "Silt Basin A"));
        vegetables.add(new Vegetable("Tomato", "Solanum lycopersicum", 25, "Farmer B", "Warm Fields B"));
        vegetables.add(new Vegetable("Tomato", "Solanum lycopersicum", 29, "Farmer C", "Organic Ridge C"));
        vegetables.add(new Vegetable("Potato", "Solanum tuberosum", 17, "Farmer F", "Sandy Loam F"));
        vegetables.add(new Vegetable("Potato", "Solanum tuberosum", 19, "Farmer G", "Terrace Plot G"));
        vegetables.add(new Vegetable("Onion", "Allium cepa", 18, "Farmer H", "Red Soil H"));
        vegetables.add(new Vegetable("Onion", "Allium cepa", 21, "Farmer I", "Alluvial Delta I"));
        vegetables.add(new Vegetable("Onion", "Allium cepa", 24, "Farmer J", "Highland Field J"));
        vegetables.add(new Vegetable("Eggplant", "Solanum melongena", 20, "Farmer A", "Southern Plain A"));
        vegetables.add(new Vegetable("Eggplant", "Solanum melongena", 24, "Farmer B", "Silt Meadow B"));
        vegetables.add(new Vegetable("Eggplant", "Solanum melongena", 26, "Farmer C", "Sunny Hillside C"));
        vegetables.add(new Vegetable("Beetroot", "Beta vulgaris", 27, "Farmer D", "Valley Bed D"));
        vegetables.add(new Vegetable("Beetroot", "Beta vulgaris", 30, "Farmer E", "Cold Ridge E"));
        vegetables.add(new Vegetable("Okra", "Abelmoschus esculentus", 21, "Farmer F", "Humid Lowland F"));
        vegetables.add(new Vegetable("Okra", "Abelmoschus esculentus", 23, "Farmer G", "Spring Basin G"));
        vegetables.add(new Vegetable("Spinach", "Spinacia oleracea", 14, "Farmer K", "Creek Meadow K"));
        vegetables.add(new Vegetable("Spinach", "Spinacia oleracea", 16, "Farmer L", "Mist Valley L"));
        vegetables.add(new Vegetable("Garlic", "Allium sativum", 72, "Farmer M", "Dry Slope M"));
        vegetables.add(new Vegetable("Ginger", "Zingiber officinale", 62, "Farmer N", "Tropical Corner N"));
        vegetables.add(new Vegetable("Cauliflower", "Brassica oleracea var. botrytis", 32, "Farmer O", "Silt Flat O"));
        vegetables.add(new Vegetable("Cabbage", "Brassica oleracea var. capitata", 20, "Farmer O", "Silt Flat O"));
        vegetables.add(new Vegetable("Green Peas", "Pisum sativum", 38, "Farmer P", "High Slope P"));
        vegetables.add(new Vegetable("Radish", "Raphanus sativus", 16, "Farmer Q", "River Flat Q"));
        vegetables.add(new Vegetable("Sweet Potato", "Ipomoea batatas", 24, "Farmer R", "Sandy Ridge R"));
        vegetables.add(new Vegetable("Bitter Gourd", "Momordica charantia", 22, "Farmer S", "Moist Valley S"));
        vegetables.add(new Vegetable("Bottle Gourd", "Lagenaria siceraria", 17, "Farmer T", "Wet Basin T"));
        vegetables.add(new Vegetable("Cucumber", "Cucumis sativus", 18, "Farmer V", "Lake Boundary V"));
        vegetables.add(new Vegetable("Ivy Gourd", "Coccinia grandis", 24, "Farmer W", "Loam Belt W"));
        vegetables.add(new Vegetable("Moringa Drumstick", "Moringa oleifera", 42, "Farmer X", "Arid Sector X"));
        vegetables.add(new Vegetable("Lemon", "Citrus limon", 38, "Farmer Y", "Dry Orchard Y"));
        vegetables.add(new Vegetable("Green Chilli", "Capsicum frutescens", 24, "Farmer Z", "Sunny Patch Z"));
        vegetables.add(new Vegetable("Coriander Leaves", "Coriandrum sativum", 13, "Farmer B", "Humid Strip B"));
        vegetables.add(new Vegetable("Mint Leaves", "Mentha spicata", 12, "Farmer B", "Humid Strip B"));
        vegetables.add(new Vegetable("Pumpkin", "Cucurbita moschata", 15, "Farmer D", "Clay Sector D"));
        vegetables.add(new Vegetable("Sweet Corn", "Zea mays convar. saccharata", 28, "Farmer F", "Basin Block F"));
        vegetables.add(new Vegetable("Green Zucchini", "Cucurbita pepo", 45, "Farmer H", "Roof Patch H"));
        vegetables.add(new Vegetable("Iceberg Lettuce", "Lactuca sativa", 55, "Farmer I", "Glass House I"));
        vegetables.add(new Vegetable("Raw Banana", "Musa paradisiaca", 14, "Farmer J", "Silt Slope J"));
        vegetables.add(new Vegetable("Broad Beans", "Vicia faba", 25, "Farmer L", "North Border L"));
        vegetables.add(new Vegetable("Chayote", "Sechium edule", 22, "Farmer N", "Low Valley N"));
        vegetables.add(new Vegetable("Red Spinach", "Amaranthus cruentus", 15, "Farmer P", "Red Soil P"));
        vegetables.add(new Vegetable("Carrot", "Daucus carota", 23, "Farmer D", "Sandy Flat D"));
        vegetables.add(new Vegetable("Carrot", "Daucus carota", 27, "Farmer E", "Loam Patch E"));
        vegetables.add(new Vegetable("Tomato", "Solanum lycopersicum", 23, "Farmer G", "Central Flat G"));
        vegetables.add(new Vegetable("Tomato", "Solanum lycopersicum", 26, "Farmer H", "Ridge Flat H"));
        vegetables.add(new Vegetable("Potato", "Solanum tuberosum", 20, "Farmer J", "High Slope J"));
        vegetables.add(new Vegetable("Potato", "Solanum tuberosum", 16, "Farmer L", "Basin Soil L"));
        vegetables.add(new Vegetable("Onion", "Allium cepa", 20, "Farmer L", "Silt Land L"));
        vegetables.add(new Vegetable("Onion", "Allium cepa", 22, "Farmer N", "Plain Field N"));
        vegetables.add(new Vegetable("Eggplant", "Solanum melongena", 21, "Farmer P", "Southern Field P"));
        vegetables.add(new Vegetable("Carrot", "Daucus carota", 25, "Farmer A", "Alpine Patch A"));
        vegetables.add(new Vegetable("Okra", "Abelmoschus esculentus", 19, "Farmer T", "East Plain T"));
        vegetables.add(new Vegetable("Okra", "Abelmoschus esculentus", 22, "Farmer V", "West Meadow V"));
        vegetables.add(new Vegetable("Spinach", "Spinacia oleracea", 13, "Farmer Z", "Creek Land Z"));
        vegetables.add(new Vegetable("Spinach", "Spinacia oleracea", 17, "Farmer A", "Valley Land A"));
        vegetables.add(new Vegetable("Cauliflower", "Brassica oleracea var. botrytis", 33, "Farmer C", "Cool Slopes C"));
        vegetables.add(new Vegetable("Cabbage", "Brassica oleracea var. capitata", 21, "Farmer D", "Cold Valley D"));
        vegetables.add(new Vegetable("Green Peas", "Pisum sativum", 39, "Farmer F", "Northern Slope F"));
        vegetables.add(new Vegetable("Radish", "Raphanus sativus", 15, "Farmer H", "Wetland Flat H"));
        vegetables.add(new Vegetable("Sweet Potato", "Ipomoea batatas", 25, "Farmer J", "Sandy Field J"));
        vegetables.add(new Vegetable("Bitter Gourd", "Momordica charantia", 23, "Farmer L", "Moist Block L"));
        vegetables.add(new Vegetable("Bottle Gourd", "Lagenaria siceraria", 16, "Farmer N", "Wet Field N"));
        vegetables.add(new Vegetable("Cucumber", "Cucumis sativus", 19, "Farmer P", "Riverbank Flat P"));
        vegetables.add(new Vegetable("Ivy Gourd", "Coccinia grandis", 23, "Farmer R", "Alluvial Strip R"));
        vegetables.add(new Vegetable("Moringa Drumstick", "Moringa oleifera", 43, "Farmer T", "Dry Meadow T"));
        vegetables.add(new Vegetable("Lemon", "Citrus limon", 39, "Farmer V", "Crest Orchard V"));
        vegetables.add(new Vegetable("Green Chilli", "Capsicum frutescens", 25, "Farmer X", "Sunny Corner X"));
        vegetables.add(new Vegetable("Pumpkin", "Cucurbita moschata", 14, "Farmer Z", "Clay Meadow Z"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search term (e.g. 'Carrot' or 'Farmer A'): ");
        String searchInput = scanner.nextLine();

        // Perform linear search on ArrayList
        linearSearch(vegetables, searchInput);

        scanner.close();
    }
}