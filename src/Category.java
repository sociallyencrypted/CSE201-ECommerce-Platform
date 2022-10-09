import java.util.ArrayList;

public class Category {
    private final String name;
    private final ArrayList<Product> products = new ArrayList<>();
    private final int ID;
    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public int getID() {
        return this.ID;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return this.name;
    }
}
