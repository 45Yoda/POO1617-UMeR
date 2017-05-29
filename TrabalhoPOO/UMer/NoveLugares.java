
public class NoveLugares extends Veiculo{
   
   public NoveLugares(){
       super();
   }
   
   public NoveLugares(String matricula,int vMed, int preco, int factorF){
       super(vMed,preco,factorF,matricula);
   }
   
   public NoveLugares(NoveLugares nl){
       super(nl);
   }
   
   
   public NoveLugares clone(){
       return new NoveLugares();
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
       }
       if((o==null) || o.getClass() != this.getClass()){
           return false;
       }
       NoveLugares nl = (NoveLugares) o;
       return super.equals(o);
   }
    
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
       
       return sb.toString();
   }
}
