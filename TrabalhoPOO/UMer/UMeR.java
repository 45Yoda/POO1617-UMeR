import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;


public class UMeR
{
   private Map<String,Utilizador> utilizadores; //Email
   private Map<String,Veiculo> veiculo; //Matricula;
   private Utilizador user;
   private Map<Veiculo,Motorista> motorista;
   
   public UMeR(){
       this.utilizadores = new TreeMap<String,Utilizador>();
       this.veiculo = new TreeMap<String,Veiculo>();
       this.user = null;
   }
   
   public UMeR(TreeMap<String,Utilizador> uti, TreeMap<String,Veiculo> veic, Utilizador util){
       this.utilizadores = uti;
       this.veiculo = veic;
       this.user = util;
   }
   
   public UMeR (UMeR um){
       this.utilizadores = um.getUtilizadores();
       this.veiculo = um.getVeiculo();
       this.user = um.getUser();
   }
   
   //gets
   
   /*tipo de utilizador
    * 0 se n existir
    * 1-Cliente
    * 2-Motorista
    */
   public int getTipoUtilizador(){
       if(user instanceof Cliente){
           return 1;
       }
       if(user instanceof Motorista){
           return 2;
       }
       
       return 0;
   }

   //obter email
   private String getMail(){
       if(user != null){
           return user.getEmail();
       }
       
       return "Não existe";
   }
   
   //utilizador com login
   public Utilizador getUser(){
       if(user instanceof Cliente){
           Cliente c = (Cliente) user;
           return c.clone();
       }
       if(user instanceof Motorista){
           Motorista m = (Motorista) user;
           return m.clone();
       }
       
       return null;
   }
   
   //Utilizadores do UMeR
   private Map<String,Utilizador> getUtilizadores(){
       utilizadores = new TreeMap<String,Utilizador>();
       
       for(Map.Entry<String,Utilizador> entry: this.utilizadores.entrySet())
            utilizadores.put(entry.getKey(),entry.getValue().clone());
            
       return utilizadores;
   }
   
   //veiculos da UMeR
   public Map<String,Veiculo> getVeiculo(){
       veiculo = new TreeMap<String,Veiculo>();
       
       for(Map.Entry<String,Veiculo> entry: this.veiculo.entrySet())
            veiculo.put(entry.getKey(),entry.getValue().clone());
       
       return veiculo;
   }
   

   //sets
   
   //Utilizador com login
   public void setUtilizador(Utilizador util){
       if(util instanceof Cliente)
            user = new Cliente ((Cliente) util);
       if(util instanceof Motorista)
            user = new Motorista ((Motorista) util);
   }
   
   //Utilizadores
   public void setUtilizadores(Map<String,Utilizador> utilizadores){
       this.utilizadores = new TreeMap<String,Utilizador>();
       for(Map.Entry<String,Utilizador> entry: utilizadores.entrySet())
            this.utilizadores.put(entry.getKey(), entry.getValue().clone());
   }
   
   //Veiculo da UMeR
   public void setVeiculo(Map<String,Veiculo> veiculo){
       this.veiculo = new TreeMap<String,Veiculo>();
       for(Map.Entry<String,Veiculo> entry: veiculo.entrySet())
            this.veiculo.put(entry.getKey(), entry.getValue().clone());
   }

   //Clone da UMeR
   public UMeR clone(){
       return new UMeR();
   }

   //Equals
   public boolean equals(Object o){
       if(o == this){
           return true;
       }
       if(o == null || o.getClass() != this.getClass()){
           return false;
       }
       UMeR u = (UMeR) o;
       return u.getUtilizadores().equals(utilizadores) &&
              u.getVeiculo().equals(veiculo) &&
              u.getUser().equals(user);
   }
   
   //Métodos
   
   /****Relativos ao inicio,registo e fecho de sessão****/
   
   //Registar utilizador
   public void registarUtilizador(Utilizador user) throws UtilizadorExistenteException{
       String mail = user.getEmail();
       if(utilizadores.containsKey(mail)){ throw new UtilizadorExistenteException("Utilizador já existente");}
       else utilizadores.put(mail,user);
   }
   
   //Iniciar a sessão
   public void iniciaSessao(String email, String password) throws SemAutorizacaoException{
       Utilizador u = utilizadores.get(email);
       if(u==null) {throw new SemAutorizacaoException("Utilizador não existente" + email);}
       if(u.getPass().equals(password)) this.user = u;
       else{ throw new SemAutorizacaoException("Password incorreta");}
    }
   
   //Fim da sessão
   public void fechaSessao(){
       this.user = null;
   }
   
   /****Relativos aos veiculos ****/
   
   //Criar e inserir veiculos
   public void insereVeiculo(Veiculo v) throws VeiculoExistenteException, SemAutorizacaoException{
      if(this.veiculo.containsKey(v.getMat())) throw new VeiculoExistenteException("Veiculo já existe\n");
      
      veiculo.put(v.getMat(),v);
   }
   
   //Associar o motorista ao veiculo
   public void associarMotVei(Motorista m, Veiculo v) throws SemAutorizacaoException{
       if(getTipoUtilizador() != 2) throw new SemAutorizacaoException("Cliente não pode aceder a área de Motorista");
       
       m.setVeiculo(v);
   }
}
   




