

public class Moto extends Veiculo{

   
   public Moto(){
       super();
   }
   
   public Moto(int vMed, int preco, int factorF, String matricula, Localizacao loc){
       super(vMed,preco,factorF,matricula,loc);
       
   }
   
   public Moto(Moto l){
       super(l);
   }
   
   public Moto clone(){
       return new Moto();
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
       }
       if((o==null) || o.getClass() != this.getClass()){
           return false;
       }
       Moto l = (Moto) o;
       return super.equals(o);
   }
    
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
     
       return sb.toString();
   }
}