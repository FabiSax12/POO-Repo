import Models.Customer;
import Models.Employee;
import ui.Home;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Amy", "Pérez", "192387");

        Home home = new Home(
                new Employee[]{
                    new Employee("Fabián", "Vargas", 15000),
                    new Employee("Amy", "Pérez", 20000),
                    new Employee("Joseph", "Salas", 14000)
                },
                new Customer[]{
                        new Customer("Ignacio", "Santos", "1A8173B")
                }
        );
    }
}
