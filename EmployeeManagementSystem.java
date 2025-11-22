import java.util.Scanner;

class Employee {
    protected int employeeId;
    protected String name;
    protected double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    
    public Employee(int employeeId, String name) {
        this(employeeId, name, 50000); 
    }

    public double calculateBonus() {
        return salary * 0.05; 
    }

    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId +
                ", Name: " + name +
                ", Salary: " + salary +
                ", Bonus: " + calculateBonus());
    }
}


class Manager extends Employee {
    private String department;

    public Manager(int employeeId, String name, double salary, String department) {
        super(employeeId, name, salary);
        this.department = department;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10; 
    }

    @Override
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId +
                ", Name: " + name +
                ", Department: " + department +
                ", Salary: " + salary +
                ", Bonus: " + calculateBonus());
    }
}


class Developer extends Employee {
    private String programmingLanguage;

    public Developer(int employeeId, String name, double salary, String programmingLanguage) {
        super(employeeId, name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.08;
    }

    @Override
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId +
                ", Name: " + name +
                ", Programming Language: " + programmingLanguage +
                ", Salary: " + salary +
                ", Bonus: " + calculateBonus());
    }
}

public class EmployeeManagementSystem {
    private static Scanner sc = new Scanner(System.in);
    private static Employee[] employees = new Employee[100];
    private static int count = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Employee Management System!");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Developer");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addManager(); break;
                case 2: addDeveloper(); break;
                case 3: searchEmployee(); break;
                case 4: displayAllEmployees(); break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void addManager() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        employees[count++] = new Manager(id, name, salary, dept);
        System.out.println("Manager added successfully.");
    }

    private static void addDeveloper() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Programming Language: ");
        String lang = sc.nextLine();

        employees[count++] = new Developer(id, name, salary, lang);
        System.out.println("Developer added successfully.");
    }

    private static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                employees[i].displayDetails();
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    private static void displayAllEmployees() {
        if (count == 0) {
            System.out.println("No employees available!");
            return;
        }
        for (int i = 0; i < count; i++) {
            employees[i].displayDetails();
        }
    }
}
