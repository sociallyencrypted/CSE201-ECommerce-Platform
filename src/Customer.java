import java.util.ArrayList;

public class Customer extends User{
    protected float balance;
    protected ArrayList<Product> cart;
    protected ArrayList<Deal> dealsAddedToCart;
    protected ArrayList<Float> coupons;
    public Customer(String username, String password) {
        super(username, password);
    }
    public void addToCart(Product product){
        cart.add(product);
    }
    public void removeFromCart(Product product){
        cart.remove(product);
    }
    public void addToCart(Deal deal){
        dealsAddedToCart.add(deal);
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
    public void addCoupon(float coupon){
        coupons.add(coupon);
    }
    public ArrayList<Product> getCart(){
        return cart;
    }
    public ArrayList<Deal> getDealsAddedToCart(){
        return dealsAddedToCart;
    }
}
