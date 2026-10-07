public class MainApplication {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        System.out.println("--- Transaction 1 ---");
        try {
            productService.purchaseProduct("TICKET_001");
        } catch (SoldOutException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Transaction 4 (Should fail) ---");
        try {
            productService.purchaseProduct("TICKET_001");
        } catch (SoldOutException e) {
            System.err.println("Redirecting User: [ " + e.getMessage() + " ] Please join the waitlist.");
        }

        System.out.println("\n--- Transaction 5 (Should fail) ---");
        try {
            productService.purchaseProduct("TICKET_002");
        } catch (SoldOutException e) {
            System.err.println("Redirecting User: [ " + e.getMessage() + " ] Notify me when restocked.");
        }
    }
}