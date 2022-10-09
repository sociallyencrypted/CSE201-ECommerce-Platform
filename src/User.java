public class User {
    protected final String username;
    protected final String hashedPassword;

    public User(String username, String password) {
        this.username = username;
        this.hashedPassword = password;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean isAdmin() {
        return this instanceof Admin;
    }
}
