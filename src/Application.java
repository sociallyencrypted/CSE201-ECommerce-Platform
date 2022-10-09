import java.util.ArrayList;

public class Application {
    private final ArrayList<Admin> admins = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Category> categories = new ArrayList<>();
    private final ArrayList<Deal> deals = new ArrayList<>();
    public void register(String username, String password, boolean isAdmin) {
        if (isAdmin) {
            admins.add(new Admin(username, password));
        } else {
            NormalUser normalUser = new NormalUser(username, password);
            normalUser.setBalance(1000);
            customers.add(normalUser);
        }
    }

    public User login(String username, String hashedPassword, boolean isAdmin) {
        if (isAdmin) {
            for (Admin admin : admins) {
                if (admin.getUsername().equals(username) && admin.getHashedPassword().equals(hashedPassword)) {
                    return admin;
                }
            }
        } else {
            for (Customer customer : customers) {
                if (customer.getUsername().equals(username) && customer.getHashedPassword().equals(hashedPassword)) {
                    return customer;
                }
            }
        }
        return null;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
    public Category getCategory(int categoryID) {
        for (Category category : categories) {
            if (category.getID() == categoryID) {
                return category;
            }
        }
        return null;
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }

    public Product getProduct(String productID) {
        int categoryID = Integer.parseInt(productID.split("\\.")[0]);
        int productIDInt = Integer.parseInt(productID.split("\\.")[1]);
        for (Category category : categories) {
            if (category.getID() == categoryID) {
                for (Product product : category.getProducts()) {
                    if (product.getID() == productIDInt) {
                        return product;
                    }
                }
            }
        }
        return null;
    }

    public void deleteProduct(Product product) {
        for (Category category : categories) {
            if (category.getProducts().contains(product)) {
                category.getProducts().remove(product);
                return;
            }
        }
    }

    public void addDeal(Deal deal) {
        deals.add(deal);
    }

    public ArrayList<Deal> getDeals() {
        return deals;
    }

    public Deal getDeal(String dealID) {
        for (Deal deal : deals) {
            if (deal.getID().equals(dealID)) {
                return deal;
            }
        }
        return null;
    }

    public Customer morphCustomer(Customer customer, String status) {
        String currentStatus = customer.getStatus();
        if (currentStatus.equals(status)) {
            return customer;
        }
        switch (currentStatus) {
            case "Normal":
                if (status.equals("Prime")) {
                    if (customer.getBalance() < 200) {
                        System.out.println("Not enough balance");
                        return customer;
                    }
                    customer.setBalance(customer.getBalance() - 200);
                } else if (status.equals("Elite")) {
                    if (customer.getBalance() < 300) {
                        System.out.println("Not enough balance");
                        return customer;
                    }
                    customer.setBalance(customer.getBalance() - 300);
                }
                break;
            case "Prime":
                if (status.equals("Elite")) {
                    if (customer.getBalance() < 100) {
                        System.out.println("Not enough balance");
                        return customer;
                    }
                    customer.setBalance(customer.getBalance() - 100);
                }
                break;
        }
        switch (status) {
            case "Normal" -> {
                NormalUser normalUser = new NormalUser(customer.getUsername(), customer.getHashedPassword(), customer.getBalance(), customer.getCart(), customer.getDealsAddedToCart(), customer.getCoupons());
                System.out.println("Customers array: " + customers);
                System.out.println("Customer found at index " + customers.indexOf(customer));
                customers.set(customers.indexOf(customer), normalUser);
                return normalUser;
            }
            case "Prime" -> {
                PrimeUser primeUser = new PrimeUser(customer.getUsername(), customer.getHashedPassword(), customer.getBalance(), customer.getCart(), customer.getDealsAddedToCart(), customer.getCoupons());
                System.out.println("Customers array: " + customers);
                System.out.println("Customer found at index " + customers.indexOf(customer));
                customers.set(customers.indexOf(customer), primeUser);
                return primeUser;
            }
            case "Elite" -> {
                EliteUser eliteUser = new EliteUser(customer.getUsername(), customer.getHashedPassword(), customer.getBalance(), customer.getCart(), customer.getDealsAddedToCart(), customer.getCoupons());
                System.out.println("Customers array: " + customers);
                System.out.println("Customer found at index " + customers.indexOf(customer));
                customers.set(customers.indexOf(customer), eliteUser);
                return eliteUser;
            }
        }
        return customer;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (Category category : categories) {
            products.addAll(category.getProducts());
        }
        return products;
    }
}
