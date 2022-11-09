package lula;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Eval {
    private static String getCommand(String linha) {
        return linha.split(" ")[0];
    }
    private static String[] getEntreParenteses(String linha) {
        List<String> valores = 
            Arrays
              .asList(linha.split("\""))
              .stream()
              .filter(x -> x.trim() != "")
              .collect(Collectors.toList());
        return valores.toArray(new String[valores.size()]);
    }
    public static String eval(Sistema sis, String linha) {
        String comando = getCommand(linha);

        if (comando.equals("CAD_LOCAL")) {
            String[] valores = getEntreParenteses(linha);
            String id    = valores[1];
            String local = valores[2];
            String ramal = valores[3];

            int result = sis.addLocal(local, id, Integer.valueOf(ramal));
            if (result == 0) 
                return "Local cadastrado com sucesso";
            else if (result == 1)
                return "Todos os espaços prenchidos";
            else if (result == 2)
                return "Local já cadastrado";

        } else if (comando.equals("CAD_COMITIVA")) {
            String descricao = getEntreParenteses(linha)[1];

            String[] valores = linha.split(" ");
            int id       = Integer.valueOf(valores[1]);
            int total_p  = Integer.valueOf(valores[valores.length - 2]);
            int telefone = Integer.valueOf(valores[valores.length - 1]);
            
            int result = sis.addComitiva(id, descricao, telefone, total_p);
            if (result == 0)
                return "Comitiva cadastrado com sucesso";
            else if (result == 1)
                return "ID's não podem estar abaixo de 0 ou a cima de 99";
            else if (result == 2)
                return "Comitiva já cadastrada";

        } else if (comando.equals("EXIBE_COMITIVA")) {
            int id = Integer.valueOf(linha.split(" ")[1]);
            return sis.showComitiva(id);
        } else if (comando.equals("EXIBE_LOCAL")) {
            String id = linha.split(" ")[1];
            return sis.showLocal(id);
        } else if (comando.equals("EXIT")) {
            return "exit";
        }
        return String.format("%s não é um comando reconhecido", comando);
    }
}
