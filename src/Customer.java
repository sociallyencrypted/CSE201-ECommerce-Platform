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
        while (quantity > 0){
            cart.add(product);
            quantity--;
        }
    }
    public void removeFromCart(Product product){
        cart.remove(product);
    }
    public void addToCart(Deal deal, int quantity){
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
        for (Product product : cart) {
            this.removeFromCart(product);
        }
        for (Deal deal : dealsAddedToCart) {
            this.removeFromCart(deal);
        }
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
