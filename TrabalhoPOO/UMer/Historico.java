
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class Historico{
    private Map<Localizacao,Localizacao> viagem;
    private String nomeCondut;
    
    
    public Historico(){
        this.nomeCondut = " ";
        this.viagem = new HashMap<>();
    }
    
    public Historico(String nome, Map<Localizacao,Localizacao> viagem){
        this.nomeCondut = nome;
        this.viagem = new HashMap<Localizacao,Localizacao>();
        setHistorico(viagem);
    }
    
    public Historico(Historico c){
        this.nomeCondut = c.getNomeCondut();
        this.viagem = c.getViagem();
    }
    
    public String getNomeCondut(){
        return nomeCondut;
    }
    
    public Map<Localizacao,Localizacao> getViagem(){
        return this.viagem.entrySet()
                          .stream()
                          .collect(toMap(e->e.getKey(),e->e.getValue().clone()));
    }
    
    public void setHistorico(Map<Localizacao,Localizacao> viagem){
        this.viagem = viagem.entrySet()
                            .stream()
                            .collect(toMap(e->e.getKey(),e->e.getValue().clone()));
    }
    
    public int quantViagens(){
        return this.viagem.size();
    }
    
    public void adiciona(Localizacao inicio, Localizacao fim){
        this.viagem.put(inicio,fim);
    }
    
    public Historico clone(){
        return new Historico(this);
    }
    
    public boolean equals(Object o){
        if(o==this)
            return true;
        if((o==null) || o.getClass() != this.getClass())
            return false;
        Historico hist = (Historico) o;
        return hist.getViagem().equals(viagem) &&
               hist.getNomeCondut().equals(nomeCondut);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Condutor ").append(nomeCondut).append("\n");
        sb.append("( ").append(viagem.toString()).append(" )");
        return sb.toString();
    }
}
