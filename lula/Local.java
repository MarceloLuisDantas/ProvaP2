package lula;

/**
 * Classe que guarda as informações de um local
 * 
 * @author Marcelo Dantas
 */
public class Local {
    private String local; // Nome do local
    private String id;    // Identificação do local
    private int ramal;    // Ramal Telefônico de identificação

    public Local(String local, String id, int ramal) {
        this.local = local;
        this.id = id;
        this.ramal = ramal;
    } 

    public String getId()    { return this.id; }

    public String toString() {   
        return String.format("%s - %s - %d", id, local, ramal);
    } 

    /**
     * Dois locais são considerado o mesmo, caso os ids sejam os mesmo
     */
    public boolean equals(Local local) {
        return this.id.equals(local.getId());
    }
}
