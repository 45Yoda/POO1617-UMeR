
public class Carrinha extends Veiculo{

   
   public Carrinha(){
       super();
   }
   
   public Carrinha(int vMed, int preco, int factorF, String matricula){
       super(vMed,preco,factorF,matricula);
       
   }
   
   public Carrinha(Carrinha l){
       super(l);
   }
   
   public Carrinha clone(){
       return new Carrinha(this);
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
       }
       if((o==null) || o.getClass() != this.getClass()){
           return false;
       }
       Carrinha l = (Carrinha) o;
       return super.equals(o);
   }
    
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
     
       return sb.toString();
   }
}

