import java.util.ArrayList;

public class Application {
    private ArrayList<Admin> admins;
    private ArrayList<Customer> customers;
    private ArrayList<Category> categories;
    private ArrayList<Deal> deals;
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
}
