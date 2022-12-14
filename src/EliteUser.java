import java.util.ArrayList;

public class EliteUser extends Customer implements Buyer{
    public EliteUser(String username, String password) {
        super(username, password);
    }
    public EliteUser(String username, String hashedPassword, float balance, ArrayList<Product> cart, ArrayList<Deal> dealsAddedToCart, ArrayList<Float> coupons) {
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
            System.out.println("Your order will be delivered in 2 days.");
        }
        int random = (int) (Math.random() * 100);
        if (random < 10) {
            System.out.println("We at FLIPZON have decided to reward you for your support as an ELITE member. You have won a free gold coin!");
        }
        if (cartPrice >5000){
            receiveCoupons();
        }
    }

    @Override
    public float calculateDiscountsOnCart() {
        float cartPrice = 0;
        for (Product product : cart) {
            float couponToBeUsed = 0;
            for (float coupon : coupons) {
                if (coupon > couponToBeUsed){
                    couponToBeUsed = coupon;
                }
            }
            float appliedDiscount = maxOfThree(couponToBeUsed, product.getDiscountElite(), 10);
            if ((appliedDiscount == couponToBeUsed) && (appliedDiscount != 0) && (appliedDiscount != 10) && (appliedDiscount != product.getDiscountElite())){
                coupons.remove(couponToBeUsed);
            }
            cartPrice+= product.getPrice() - (appliedDiscount*product.getPrice()/100);
            System.out.println("Discount: " + appliedDiscount + "%  on " + product.getName());
        }
        for (Deal deal : dealsAddedToCart) {
            cartPrice+= deal.getPriceElite();
        }
        //delivery
        float deliveryCharges = (float) (100);
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

    @Override
    public void receiveCoupons() {
        int numberOfCoupons = (int) (Math.random() * 2 + 3);
        for (int i = 0; i < numberOfCoupons; i++) {
            int coupon = (int) (Math.random() * 11 + 5);
            coupons.add((float)coupon);
            System.out.println("You have received a coupon of " + coupon + "%");
        }
    }
}
