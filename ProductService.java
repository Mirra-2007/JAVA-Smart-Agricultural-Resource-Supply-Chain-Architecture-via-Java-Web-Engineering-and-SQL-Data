public class ProductService {
    public void purchaseProduct(String productId) throws SoldOutException {
        // Logic: if id is TICKET_001, it eventually fails, else it always fails
        if (productId.equals("TICKET_001")) {
            // Simplified logic: throw error
            throw new SoldOutException("Stock is empty for " + productId);
        } else {
            throw new SoldOutException("Product " + productId + " not found.");
        }
    }
}