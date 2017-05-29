
public class Viagem
{
    private double preco;
    private Localizacao inicial;
    private Localizacao fim;
    private Motorista motorista;
    private Cliente cliente;
    private Veiculo veiculo;
    private double distancia;
    
    public Viagem() {
        this.preco=0;
        this.inicial=null;
        this.fim=null;
        this.motorista=null;
        this.cliente=null;
        this.veiculo=null;
        this.distancia=0;
    }
    
    public Viagem(Viagem v) {
        this.preco=v.getPreco();
        this.inicial=v.getInicial();
        this.fim=v.getFim();
        this.motorista=v.getMotorista();
        this.cliente=v.getCliente();
        this.veiculo=v.getVeiculo();
        this.distancia=v.getDistancia();
    }
    
    public Viagem(double preco,Localizacao inicial,Localizacao fim,Motorista m, Cliente c,Veiculo v) {
        this.preco=preco;
        this.inicial=inicial;
        this.fim=fim;
        this.motorista=m;
        this.cliente=c;
        this.veiculo=v;
        this.setDistancia();
    }
    
    //gets
    
    public double getPreco() {return this.preco;}
    public Localizacao getInicial() {return this.inicial;}
    public Localizacao getFim() {return this.fim;}
    public Motorista getMotorista() {return this.motorista;}
    public Cliente getCliente() {return this.cliente;}
    public Veiculo getVeiculo() {return this.veiculo;}
    public double getDistancia() {return this.distancia;}
    
    
    public void setPreco(double p) {this.preco=p;}
    public void setInicial(Localizacao i) {this.inicial=i;}
    public void setFim(Localizacao f) {this.fim=f;}
    public void setMotorista(Motorista m) {this.motorista=m.clone();}
    public void setCliente(Cliente c) {this.cliente=c.clone();}
    public void setVeiculo(Veiculo v) {this.veiculo=v.clone();}
    public void setDistancia() {this.distancia=this.inicial.calculaDist(this.fim);}
    
     public Viagem clone(){
       return new  Viagem(this);
   }
   
    //Método equals
   public boolean equals(Object o){
       if(this==o) return true;
       if((o==null) || o.getClass() != this.getClass()) return false;
       Viagem v = (Viagem) o;
       return v.getPreco() == preco &&
              v.getInicial().equals(inicial) &&
              v.getFim().equals(fim) &&
              v.getMotorista().equals(motorista) &&
              v.getCliente().equals(cliente) &&
              v.getVeiculo().equals(veiculo) &&
              v.getDistancia() == distancia;
   }
   
   //Método toString
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Preço de viagem: ").append(preco).append("\n");
       sb.append("Localização inicial: ").append(inicial.toString()).append("\n");
       sb.append("Localizaçao final: ").append(fim.toString()).append(" )");
       sb.append("Veiculo: ").append(veiculo.toString()).append("\n");
       sb.append("Cliente: ").append(cliente.toString()).append("\n");
       sb.append("Motorista: ").append(motorista.toString()).append("\n");       
       sb.append("Distancia: ").append(distancia).append("\n");
       
       return sb.toString();
   }
}
