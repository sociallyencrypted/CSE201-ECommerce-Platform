import java.util.ArrayList;

public class Category {
    private final String name;
    private ArrayList<Product> products;
    private final int ID;
    public Category(int ID, String name) {
        this.ID = ID;
        this.name = name;
        products = new ArrayList<>();
    }
    public int getID() {
        return this.ID;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
