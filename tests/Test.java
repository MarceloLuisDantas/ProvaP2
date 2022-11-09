package tests;
public class Test { 
    public static void test(String nome, String esperado, String resultado) {
        if (esperado.equals(resultado)) {
            System.out.println("OK - " + nome);
        } else {
            System.out.println("-----------------------------");
            System.out.println("Erro em: " + nome);
            System.out.println(" - Resultado esperada: ");
            System.out.println("    " + esperado);
            System.out.println(" - Resultado real: ");
            System.out.println("    " + resultado);
            System.out.println("-----------------------------");
        }
    }
    public static void test(String nome, Boolean esperado, Boolean resultado) {
        if (esperado == resultado) {
            System.out.println("OK - " + nome);
        } else {
            System.out.println("-----------------------------");
            System.out.println("Erro em: " + nome);
            System.out.println(" - Resultado esperada: ");
            System.out.println("    " + esperado);
            System.out.println(" - Resultado real: ");
            System.out.println("    " + resultado);
            System.out.println("-----------------------------");
        }
    }
    public static void test(String nome, int esperado, int resultado) {
        if (esperado == resultado) {
            System.out.println("OK - " + nome);
        } else {
            System.out.println("-----------------------------");
            System.out.println("Erro em: " + nome);
            System.out.println(" - Resultado esperada: ");
            System.out.println("    " + esperado);
            System.out.println(" - Resultado real: ");
            System.out.println("    " + resultado);
            System.out.println("-----------------------------");
        }
    }
    public static void test(String nome, Double esperado, Double resultado) {
        if (esperado == resultado) {
            System.out.println("OK - " + nome);
        } else {
            System.out.println("-----------------------------");
            System.out.println("Erro em: " + nome);
            System.out.println(" - Resultado esperada: ");
            System.out.println("    " + esperado);
            System.out.println(" - Resultado real: ");
            System.out.println("    " + resultado);
            System.out.println("-----------------------------");
        }
    }
}
