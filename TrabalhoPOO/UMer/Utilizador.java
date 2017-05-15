

public abstract class Utilizador{
   private String email; //email do user
   private String nome; //nome do user
   private String pass; //password do user
   private String morada; //morada do user
   private String data; //data de nascimento do user
   
   //Construtores
   public Utilizador() {
       email = " ";
       nome = " ";
       pass = " ";
       morada = " ";
       data = " ";
   }
   
   public Utilizador(String mail, String name, String passw, String moradas, String date){
       this.email = mail;
       this.nome = name;
       this.pass = passw;
       this.morada = moradas;
       this.data = date;
   }
    
   public Utilizador (Utilizador a){
       email = a.getEmail();
       nome = a.getNome();
       pass = a.getPass();
       morada = a.getMorada();
       data = a.getData();
   }
   
   //gets
   
   
   public String getEmail(){
       return email;
   }
   
   public String getNome(){
       return nome;
   }
   
   public String getPass(){
       return pass;
   }
   
   public String getMorada(){
       return morada;
   }
   
   public String getData(){
       return data;
   }
   
   //sets
   
   public void setEmail(String email){
       this.email = email;
   }
   
   public void setNome(String nome){
       this.nome = nome;
   }
   
   public void setPass(String pass){
       this.pass = pass;
   }
   
   public void setMorada(String morada){
       this.morada = morada;
   }
   
   public void setData(String data){
       this.data = data;
   }
   
   //Clone do Utilizador
   public abstract Utilizador clone();
   
   //Equals do Utilizador
   public boolean equal(Object o){
       if(o==this){
           return true;
       }
       if(o==null || o.getClass() != this.getClass()){
           return false;
       }
       Utilizador ut = (Utilizador) o;
       return ut.getEmail().equals(email)
              && ut.getNome().equals(nome)
              && ut.getPass().equals(pass)
              && ut.getMorada().equals(morada)
              && ut.getData().equals(data);
   }
   
   //toString do Utilizador
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Email").append(email).append("\n");
       sb.append("Nome").append(nome).append("\n");
       sb.append("Password").append(pass).append("\n");
       sb.append("Morada").append(morada).append("\n");
       sb.append("Data de Nascimento").append(data).append("\n");
       return sb.toString();
   }
}
