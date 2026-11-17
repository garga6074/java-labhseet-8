package lab08.beans;
import java.io.Serializable;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int empId;
    private String empName;
    private double salary;

    public EmployeeBean() { }
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if (!Double.isFinite(salary) || salary < 0) {
            throw new IllegalArgumentException("salary must be finite and non-negative");
        }
        this.salary = salary;
    }
}
