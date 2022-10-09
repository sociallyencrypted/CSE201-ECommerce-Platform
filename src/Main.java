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

        }
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
                case 3 -> addProductMenu();
            }
        }
    }

    private static void addProductMenu() {
        System.out.println("Enter category ID");
        int id = sc.nextInt();
        sc.nextLine();
        Category category = app.getCategory(id);
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
        String description="";
        String descriptionLine = sc.nextLine();
        while (!description.isBlank()) {
            description += descriptionLine + "\n";
        }
        System.out.print("Product Price:");
        float price = sc.nextFloat();
        sc.nextLine();
        System.out.print("Product Quantity:");
        int quantity = sc.nextInt();
        sc.nextLine();
        Product product = new Product(productID, name, description, price, quantity, category);
        category.addProduct(product);
    }
}