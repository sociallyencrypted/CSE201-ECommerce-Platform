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
        float cartPrice = calculateDiscountsOnCart();
        if (cartPrice > this.balance){
            System.out.println("Not enough balance");
            return;
        }
        else{
            this.balance -= cartPrice;
            System.out.println("Your order is placed successfully. Details:");
            System.out.println("Total price: " + cartPrice);
            System.out.println("Remaining balance: " + this.balance);
            System.out.println("Products ordered: ");
            for (Product product : cart) {
                System.out.println(product);
                product.setQuantity(product.getQuantity() - 1);
            }
            for (Deal deal : dealsAddedToCart) {
                System.out.println(deal);
                for (Product product : deal.getProducts()) {
                    product.setQuantity(product.getQuantity() - 1);
                }
            }
            this.emptyCart();
            System.out.println("Your order will be delivered in 3-6 days.");
            if (cartPrice > 5000){
                receiveCoupons();
            }
        }
    }

    @Override
    public void receiveCoupons() {
        int numberOfCoupons = (int) (Math.random()*2 + 1);
        for (int i = 0; i < numberOfCoupons; i++) {
            int coupon = (int) (Math.random() * 11 + 5);
            coupons.add((float)coupon);
            System.out.println("You have received a coupon of " + coupon + "%");
        }
    }

    @Override
    public float calculateDiscountsOnCart() {
        float cartPrice = 0;
        float couponToBeUsed = 0;
        for (float coupon : coupons) {
            if (coupon > couponToBeUsed){
                couponToBeUsed = coupon;
            }
        }
        for (Product product : cart) {
            float appliedDiscount = maxOfThree(couponToBeUsed, product.getDiscountPrime(), 5);
            cartPrice+= product.getPrice() - (appliedDiscount*product.getPrice()/100);
            System.out.println("Discount: " + appliedDiscount + "%  on " + product.getName());
        }
        for (Deal deal : dealsAddedToCart) {
            cartPrice+= deal.getPricePrime();
        }
        //delivery
        float deliveryCharges = (float) (0.02*cartPrice + 100);
        System.out.println("Delivery charges: " + deliveryCharges);
        cartPrice+= deliveryCharges;
        System.out.println("Total price: " + cartPrice);
        return cartPrice;
    }

    private float maxOfThree(float couponToBeUsed, float discountPrime, int userDiscount) {
        float max = couponToBeUsed;
        if (discountPrime > max){
            max = discountPrime;
        }
        if (userDiscount > max){
            max = userDiscount;
        }
        return max;
    }
}
