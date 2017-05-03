import static java.lang.Math.abs;

public class Ponto2D{
    private double x, y;
    
    //Construtores
    //usa outro construtor
    public Ponto2D(){
        this(0.0, 0.0);
    }
    
    public Ponto2D(double cx, double cy){
        x = cx;
        y = cy;
    }
    
    public Ponto2D(Ponto2D p){
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
    
    //incremento das coordenadas
    public void incCoord(double dx, double dy){
        x += dx;
        y += dy;
    }
    
    //decremento das coordenadas
    public void decCoord(double dx, double dy){
        x -= dx;
        y -= dy;
    }
    
    //soma as coordenadas do ponto Parametro ao ponto receptor
    public void somaPonto(Ponto2D p){
        x += p.getX();
        y += p.getY();
    }
    
    //soma os valores parametro e devolve um novo ponto
    public Ponto2D somaPonto(double dx, double dy){
        return new Ponto2D(x += dx, y += dy);
    }
    
    //verifica se ambas as coordenadas são positivas
    public boolean coordPos(){
        return x > 0 && y > 0;
    }
    
    //Métodos auxiliares
    
    //verifica se os 2 pontos são iguais
    public boolean igual(Ponto2D p){
        if (p != null) return (x == p.getX() && y == p.getY());
        else return false;
    }
    
    //Método equals
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Ponto2D p = (Ponto2D) o;
        return (this.x == p.getX() && this.y == p.getY());
    }
    
    //toString do Ponto2D 
    public String toString(){
        return new String("Pt2D = " + x + ", " + y);
    }
            
    //Clone de Ponto2D
    public Ponto2D clone(){
        return new Ponto2D(this);
    }
    
    //Método hashCode
    public int hashCode(){
        return (int)(this.x*7 + this.y*11);
    }
}
