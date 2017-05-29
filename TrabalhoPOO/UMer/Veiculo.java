
import java.util.List;
import java.util.ArrayList;

public abstract class Veiculo{
   private int vMed; //Velocidade média por km
   private int preco; //Preço base por Km
   private int factorF; //factor de fiabilidade
   private String matricula;
   private List<Cliente> filaEspera;
   private boolean uso;
   
   //Construtores
   public Veiculo (){
       this.vMed = 0;
       this.preco = 0;
       this.factorF = 0;
       this.matricula = " ";
       this.filaEspera = new ArrayList<>();
       this.uso=false;
   }
   
   public Veiculo(int vMed, int preco, int factorF, String matricula){
       this.vMed = vMed;
       this.preco = preco;
       this.factorF = factorF;
       this.matricula = matricula;
       this.filaEspera = new ArrayList<Cliente>();
       this.uso=false;
   }
   
   public Veiculo (Veiculo v){
       this.vMed = v.getVMed();
       this.preco = v.getPreco();
       this.factorF = v.getFactorF();
       this.matricula = v.getMat();
       this.filaEspera = v.getFila();
       this.uso=v.getUso();
   }
   
   
   //gets
   
   public int getVMed(){
       return vMed;
   }
   
   public int getPreco(){
       return preco;
   }
   
   public int getFactorF(){
       return factorF;
   }
  
   public String getMat(){
       return matricula;
   }
   
   public List<Cliente> getFila(){
       return new ArrayList<Cliente>(this.filaEspera);
   }
   
   public boolean getUso() {
       return this.uso;
    }
   //sets
   
   public void setVMed(int vMed){
       this.vMed = vMed;
   }
   
   public void setPreco(int preco){
       this.preco = preco;
   }
   
   public void setFactorF(int factorF){
       this.factorF = factorF;
   }
   
   public void setMat(String matricula){
       this.matricula = matricula;
   }
   
   public void insereFila(Cliente next){
       this.filaEspera.add(next.clone());
   }

   public void setUso(boolean uso) {
    this.uso=uso;
   }
   //Clone do Veiculo
   public abstract Veiculo clone();
   
   //Método equals
   public boolean equals(Object o){
       if(o==this){
           return true;
       }
       if(o==null || o.getClass() != this.getClass()){
           return false;
       }
       Veiculo v = (Veiculo) o;
       return v.getVMed() == vMed
              && v.getPreco() == preco
              && v.getFactorF() == factorF
              && v.getMat().equals(matricula)
              && v.getFila().equals(filaEspera)
              && v.getUso()==uso;
   }
   
   //Método toString
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Velocidade média: ").append(vMed).append("\n");
       sb.append("Preço: ").append(preco).append("€").append("\n");
       sb.append("Factor de fiabilidade: ").append(factorF).append("\n");
       sb.append("Matricula: ").append(matricula).append("\n");
       sb.append("Em uso: ").append(uso).append("\n");
       for(Cliente c : filaEspera){
           sb.append(c).append("\n");
        }
       return sb.toString();
   }
}