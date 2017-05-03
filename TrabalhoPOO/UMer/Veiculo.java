
import java.util.List;
import java.util.ArrayList;

public abstract class Veiculo{
   private String matricula;
   private List<Cliente> filaEspera;
   
   //Construtores
   public Veiculo (){
       this.matricula = " ";
       this.filaEspera = new ArrayList<>();
   }
   
   public Veiculo(String matricula){
       this.matricula = matricula;
       this.filaEspera = new ArrayList<Cliente>();
   }
   
   public Veiculo (Veiculo v){
       this.matricula = v.getMat();
       this.filaEspera = v.getFila();
   }
   
   
   //gets
   public String getMat(){
       return matricula;
   }
   
   public List<Cliente> getFila(){
       return new ArrayList<Cliente>(this.filaEspera);
   }
   
   //sets
   public void setMat(String matricula){
       this.matricula = matricula;
   }
   
   public void insereFila(Cliente next){
       this.filaEspera.add(next.clone());
   }

   
}