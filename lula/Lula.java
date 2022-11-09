package lula;

import java.util.Scanner;

public class Lula {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Sistema sistema = new Sistema(100);
        
        while (true) {
            System.out.print(" $> ");
            String linha = input.nextLine();
        
            String result = Eval.eval(sistema, linha);
            if (result.equals("exit"))
                break;
        
            System.out.println(result);
        }

        System.out.println("Finalizanod Programa");
        input.close();
    }
}
