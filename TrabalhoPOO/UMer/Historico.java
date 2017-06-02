import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Historico{
    private List<Viagem> viagens;
    
    
    public Historico(){
        this.viagens = new ArrayList<Viagem>();
    }
    
    public Historico(List<Viagem> viagens){
        this.viagens = new ArrayList<Viagem>();
        setViagens(viagens);
    }
    
    public Historico(Historico c){
        this.viagens = c.getViagens();
    }
 
    
    public List<Viagem> getViagens(){
        List<Viagem> aux = new ArrayList<Viagem>();
        for(Viagem v: this.viagens)
            aux.add(v.clone());
                
        return aux;
    }
    
    public void setViagens(List<Viagem> viagens){
        for(Viagem v: viagens)
            this.viagens.add(v.clone());
    }
    
    
    //NEW !!!!!!!
    public List<Viagem> getBetween(LocalDate data1, LocalDate data2){
        List<Viagem> trips = new ArrayList<Viagem>();
        
        for(Viagem v: this.viagens){
            LocalDate data = v.getData();
            if(data.isAfter(data1) && data.isBefore(data2)){
                trips.add(v.clone());
            }
        }
        
        return trips;
    }
    //NEW !!!!!!
    
    
    
    public int quantViagens(){
        return this.viagens.size();
    }
    
    public void addViagem(Viagem v){
        this.viagens.add(v.clone());
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
        return hist.getViagens().equals(viagens);
    }
    
    public String toString(){
        int i;
        StringBuilder sb = new StringBuilder();
        for(i=0;i<this.viagens.size();i++){
            sb.append("Viagem: ").append(viagens.get(i).toString()).append("\n");
        }
        
        return sb.toString();
    }
}
