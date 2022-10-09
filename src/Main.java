import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    private static final Application app = new Application();
    private static final Scanner sc = new Scanner(System.in);

    static {
        app.register("Beff Jezos", "2021066", true);
        app.register("Gill Bates", "2021066", false);
    }

    public static class MD5 {
        public static String getMd5(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(input.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                StringBuilder hashtext = new StringBuilder(no.toString(16));
                while (hashtext.length() < 32) {
                    hashtext.insert(0, "0");
                }
                return hashtext.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    WELCOME TO FLIPZON
                    1) Enter as Admin
                    2) Explore the Product Catalog
                    3) Show Available Deals
                    4) Enter as Customer
                    5) Exit the Application""");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("""
                        Dear Admin,
                        Please enter your username and password""");
                    String username = sc.nextLine();
                    String hashedPassword = MD5.getMd5(sc.nextLine());
                    Admin admin = (Admin) app.login(username, hashedPassword, true);
                    if (admin != null) {
                        adminMenu(admin);
                    } else {
                        System.out.println("Invalid Credentials");
                    }
                }
            case 2 -> exploreCatalog();
            case 3 -> showDeals();
            case 4 -> customerMenu();
            case 5 -> System.exit(0);
            default -> System.out.println("Invalid Choice");
            }
        }
    }

    private static void customerMenu() {
        while (true){
            System.out.println("""
                    1) Sign up
                    2) Log in
                    3) Back""");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 -> userSignUp();
                case 2 -> userLogin();
                case 3 -> {return;}
            }
        }
    }

    private static void userLogin() {
    }

    private static void userSignUp() {

    }

    private static void showDeals() {
        
    }

    private static void exploreCatalog() {
        
    }

    private static void adminMenu(Admin admin) {
        System.out.println("Welcome " + admin.getUsername() + "!!!!");
        while (true) {
            System.out.println("""
                    Please choose any one of the following actions:
                    1) Add category
                    2) Delete category
                    3) Add Product
                    4) Delete Product
                    5) Set Discount on Product
                    6) Add giveaway deal
                    7) Back
                    """);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1 -> {
                    System.out.println("Add category ID");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (app.getCategory(id) != null) {
                        System.out.println("Category already exists");
                        break;
                    }
                    System.out.println("Add name of the Category");
                    String name = sc.nextLine();
                    app.addCategory(new Category(id, name));
                    addProductMenu(id);
                }
                case 2 -> {
                    System.out.println("Enter category ID");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Category category = app.getCategory(id);
                    if (category == null) {
                        System.out.println("Category does not exist");
                        break;
                    }
                    app.deleteCategory(category);
                }
                case 3 -> addProductMenu(-1);
                case 4 -> {
                    System.out.println("Enter product ID");
                    String id = sc.nextLine();
                    Product product = app.getProduct(id);
                    if (product == null) {
                        System.out.println("Product does not exist");
                        break;
                    }
                    app.deleteProduct(product);
                }
                case 5 -> {
                    System.out.println("Enter the Product ID");
                    String id = sc.nextLine();
                    Product product = app.getProduct(id);
                    if (product == null) {
                        System.out.println("Product does not exist");
                        break;
                    }
                    System.out.println("Enter discount for Elite, Prime and Normal customers respectively (in % terms)");
                    int eliteDiscount = sc.nextInt();
                    int primeDiscount = sc.nextInt();
                    int normalDiscount = sc.nextInt();
                    sc.nextLine();
                    product.setDiscount(eliteDiscount, primeDiscount, normalDiscount);
                }
                case 6 -> {
                    System.out.println("Dear Admin give the Product IDs you want to combine and giveaway a deal for");
                    System.out.println("Enter the first Product ID :");
                    String id1 = sc.nextLine();
                    Product product1 = app.getProduct(id1);
                    if (product1 == null) {
                        System.out.println("Product does not exist");
                        break;
                    }
                    System.out.println("Enter the second Product ID :");
                    String id2 = sc.nextLine();
                    Product product2 = app.getProduct(id2);
                    if (product2 == null) {
                        System.out.println("Product does not exist");
                        break;
                    }
                    System.out.println("Enter the combined price of the two products for Elite, Prime and Normal customers respectively (must be less than the sum of the individual prices)");
                    int elitePrice = sc.nextInt();
                    int primePrice = sc.nextInt();
                    int normalPrice = sc.nextInt();
                    sc.nextLine();
                    Deal deal = new Deal(product1, product2, elitePrice, primePrice, normalPrice);
                    app.addDeal(deal);
                }
                case 7 -> {
                    return;
                }
            }
        }
    }

    private static void addProductMenu(int categoryID) {
        Category category;
        if (categoryID==-1){
            System.out.println("Enter category ID");
            int id = sc.nextInt();
            sc.nextLine();
            category = app.getCategory(id);
        }
        else {
            category = app.getCategory(categoryID);
        }
        if (category == null) {
            System.out.println("Category does not exist");
            return;
        }
        System.out.println("Add a Product:-");
        System.out.print("Product Name:");
        String name = sc.nextLine();
        System.out.print("Product ID:");
        // of the format categoryID.productID
        String productID = sc.nextLine();
        if (app.getProduct(productID) != null) {
            System.out.println("Product already exists");
            return;
        }
        else if (!productID.startsWith(category.getID() + ".")) {
            System.out.println("Product ID does not match the format. Format: categoryID.productID");
            return;
        }
        System.out.println("Product Details:");
        StringBuilder description= new StringBuilder();
        String descriptionLine = sc.nextLine();
        while (!description.toString().isBlank()) {
            description.append(descriptionLine).append("\n");
        }
        System.out.print("Product Price:");
        float price = sc.nextFloat();
        sc.nextLine();
        System.out.print("Product Quantity:");
        int quantity = sc.nextInt();
        sc.nextLine();
        Product product = new Product(productID, name, description.toString(), price, quantity, category);
        category.addProduct(product);
    }
}