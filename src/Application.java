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
}
