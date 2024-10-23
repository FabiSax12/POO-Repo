import java.lang.reflect.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese la clase que desea utilizar (o 'salir' para terminar): ");
            String clase = scanner.nextLine();

            if (clase.equalsIgnoreCase("salir")) break;

            clase = clase.substring(0, 1).toUpperCase() + clase.substring(1).toLowerCase();

            try {
                Class<?> dynamicClass = Class.forName("models." + clase);

                Constructor<?>[] constructors = dynamicClass.getConstructors();
                Constructor<?> constructor = chooseConstructor(constructors, scanner);
                System.out.println("Parametros: ");

                for (Parameter p : constructor.getParameters()) {
                    System.out.println(p.getType() + " " + p.getName() + ", ");
                }

                Object instance = createInstance(constructor, scanner);
                System.out.println("Instancia creada: " + instance);

                System.out.println("Ingrese el método que desea ejecutar: ");
                String metodo = scanner.nextLine();

                Method[] methods = dynamicClass.getMethods();
                Method method = chooseMethod(methods, metodo);
                Parameter[] parameters = method.getParameters();
                System.out.println(parameters);

                Object result = invokeMethod(instance, method, scanner);
                System.out.println("Resultado del método: " + result);

            } catch (ClassNotFoundException e) {
                System.out.println("Clase no encontrada: " + clase);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                System.out.println("Error al instanciar la clase o ejecutar el método: " + e.getMessage());
            }
        }
    }

    private static Constructor<?> chooseConstructor(Constructor<?>[] constructors, Scanner scanner) {
        if (constructors.length == 1) {
            return constructors[0];
        }

        System.out.println("Constructores disponibles:");
        for (int i = 0; i < constructors.length; i++) {
            System.out.println(i + ": " + constructors[i]);
        }

        System.out.println("Seleccione el número del constructor que desea utilizar: ");
        int constructorIndex = Integer.parseInt(scanner.nextLine());
        return constructors[constructorIndex];
    }

    private static Object createInstance(Constructor<?> constructor, Scanner scanner) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            System.out.println("Ingrese el valor para el parámetro " + parameters[i].getName() + " (tipo " + parameters[i].getType() + "): ");
            args[i] = getParameterValue(scanner, parameters[i].getType());
        }

        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    private static Method chooseMethod(Method[] methods, String methodName) throws NoSuchMethodException {
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException("Método no encontrado: " + methodName);
    }

    private static Object invokeMethod(Object instance, Method method, Scanner scanner) throws InvocationTargetException, IllegalAccessException {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            System.out.println("Ingrese el valor para el parámetro " + parameters[i].getName() + " (tipo " + parameters[i].getType() + "): ");
            args[i] = getParameterValue(scanner, parameters[i].getType());
        }

        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    private static Object getParameterValue(Scanner scanner, Class<?> paramType) {
        String input = scanner.nextLine();

        if (paramType == int.class) {
            return Integer.parseInt(input);
        } else if (paramType == double.class) {
            return Double.parseDouble(input);
        } else if (paramType == boolean.class) {
            return Boolean.parseBoolean(input);
        } else if (paramType == String.class) {
            return input;
        } else {
            throw new IllegalArgumentException("Tipo de parámetro no soportado: " + paramType);
        }
    }
}
