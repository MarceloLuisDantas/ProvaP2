package tests;

import lula.Local;
import lula.Comitiva;
import lula.Sistema;
import lula.Eval;

public class Testes {
    private static void teste_local() {
        Local l1 = new Local("Biblioteca", "lib", 23);
        Test.test("Teste toString Local", 
              "lib - Biblioteca - 23", l1.toString());
        
        Local l2 = new Local("Library", "lib", 24);
        Local l3 = new Local("Library", "liby", 24);
        Test.test("Teste Local equals - Igual", true, l1.equals(l2));
        Test.test("Teste Local equals - Diferente", false, l1.equals(l3));
    }
    private static void teste_comitiva() {
        Comitiva comitiva = new Comitiva(
            0, "Alunos Concluintes do IFPB", 99565678, 10);

        String toStr = comitiva.toString();
        String esperado = "ID: 0 \nComitiva: Alunos Concluintes do IFPB \nIntegrantes: 10 \nContato: 99565678";
        Test.test("Teste Comitiva toString", esperado, toStr);

        Comitiva c_repetida = new Comitiva(
            0, "Alunos Concluintes do IFPB 2", 0, 0);
        Test.test("Tetes Comitiva equals - Igual", true, comitiva.equals(c_repetida));
        
        Comitiva c_diferente = new Comitiva(
            1, "Claramente não são os 'Alunos Concluintes do IFPB'", 99565678, 10);
        Test.test("Tetes Comitiva equals - Diferente", false, comitiva.equals(c_diferente));
    }
    
    private static void teste_sistema_local() {
        Sistema sis = new Sistema(3);

        int result1 = sis.addLocal("Biblioteca", "lib", 23);
        Test.test("Teste addLocal - Adicionado", 0, result1);

        int result2 = sis.addLocal("Refeitorio", "ref", 24);
        Test.test("Teste addLocal - Adicionado", 0, result2);
        
        int result3 = sis.addLocal("Entrada", "lib", 27);
        Test.test("Teste addLocal - Item repetido", 2, result3);
        
        int result4 = sis.addLocal("Estacionamento", "est", 25);
        Test.test("Teste addLocal - Adicionado", 0, result4);
        
        int result5 = sis.addLocal("Laboratorio", "lab", 26);
        Test.test("Teste addLocal - Sem espaço livre", 1, result5);

        String r_show1 = "lib - Biblioteca - 23";
        Test.test("Teste showLocal - Mostra local existente", r_show1, sis.showLocal("lib"));

        String r_show2 = "Nem um Local com ID: foo encontrado";
        Test.test("Teste showLocal - Local não existente", r_show2, sis.showLocal("foo"));
    }
    private static void teste_sistema_comitiva() {
        Sistema sis = new Sistema(0);

        int result1 = sis.addComitiva(0, "Alunos Concluintes do IFPB", 99565678, 10);
        Test.test("Teste addComitiva - Adicionado", 0, result1);

        int result2 = sis.addComitiva(1, "Alunos Primeiro Ano do IFPB", 99566742, 23);
        Test.test("Teste addComitiva - Adicionado", 0, result2);
        
        int result3 = sis.addComitiva(-1, "menor que 0", 0, 0);
        Test.test("Teste addComitiva - ID menor que zero", 1, result3);

        int result4 = sis.addComitiva(100, "maior que 99", 0, 0);
        Test.test("Teste addComitiva - ID maior que 99", 1, result4);

        int result5 = sis.addComitiva(1, "teste de repetido", 0, 0);
        Test.test("Teste addComitiva - Item repetido", 2, result5);        

        String r_show1 = "ID: 0 \nComitiva: Alunos Concluintes do IFPB \nIntegrantes: 10 \nContato: 99565678";
        Test.test("Teste showComitiva - Mostra comitiva existente", r_show1, sis.showComitiva(0));

        String r_show2 = "Nem uma Comitiva com ID: 5 encontrado";
        Test.test("Teste showComitiva - Comitiva não existente", r_show2, sis.showComitiva(5));
    }
    
    private static void teste_eval_cad_local() {
        Sistema sis = new Sistema(3);
        
        sis.addLocal("Biblioteca", "lib", 23);
        sis.addLocal("Refeitorio", "ref", 24);

        String add_l1 = "CAD_LOCAL \"ref\" \"Refeitorio\" \"0\"";
        String add_l2 = "CAD_LOCAL \"CN\" \"Departamento\" \"1120\"";
        String add_l3 = "CAD_LOCAL \"foo\" \"todos os locais cheios\" \"0\"";

        String result1 = Eval.eval(sis, add_l1);
        Test.test("Teste eval CAD_LOCAL - Local repetido", "Local já cadastrado", result1);

        String result2 = Eval.eval(sis, add_l2);
        Test.test("Teste eval CAD_LOCAL - Local cadastrado", "Local cadastrado com sucesso", result2);

        String result3 = Eval.eval(sis, add_l3);        
        Test.test("Teste eval CAD_LOCAL - Sem espaços sobrando", "Todos os espaços prenchidos", result3);
    }
    private static void teste_eval_cad_comitiva() {
        Sistema sis = new Sistema(0);

        sis.addComitiva(0, "Alunos Concluintes do IFPB", 99565678, 10);

        String add_c1 = "CAD_COMITIVA 0 \"Alunos Concluintes do IFPB\" 10 99565678";            
        String add_c2 = "CAD_COMITIVA 100 \"id maior que 99\" 0 1";
        String add_c3 = "CAD_COMITIVA -1 \"id menor que 0\" 0 1";
        String add_c4 = "CAD_COMITIVA 1 \"Alunos Primeiro Ano do IFPB\" 23 99565984";

        String result1 = Eval.eval(sis, add_c1);
        Test.test("Teste eval CAD_COMITIVA - Item Repetido", "Comitiva já cadastrada", result1);
        
        String result2 = Eval.eval(sis, add_c2);
        Test.test("Teste eval CAD_COMITIVA - ID maior que 99", "ID's não podem estar abaixo de 0 ou a cima de 99", result2);
        
        String result3 = Eval.eval(sis, add_c3);
        Test.test("Teste eval CAD_COMITIVA - ID menor que 0", "ID's não podem estar abaixo de 0 ou a cima de 99", result3);
        
        String result4 = Eval.eval(sis, add_c4);
        Test.test("Teste eval CAD_COMITIVA - Comitiva adicionada", "Comitiva cadastrado com sucesso", result4);
    }
    private static void teste_eval_exibe_local() {
        Sistema sis = new Sistema(3);
        
        sis.addLocal("Biblioteca", "lib", 23);
        sis.addLocal("Refeitorio", "ref", 24);

        String exibe1 = "EXIBE_LOCAL lib";
        String exibe2 = "EXIBE_LOCAL foo";

        String result1 = Eval.eval(sis, exibe1);
        Test.test("Teste eval EXIBE_LOCAL - Mostra Local", "lib - Biblioteca - 23", result1); 

        String result2 = Eval.eval(sis, exibe2);
        Test.test("Teste eval EXIBE_LOCAL - Local não cadastrado", "Nem um Local com ID: foo encontrado", result2); 
    }
    private static void teste_eval_exibe_comitiva() {
        Sistema sis = new Sistema(0);

        sis.addComitiva(0, "Alunos Concluintes do IFPB", 99565678, 10);

        String exibe1 = "EXIBE_COMITIVA 0";
        String exibe2 = "EXIBE_COMITIVA 1";

        String result1 = Eval.eval(sis, exibe1);
        String esperado1 = "ID: 0 \nComitiva: Alunos Concluintes do IFPB \nIntegrantes: 10 \nContato: 99565678";
        Test.test("Teste eval EXIBE_COMITIVA - Mostra Comitiva", esperado1, result1);

        String result2 = Eval.eval(sis, exibe2);
        Test.test("Teste eval EXIBE_COMITIVA", "Nem uma Comitiva com ID: 1 encontrado", result2);
    }
    private static void teste_eval_comando_errado() {
        Sistema sis = new Sistema(0);

        String result = Eval.eval(sis, "comandofalso foo bar");
        Test.test("Teste eval - Comando não existente", "comandofalso não é um comando reconhecido", result);
    }
    private static void teste_eval_exit() {
        Sistema sis = new Sistema(0);

        String result = Eval.eval(sis, "EXIT");
        Test.test("Teste eval - Exit", "exit", result);
    }
    
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Testes - Classe Local");
        teste_local();

        System.out.println();
        System.out.println("Testes - Classe Comitiva");
        teste_comitiva();

        System.out.println();
        System.out.println("Testes - Classe Sistema | Local");
        teste_sistema_local();

        System.out.println();
        System.out.println("Testes - Classe Sistema | Coimitiva");
        teste_sistema_comitiva();

        System.out.println();
        System.out.println("Testes - Classe Eval | CAD_LOCAL");
        teste_eval_cad_local();

        System.out.println();
        System.out.println("Testes - Classe Eval | CAD_COMITIVA");
        teste_eval_cad_comitiva();

        System.out.println();
        System.out.println("Testes - Classe Eval | EXIBE_LOCAL");
        teste_eval_exibe_local();

        System.out.println();
        System.out.println("Testes - Classe Eval | EXIBE_COMITIVA");
        teste_eval_exibe_comitiva();

        System.out.println();
        System.out.println("Testes - Classe Eval | Comando não existente");
        teste_eval_comando_errado();

        System.out.println();
        System.out.println("Testes - Classe Eval | Exit");
        teste_eval_exit();
    }
}
