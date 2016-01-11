/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.fundamentals;

/**
 *
 * @author Yannic
 */
public class CollisionBox implements Collision{
    public double x;
    public double y;
    public double width;
    public double height;
    
    public CollisionBox(double x, double y, double w, double h){
        setCoordinates(x, y);
        this.width = w;
        this.height = h;
    }
    
    public void setCoordinates(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public boolean checkCollision(CollisionBox c, boolean reverse){
        boolean b = (bCheck(c.x,x,x+width)||bCheck(c.x+c.width,x,x+width))&&(bCheck(c.y,y,y+height)||bCheck(c.y+c.height, y, y+height));
        if(reverse){
            return b||c.checkCollision(this, false);
        }else{
            return b;
        }
    }
    
    //betweenCheck
    public boolean bCheck(double c,double min, double max){
        return c>=min&&c<=max;
    }
    
    @Override
    public String toString(){
        return "CollisionBox @ "+x+","+y+" Width: "+width+" Height: "+height;
    }
}
    
    
