package lab08.beans;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AccountBean {
    private double balance;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public AccountBean() { }
    public double getBalance() { return balance; }
    public void setBalance(double newBalance) {
        if (!Double.isFinite(newBalance)) {
            throw new IllegalArgumentException("Balance must be finite");
        }
        double oldBalance = balance;
        balance = newBalance;
        changes.firePropertyChange("balance", oldBalance, newBalance);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }
}
