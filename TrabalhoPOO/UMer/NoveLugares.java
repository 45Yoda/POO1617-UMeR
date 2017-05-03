
public class NoveLugares extends Veiculo{
   private int vMed; //Velocidade média por km
   private int preco; //preço base por km
   private int factorF; //factor de fiabilidade
   
   public NoveLugares(){
       super();
       this.vMed = 0;
       this.preco = 0;
       this.factorF = 0;
   }
   
   public NoveLugares(String matricula,int vMed, int preco, int factorF){
       super(matricula);
       this.vMed = vMed;
       this.preco = preco;
       this.factorF = factorF;
   }
   
   public NoveLugares(NoveLugares nl){
       super(nl);
       this.vMed = nl.getVMed();
       this.preco = nl.getPreco();
       this.factorF = nl.getFactorF();
   }
   
   //gets
   public int getVMed(){
       return this.vMed;
   }
   
   public int getPreco(){
       return this.preco;
   }
   
   public int getFactorF(){
       return this.factorF;
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
       return super.equals(o) &&
              nl.getVMed() == vMed &&
              nl.getPreco() == preco &&
              nl.getFactorF() == factorF;
   }
    
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString());
       sb.append("Velocidade Média: ").append(vMed).append("\n");
       sb.append("Preço Base: ").append(preco).append("\n");
       sb.append("Factor Fiabilidade: ").append(factorF).append("\n");
       
       return sb.toString();
   }
}
