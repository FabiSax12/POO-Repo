import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Exercises {
    public void buildPyramid() { }

    public boolean checkIfPanagram() {
        // solicita un string y retorna true o false si el string contiene al menos una de todas las letras del abecedario
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese una cadena de caracteres");
        String input = scanner.nextLine().toLowerCase();

        // ASCII minúsculas 97 - 122

        ArrayList<Character> letters = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c >= 97 && c <= 122 && !letters.contains(c)) {
                letters.add(input.charAt(i));
            }

            if (letters.size() == 26) return true;
        }

        return false;
    }
}
