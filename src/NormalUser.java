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
            System.out.println("Your order will be delivered in 7-10 days.");
        }
    }

    @Override
    public float calculateDiscountsOnCart() {
        float cartPrice = 0;
        for (Product product : cart) {
           if (product.getDiscountNormal() != 0) {
               cartPrice+= product.getPrice() - (product.getDiscountNormal()*product.getPrice()/100);
               System.out.println("Discount: " + product.getDiscountNormal() + "%  on " + product.getName());
           }
              else {
                cartPrice+= product.getPrice();
              }
        }
        for (Deal deal : dealsAddedToCart) {
            cartPrice+= deal.getPriceNormal();
        }
        //delivery
        float deliveryCharges = (float) (0.05*cartPrice + 100);
        System.out.println("Delivery charges: " + deliveryCharges);
        cartPrice+= deliveryCharges;
        System.out.println("Total price: " + cartPrice);
        return cartPrice;
    }
    @Override
    public void receiveCoupons(){
        return;
    }
}
