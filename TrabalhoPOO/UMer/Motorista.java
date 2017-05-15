import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class Motorista extends Utilizador{
   
   private int grauC; //grau de cumprimento de horário (0 a 100)
   private int classificacao; //classificação do motorista dada pelo cliente no final da viagem (0 a 100
   private Historico viagens; //histórico das viagens realizadas
   private double kmsTotal;
   private boolean disponibilidade;
   

   public Motorista(){
       super();
       grauC = 0;
       classificacao = 0;
       viagens = null;
       kmsTotal = 0;
       disponibilidade = false;
   }
   
   public Motorista(String email,String nome, String pass, String morada, String data, int grau, int classif, Historico viagem, double kmsT, boolean disp){
       super(email,nome,pass,morada,data);
       this.grauC = grau;
       this.classificacao = classif;
       this.viagens = viagem;
       this.kmsTotal = kmsT;
       this.disponibilidade = disp;
   }
   
   public Motorista(Motorista m){
       super(m);
       grauC = m.getGrau();
       classificacao = m.getClassif();
       viagens = m.getViagens();
       kmsTotal = m.getKmsTot();
       disponibilidade = m.getDisp();
   }
    
   //gets
   
   public int getGrau(){
       return this.grauC;
   }
   
   public int getClassif(){
       return this.classificacao;
   }
   
   public Historico getViagens(){
       return (Historico) this.viagens;
   }
   
   public double getKmsTot(){
       return this.kmsTotal;
   }
   
   public boolean getDisp(){
       return this.disponibilidade;
    }
   
   //sets
   public void setGrau(int grauC){
       this.grauC = grauC;
   }
   
   public void setClass(int classificacao){
       this.classificacao = classificacao;
   }
   
   public void setViagem(Historico h){
       this.viagens = h.clone();
   }
   
   public void setKmsT(double kmsTotal){
       this.kmsTotal = kmsTotal;
   }
   
   public void setDisp(boolean disponibilidade){
       this.disponibilidade = disponibilidade;
   }
   
   
   //Método clone
   public Motorista clone(){
       return new  Motorista();
   }
   
   //Método equals
   public boolean equals(Object o){
       if(this==o) return true;
       if((o==null) || o.getClass() != this.getClass()) return false;
       Motorista mot = (Motorista) o;
       return mot.getGrau() == grauC &&
              mot.getClassif() == classificacao &&
              mot.getViagens().equals(viagens) &&
              mot.getKmsTot() == kmsTotal &&
              mot.getDisp() == disponibilidade;
   }
   
   //Método toString
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Grau de cumprimento: ").append(grauC).append("\n");
       sb.append("Classificação: ").append(classificacao).append("\n");
       sb.append("( ").append(viagens.toString()).append(" )");
       sb.append("Kms percorridos(Total): ").append(kmsTotal).append("\n");
       sb.append("Disponibilidade: ").append(disponibilidade).append("\n");
       
       return sb.toString();
   }
}
