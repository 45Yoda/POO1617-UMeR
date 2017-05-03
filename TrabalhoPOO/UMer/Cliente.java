
public class Cliente extends Utilizador{
   
   private Ponto2D localizacao;
   private Historico viagens;
   
   //Construtores
   public Cliente(){
       super();
       localizacao = null;
       viagens = null;
   }
   
   public Cliente(String email,String nome, String pass, String morada, String data, Ponto2D local, Historico viagem){
       super(email,nome,pass,morada,data);
       this.localizacao = local;
       this.viagens = viagem;
   }
   
   public Cliente(Cliente c){
       super(c);
       localizacao = c.getLocalizacao();
       viagens = c.getViagens();
   }
   
   //gets
   
   public Ponto2D getLocalizacao(){
       return (Ponto2D) this.localizacao;
   }
   
   public Historico getViagens(){
       return (Historico) this.viagens;
   }
   
   //sets
   public void setLocalizacao(Ponto2D p){
       this.localizacao = p.clone();
   }
   
   public void setViagem(Historico h){
       this.viagens.add(h.clone());
   }
   
   //Método clone
   public Cliente clone(){
       return new Cliente();
   }
   
   //Método equals
   public boolean equals(Object o){
       if(this==o) return true;
       if((o==null) || o.getClass() != this.getClass()) return false;
       Cliente c = (Cliente) o;
       return c.getViagens().equals(viagens) &&
              c.getLocalizacao().equals(localizacao);
   }

   //Método toString
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("( ").append(viagens.toString()).append(" )");
       sb.append("( ").append(localizacao.toString()).append(" )");
       
       return sb.toString();
   }
}