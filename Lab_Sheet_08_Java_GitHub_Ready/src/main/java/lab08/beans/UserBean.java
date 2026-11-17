package lab08.beans;

/** password is write-only: no getter is provided. */
public class UserBean {
    private String password;

    public UserBean() { }
    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be empty");
        }
        this.password = password;
    }
    public boolean hasPassword() {
        return password != null && !password.isEmpty();
    }
}
