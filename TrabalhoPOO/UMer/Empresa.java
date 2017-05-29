
public class Empresa{
    private String nomeEmpresa;
    private Veiculo[] taxis;
    private int nveiculos; //numero veiculos na empresa
    private Motorista[] motoristas;
    private int nmotoristas; //numero motoristas na empresa
    private int motocap; //capacidade de motoristas da empresa
    private int capacidade;//capacidade da empresa (em número de veículos)
    
    
    //Construtores
    public Empresa(){
        this.nomeEmpresa = " ";
        this.taxis = new Veiculo[capacidade];
        this.capacidade = 0;
        this.motocap=0;
        this.nveiculos = 0;
        this.nmotoristas=0;
        this.motoristas = new Motorista[motocap];
    }
    
    public Empresa(String nome, int capacidade,int motocap){
        this.nomeEmpresa = nome;
        this.taxis = new Veiculo[capacidade];
        this.nveiculos = 0;
        this.motocap=motocap;
        this.capacidade = capacidade;
        this.nmotoristas=0;
        this.motoristas = new Motorista[motocap];
    }
    
    public Empresa(Empresa emp){
        this.nomeEmpresa = emp.getNomeEmpresa();
        this.taxis = emp.getTaxis();
        this.nveiculos = emp.getNVeiculos();
        this.capacidade = emp.getCapacidade();
        this.nmotoristas=emp.getNMotoristas();
        this.motocap=emp.getMotocap();
        this.motoristas=emp.getMotoristas();
    }
    
    //gets
    public String getNomeEmpresa(){
        return this.nomeEmpresa;
    }
    
    public Veiculo[] getTaxis(){
        Veiculo[] aux = new Veiculo[taxis.length];
        for(int i =0;i<taxis.length;i++)
                aux[i] = taxis[i];
                
        return aux;
    }
    
    public Motorista[] getMotoristas(){
        Motorista[] aux = new Motorista[motoristas.length];
        for(int i =0;i<motoristas.length;i++)
                aux[i] = motoristas[i];
                
        return aux;
    }
    
    public int getNVeiculos(){
        return this.nveiculos;
    }
    
    public int getNMotoristas(){
        return this.nmotoristas;
    }
    
    public int getCapacidade(){
        return this.capacidade;
    }
    
    public int getMotocap(){
        return this.motocap;
    }
    
    
    //sets
    
    
    public void setNomeEmpresa(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }
    
    
    public void setTaxis(Veiculo[] taxis){
        this.taxis = taxis;
    }
    
    public void setMotoristas(Motorista[] motoristas) {
        this.motoristas=motoristas;
    }
    
    public void setNVeiculos(int nveiculos){
        this.nveiculos = nveiculos;
    }
    
    public void setNMotoristas(int nmotoristas) {
        this.nmotoristas=nmotoristas;
    }
    
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    
    public void setMotocap(int motocap){
        this.motocap=motocap;
    }
    
    //Métodos auxiliares
    public void insereVeiculo(Veiculo v){
        if(!existeVeiculo(v) && nveiculos < taxis.length){
            taxis[nveiculos++] = v;
        }
    }
    
    public void insereMotorista(Motorista m) {
        if(!existeMotorista(m) && nmotoristas<motocap) 
                    motoristas[nmotoristas++] = m;
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
    
    public boolean existeMotorista(Motorista m){
        int i;
        boolean r = false;
        for(i=0;i<nmotoristas && !r;i++){
            if(motoristas[i].equals(m)){
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
