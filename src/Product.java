public class Product {
    private final String name;
    private final float price;
    private final int ID;
    private final Category category;
    private final String description;

    private float discountNormal;
    private float discountPrime;
    private float discountElite;
    private int quantity;

    public Product(String productID, String name, String description, float price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.ID = Integer.parseInt(productID.split("\\.")[1]);
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.discountNormal = 0;
        this.discountPrime = 0;
        this.discountElite = 0;
    }

    public float getPrice() {
        return price;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public Category getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public void setDiscount(float discountElite, float discountPrime, float discountNormal) {
        this.discountNormal = discountNormal;
        this.discountPrime = discountPrime;
        this.discountElite = discountElite;
    }
    public float getDiscountNormal() {
        return discountNormal;
    }
    public float getDiscountPrime() {
        return discountPrime;
    }
    public float getDiscountElite() {
        return discountElite;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return "Product: " + this.getName() + "\n"+ this.getDescription();
    }
}
