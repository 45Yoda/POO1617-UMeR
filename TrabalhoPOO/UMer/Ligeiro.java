
public class Ligeiro extends Veiculo{
   private int vMed; //Velocidade média por km
   private int preco; //preço base por km
   private int factorF; //factor de fiabilidade
   
   public Ligeiro(){
       super();
       this.vMed = 0;
       this.preco = 0;
       this.factorF = 0;
   }
   
   public Ligeiro(String matricula,int vMed, int preco, int factorF){
       super(matricula);
       this.vMed = vMed;
       this.preco = preco;
       this.factorF = factorF;
   }
   
   public Ligeiro(Ligeiro l){
       super(l);
       this.vMed = l.getVMed();
       this.preco = l.getPreco();
       this.factorF = l.getFactorF();
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
   
   public Ligeiro clone(){
       return new Ligeiro();
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
       }
       if((o==null) || o.getClass() != this.getClass()){
           return false;
       }
       Ligeiro l = (Ligeiro) o;
       return super.equals(o) &&
              l.getVMed() == vMed &&
              l.getPreco() == preco &&
              l.getFactorF() == factorF;
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
