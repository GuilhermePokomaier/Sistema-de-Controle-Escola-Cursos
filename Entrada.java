import java.util.Scanner;

// Classe auxiliar: centraliza a leitura do teclado e evita que o programa
// quebre quando o usuário digita texto onde era esperado um número.
public class Entrada {

    private static Scanner scanner = new Scanner(System.in);

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                // aceita tanto 8.5 quanto 8,5
                return Double.parseDouble(lerTexto(mensagem).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (ex: 8.5).");
            }
        }
    }
}
