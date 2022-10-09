import java.util.ArrayList;

public class Customer extends User{
    protected float balance;
    protected ArrayList<Product> cart;
    protected ArrayList<Deal> dealsAddedToCart;
    protected ArrayList<Float> coupons;
    public Customer(String username, String password) {
        super(username, password);
        cart = new ArrayList<>();
        dealsAddedToCart = new ArrayList<>();
        coupons = new ArrayList<>();
    }
    public void addToCart(Product product, int quantity){
        if (product.getQuantity() < quantity){
            System.out.println("Not enough quantity");
            return;
        }
        while (quantity > 0){
            cart.add(product);
            quantity--;
        }
    }
    public void addToCart(Deal deal, int quantity){
        for (Product product : deal.getProducts()) {
            if (product.getQuantity() < quantity){
                System.out.println("Not enough quantity");
                return;
            }
        }
        while (quantity > 0){
            dealsAddedToCart.add(deal);
            quantity--;
        }
    }
    public void removeFromCart(Deal deal){
        dealsAddedToCart.remove(deal);
    }
    public void setBalance(float balance){
        this.balance = balance;
    }
    public float getBalance(){
        return balance;
    }
    public ArrayList<Product> getCart(){
        return cart;
    }
    public ArrayList<Deal> getDealsAddedToCart(){
        return dealsAddedToCart;
    }

    public ArrayList<Float> getCoupons() {
        return this.coupons;
    }

    public void emptyCart() {
        cart.clear();
        dealsAddedToCart.clear();
    }

    public String getStatus() {
        if (this instanceof NormalUser) {
            return "Normal";
        } else if (this instanceof PrimeUser) {
            return "Prime";
        } else if (this instanceof EliteUser) {
            return "Elite";
        }
           return null;
    }
}
