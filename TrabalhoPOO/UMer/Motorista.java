
public class Motorista extends Utilizador{
   
   private int grauCump;
   private int classificacao;
   private Historico viagens;
   private double kmsTotal;
   private String disponibilidade;
   private Ponto2D localizacao;
   
   
   //Construtores
   public Motorista(){
       super();
       grauCump = 0;
       classificacao = 0;
       kmsTotal = 0;
       localizacao = null;
       viagens = null;
       
   }
   
   public Motorista(String email,String nome, String pass, String morada, String data, int grau, int classif, Historico viagem, double kmsT, String disp, Ponto2D local){
       super(email,nome,pass,morada,data);
       this.grauCump = grau;
       this.classificacao = classif;
       this.viagens = viagem;
       this.kmsTotal = kmsT;
       this.disponibilidade = disp;
       this.localizacao = local;
   }
   
   public Motorista(Motorista m){
       super(m);
       this.grauCump = m.getGrau();
       this.classificacao = m.getClass();
       this.viagens = m.getViagens();
       this.kmsTotal = m.getKmsT();
       this.disponibilidade = m.getDisp();
       this.localizacao = m.getLocalizacao();
   }
   
   //gets
   public int getGrau(){
       return this.grauCump;
   }
   
   public int getClass(){
       return this.classificacao;
   }
   
   public Historico getViagens(){
       return (Historico) this.viagens;
   }
   
   public double getKmsT(){
       return this.kmsTotal;
   }
   
   public String getDisp(){
       return this.disponibilidade;
   }
   
   public Ponto2D getLocalizacao(){
       return (Ponto2D) this.localizacao;
   }
   
   //sets
   public void setGrau(int grauCump){
       this.grauCump = grauCump;
   }
   
   public void setClass(int classificacao){
       this.classificacao = classificacao;
   }
   
   public void setViagem(Historico h){
       this.viagens.add(h.clone());
   }
   
   public void setKmsT(double kmsTotal){
       this.kmsTotal = kmsTotal;
   }
   
   public void setDisp(String disponibilidade){
       this.disponibilidade = disponibilidade;
   }
   
   public void setLocalizacao(Ponto2D p){
       this.localizacao = p.clone();
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
       return mot.getGrau() == grauCump &&
              mot.getClass() == classificacao &&
              mot.getViagem().equals(viagens) &&
              mot.getKmsT() == kmsTotal &&
              mot.getDisp().equals(disponibilidade) &&
              mot.getLocalizacao().equals(localizacao);
   }
   
   //Método toString
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Grau de cumprimento: ").append(grau).append("\n");
       sb.append("Classificação: ").append(classificacao).append("\n");
       sb.append("( ").append(viagens.toString()).append(" )");
       sb.append("Kms percorridos(Total): ").append(kmsTotal).append("\n");
       sb.append("Disponibilidade: ").append(disponibilidade).append("\n");
       sb.append("( ").append(localizacao.toString()).append(" )");
       
       return sb.toString();
   }
}
