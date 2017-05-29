import static java.lang.Math.abs;

public class Localizacao{
    private double x, y;
    
    //Construtores
    //usa outro construtor
    public Localizacao(){
        this(0.0, 0.0);
    }
    
    public Localizacao(double cx, double cy){
        x = cx;
        y = cy;
    }
    
    public Localizacao(Localizacao p){
        x = p.getX();
        y = p.getY();
    }
    
    //gets
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
   
    
    //Métodos auxiliares
 
    
    //Método equals
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Localizacao p = (Localizacao) o;
        return (this.x == p.getX() && this.y == p.getY());
    }
    
    //toString do Localizacao 
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Localização atual: ").append("(").append(x).append(",").append(y).append(")").append("\n");
        return sb.toString();
    }
            
    //Clone de Localizacao
    public Localizacao clone(){
        return new Localizacao(this);
    }
    
}
