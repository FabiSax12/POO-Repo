package ui;

import Models.Customer;
import Models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class Home extends JFrame {
    private JPanel homePanel;
    private JTabbedPane tabs;

    // Employees Tab
    private JTable employeesTable;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel salaryLabel;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JTextField salaryInput;
    private JButton addEmployeeBtn;

    // Customers Tab
    private JTable customersTable;
    private JLabel accountNumberLabel;
    private JTextField accountNumberInput;
    private JButton addCustomerBtn;

    private final ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();

    public Home(Employee[] employeesData, Customer[] customersData) {
        this.employees.addAll(Arrays.asList(employeesData));
        this.refreshEmployeesTable();

        this.customers.addAll(Arrays.asList(customersData));
        this.refreshCustomerTable();

        this.frameConfig();

        this.addEmployeeBtn.addActionListener(e -> {
            employees.add(new Employee(
                    firstNameInput.getText(),
                    lastNameInput.getText(),
                    Integer.parseInt(salaryInput.getText()))
            );
            firstNameInput.setText("");
            lastNameInput.setText("");
            salaryInput.setText("");

            refreshEmployeesTable();
        });

        this.addCustomerBtn.addActionListener(e -> {
            customers.add(new Customer(
                    firstNameInput.getText(),
                    lastNameInput.getText(),
                    accountNumberInput.getText()
            ));
            firstNameInput.setText("");
            lastNameInput.setText("");
            accountNumberInput.setText("");

            refreshEmployeesTable();
        });
    }

    private void frameConfig() {
        setContentPane(homePanel);
        setLocationRelativeTo(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Bank POO");
        setVisible(true);
    }

    private void refreshEmployeesTable() {
        String[] columns = {"Id", "FirstName", "LastName", "Salary"};
        Object[][] data = new Object[this.employees.size()][columns.length];
        for (int i = 0; i < this.employees.size(); i++) {
            data[i][0] = this.employees.get(i).getId();
            data[i][1] = this.employees.get(i).getFirstName();
            data[i][2] = this.employees.get(i).getLastname();
            data[i][3] = this.employees.get(i).getSalary();

        }
        employeesTable.setModel(new DefaultTableModel(data, columns));
    }

    private void refreshCustomerTable() {
        String[] columns = {"Id", "FirstName", "LastName", "AccountNumber"};
        Object[][] data = new Object[this.customers.size()][columns.length];
        for (int i = 0; i < this.customers.size(); i++) {
            data[i][0] = this.customers.get(i).getId();
            data[i][1] = this.customers.get(i).getFirstName();
            data[i][2] = this.customers.get(i).getLastname();
            data[i][3] = this.customers.get(i).getAccountNumber();
        }
        customersTable.setModel(new DefaultTableModel(data, columns));
    }
}
