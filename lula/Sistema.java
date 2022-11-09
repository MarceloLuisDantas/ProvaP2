package lula;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Local> locais = new ArrayList<Local>();
    private List<Comitiva> comitivas = new ArrayList<Comitiva>();
    private int limiteLocais; // alterar para o valor real de 100 depois

    public Sistema(int l) {
        this.limiteLocais = l;
    }

    private boolean localRepetido(Local l) {
        for (Local i : locais) 
            if (i.equals(l)) 
                return true;
        return false;
    }
    public int addLocal(String l, String id, int cn) {
        if (locais.size() >= limiteLocais)
            return 1;

        Local newLocal = new Local(l, id, cn);
        if (localRepetido(newLocal)) 
            return 2;   
        
        locais.add(newLocal);
        return 0;
    }
    public String showLocal(String id) {
        for (Local i : locais) 
            if (i.getId().equals(id))
                return i.toString();
        return String.format("Nem um Local com ID: %s encontrado", id);
    }

    private boolean comiRepetida(Comitiva c) {
        for (Comitiva i : comitivas) 
            if (i.equals(c)) 
                return true;
        return false;
    }
    public int addComitiva(int id, String d, int tele, int total) {
        if (id < 0 || id > 99) 
            return 1;
        
        Comitiva c = new Comitiva(id, d, tele, total);
        if (comiRepetida(c))
            return 2;

        comitivas.add(c);
        return 0;
    }
    public String showComitiva(int id) {
        for (Comitiva i : comitivas) 
            if (i.getId() == id)
                return i.toString();
        return String.format("Nem uma Comitiva com ID: %s encontrado", id);
    }   
}
