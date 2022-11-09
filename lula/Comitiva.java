package lula;

/**
 * Classe que guarda as informações sobre as comitivas
 * 
 * @author Marcelo Dantas
 */
public class Comitiva {
    private String descricao; // Descrição sobre a comitiva, deve conter o nome 
    private int telefone;     // Telefone para contato
    private int total_p;      // Total de participantes da comitiva
    private int id;           // Identificação da comitiva

    public Comitiva(int id, String d, int tele, int total) {
        this.descricao = d;
        this.telefone = tele;
        this.total_p = total;
        this.id = id;
    }

    public int getId() { return this.id; }

    public String toString() {
        return String.format(
            "ID: %d \nComitiva: %s \nIntegrantes: %d \nContato: %d", 
            this.id, this.descricao, this.total_p, this.telefone
        );
    }

    /**
     * Duas comitivas são consideradas a mesma, caso os ids sejam os mesmo
     */
    public boolean equals(Comitiva c) {
        return this.id == c.getId();
    }
}
