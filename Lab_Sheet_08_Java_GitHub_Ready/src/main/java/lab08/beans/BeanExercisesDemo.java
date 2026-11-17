package lab08.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.MethodDescriptor;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/** Demonstrates the JavaBean exercises 1–10. */
public class BeanExercisesDemo {
    public static void main(String[] args) throws Exception {
        // 1. StudentBean: id, name and grade.
        StudentBean student = new StudentBean();
        student.setId(101);
        student.setName("Aarav");
        student.setGrade(8.7);
        System.out.printf("1. Student: %d, %s, %.2f%n",
                student.getId(), student.getName(), student.getGrade());

        // 2. RectangleBean: area and perimeter.
        RectangleBean rectangle = new RectangleBean(10, 5);
        System.out.printf("2. Rectangle area=%.2f, perimeter=%.2f%n",
                rectangle.calculateArea(), rectangle.calculatePerimeter());

        // 3. EmployeeBean implements Serializable.
        EmployeeBean employee = new EmployeeBean();
        employee.setEmpId(501);
        employee.setEmpName("Neha Sharma");
        employee.setSalary(45000);
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
            output.writeObject(employee);
        }
        System.out.println("3. EmployeeBean serialized to employee.ser");

        // 4. Read-only currentDate.
        DateBean dateBean = new DateBean();
        System.out.println("4. Current date: " + dateBean.getCurrentDate());

        // 5. Write-only password (no getPassword method exists).
        UserBean user = new UserBean();
        user.setPassword("ChangeMe_123");
        System.out.println("5. Password set: " + user.hasPassword()
                + " (password is intentionally not displayed)");

        // 6–7. PropertyChangeListener and balance event.
        AccountBean account = new AccountBean();
        account.addPropertyChangeListener(event ->
            System.out.printf("6–7. %s changed: %.2f -> %.2f%n",
                event.getPropertyName(),
                (Double) event.getOldValue(),
                (Double) event.getNewValue())
        );
        account.setBalance(1000.00);
        account.setBalance(1750.50);

        // 8. Introspector: properties and methods.
        BeanInfo info = Introspector.getBeanInfo(StudentBean.class);
        System.out.println("8. StudentBean properties:");
        for (PropertyDescriptor property : info.getPropertyDescriptors()) {
            Method getter = property.getReadMethod();
            Method setter = property.getWriteMethod();
            System.out.printf("   %s : %s (getter=%s, setter=%s)%n",
                property.getName(),
                property.getPropertyType() == null
                    ? "unknown" : property.getPropertyType().getSimpleName(),
                getter == null ? "none" : getter.getName(),
                setter == null ? "none" : setter.getName());
        }
        System.out.println("   StudentBean methods:");
        for (MethodDescriptor descriptor : info.getMethodDescriptors()) {
            System.out.println("   " + descriptor.getMethod().getName());
        }

        // 9. CarBean: set and get properties.
        CarBean car = new CarBean();
        car.setMake("Toyota");
        car.setModel("Camry");
        car.setYear(2024);
        System.out.printf("9. Car: %s %s %d%n", car.getMake(), car.getModel(), car.getYear());

        // 10. CircleBean: read-only area and circumference.
        CircleBean circle = new CircleBean();
        circle.setRadius(7);
        System.out.printf("10. Circle radius=%.2f, area=%.2f, circumference=%.2f%n",
                circle.getRadius(), circle.getArea(), circle.getCircumference());
    }
}
