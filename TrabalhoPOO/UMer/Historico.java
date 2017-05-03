
import java.util.HashMap;
import java.util.Map;

public class Historico{
    private Map<Ponto2D,Ponto2D> viagem;
    private String nomeCondut;
    
    
    public Historico(){
        this.nomeCondut = " ";
        this.viagem = new HashMap<>();
    }
    
    public Historico(String nome, Map<Ponto2D,Ponto2D> viagem){
        this.nomeCondut = nome;
        this.viagem = new HashMap<Ponto2D,Ponto2D>();
        setViagem(viagem);
    }
    
    public Historico(Historico c){
        this.nomeCondut = c.getNomeCondut();
        this.viagem = c.getViagem();
    }
    
    public String getNomeCondut(){
        return nomeCondut;
    }
    
    private Map<Ponto2D,Ponto2D> getViagem(){
        return this.viagem.entrySet()
                          .stream()
                          .collect(toMap(e->e.getKey(),e->e.getValue().clone()));
    }
    
    private void setViagem(Map<Ponto2D,Ponto2D> viagem){
        this.viagem = viagem.entrySet()
                            .stream()
                            .collect(toMap(e->e.getKey(),e->e.getValue().clone()));
    }
    
    public int quantViagens(){
        return this.viagem.size();
    }
    
    public void adiciona(Ponto2D inicio, Ponto2D fim){
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
               hist.getNomeCondt().equals(nomeCondut);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Condutor ").append(nomeCondut).append("\n");
        sb.append("( ").append(viagem.toString()).append(" )");
        return sb.toString();
    }
}
