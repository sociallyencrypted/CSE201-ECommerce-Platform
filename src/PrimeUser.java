import java.util.ArrayList;

public class PrimeUser extends Customer implements Buyer{
    public PrimeUser(String username, String password) {
        super(username, password);
    }
    public PrimeUser(String username, String hashedPassword, float balance, ArrayList<Product> cart, ArrayList<Deal> dealsAddedToCart, ArrayList<Float> coupons) {
        super(username, hashedPassword);
        this.balance = balance;
        this.cart = cart;
        this.dealsAddedToCart = dealsAddedToCart;
        this.coupons = coupons;
    }
    @Override
    public void checkout() {

    }

    @Override
    public void calculateDiscountsOnCart() {

    }
}
