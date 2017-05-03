
public class Empresa{
    private String nomeEmpresa;
    private Veiculo[] taxis;
    private int nveiculos; //numero veiculos no stand
    
    //capacidade do stand (em número de veículos)
    private int capacidade;
    
    //Construtores
    public Empresa(){
        this.nomeEmpresa = " ";
        this.taxis = new Veiculo[capacidade];
        this.capacidade = 0;
        this.nveiculos = 0;
    }
    
    public Empresa(String nome, Veiculo[] taxis, int nveiculos, int capacidade){
        this.nomeEmpresa = nome;
        this.taxis = new Veiculo[capacidade];
        this.nveiculos = 0;
        this.capacidade = capacidade;
    }
    
    public Empresa(Empresa emp){
        this.nomeEmpresa = emp.getNomeEmp();
        this.taxis = emp.getTaxis();
        this.nveiculos = emp.getNVeiculos();
        this.capacidade = emp.getCapacidade();
    }
    
    //gets
    public String getNomeEmpresa(){
        return this.nomeEmpresa;
    }
    
    public Veiculo[] getTaxis(){
        Veiculo[] aux = new Veiculo[taxis.length];
        for(int i =0;i<taxis.length;i++)
            if(i<nveiculos)
                aux[i] = taxis[i].clone();
            else
                aux[i] = taxis[i];
                
        return aux;
    }
    
    public int getNVeiculos(){
        return this.nveiculos;
    }
    
    public int getCapacidade(){
        return this.capacidade;
    }
    
    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }
    
    public void setTaxis(Veiculo[] taxis){
        this.taxis = taxis;
    }
    
    public void setNVeiculos(int nveiculos){
        this.nveiculos = nveiculos;
    }
    
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    
    //Métodos auxiliares
    public void insereVeiculo(Veiculo v){
        if(!existeVeiculo(v) && nveiculos < taxis.length){
            taxis[nveiculos++] = v.clone();
        }
    }
    
    public boolean existeVeiculo(Veiculo v){
        int i;
        boolean r = false;
        for(i=0;i<nveiculos && !r;i++){
            if(taxis[i].equals(v)){
                r=true;
            }
        }
        
        return r;
    }
    
    //Método equals
    public boolean equals(Object o){
        if(this==o) return true;
        if((o==null) || this.getClass() != o.getClass()) return false;
        Empresa emp = (Empresa) o;
        return emp.getNomeEmpresa().equals(nomeEmpresa) &&
               emp.getTaxis().equals(taxis) &&
               emp.getNVeiculos() == nveiculos &&
               emp.getCapacidade() == capacidade;
    }
    
    //Método toString
    public String toString(){
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append("Nome Empresa: ").append(nomeEmpresa);
        sb.append("Número de veiculos: ").append(nveiculos);
        sb.append("Capacidade: ").append(capacidade);
        
        for(i=0;i<nveiculos;i++){
            sb.append("Táxis: ").append(taxis[i]);
        }
        
        return sb.toString();
    }
    
    //Método clone
    public Empresa clone(){
        return new Empresa();
    }
}
