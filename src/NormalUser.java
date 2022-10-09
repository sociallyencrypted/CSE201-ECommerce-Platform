import java.util.ArrayList;

public class NormalUser extends Customer implements Buyer{
    public NormalUser(String username, String password) {
        super(username, password);
    }

    public NormalUser(String username, String hashedPassword, float balance, ArrayList<Product> cart, ArrayList<Deal> dealsAddedToCart, ArrayList<Float> coupons) {
        super(username, hashedPassword);
        this.balance = balance;
        this.cart = cart;
        this.dealsAddedToCart = dealsAddedToCart;
        this.coupons = coupons;
    }
}
