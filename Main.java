class SoldOutException extends Exception {
    public SoldOutException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            int stock = 0;
            if (stock <= 0) {
                throw new SoldOutException("Item is out of stock!");
            }
        } catch (SoldOutException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Process finished.");
        }
    }
}