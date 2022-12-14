import java.util.ArrayList;

public class Deal {
    private final Product p1;
    private final Product p2;
    private final float combinedPriceNormal;
    private final float combinedPricePrime;
    private final float combinedPriceElite;

    public Deal(Product p1, Product p2, float combinedPriceNormal, float combinedPricePrime, float combinedPriceElite) {
        this.p1 = p1;
        this.p2 = p2;
        assert combinedPriceNormal <= combinedPricePrime && combinedPricePrime <= combinedPriceElite;
        assert combinedPriceNormal <= p1.getPrice() + p2.getPrice();
        this.combinedPriceNormal = combinedPriceNormal;
        this.combinedPricePrime = combinedPricePrime;
        this.combinedPriceElite = combinedPriceElite;
    }

    public String getID() {
        return p1.getCategory().getID() + "." + p1.getID() + "_" + p2.getCategory().getID() + "." + p2.getID();
    }

    public String toString() {
        return p1.getName() + " + " + p2.getName() + " for " + combinedPriceNormal + " (Normal), " + combinedPricePrime + " (Prime), " + combinedPriceElite + " (Elite)";
    }

    public float getPriceNormal() {
        return combinedPriceNormal;
    }
    public float getPricePrime() {
        return combinedPricePrime;
    }
    public float getPriceElite() {
        return combinedPriceElite;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        return products;
    }
}
