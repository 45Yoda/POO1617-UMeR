

public class Mota extends Veiculo{

   
   public Mota(){
       super();
   }
   
   public Mota(int vMed, double preco, int factorF, String matricula, Localizacao loc,Motorista mot){
       super(vMed,preco,factorF,matricula,loc,mot);
       
   }
   
   public Mota(Mota l){
       super(l);
   }
   
   public Mota clone(){
       return new Mota(this);
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
       }
       if((o==null) || o.getClass() != this.getClass()){
           return false;
       }
       Mota l = (Mota) o;
       return super.equals(o);
   }
    
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
     
       return sb.toString();
   }
}