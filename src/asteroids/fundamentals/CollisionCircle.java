/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.fundamentals;

import java.awt.Color;
import java.awt.Graphics;

/**
 *Umsetzung von Kollision als Kreis um ein Objekt. Kollisionserkennung durch Überprüfung der folgenden Bedingung:
 * Radius(a)+Radius(b) kleiner geometrischer Abstand (Satz des Pythagoras) = Kollision (Die Kreise schneiden sich)
 * Diese Überprüfung wird über die checkCollision-Methode durchgefuehrt, welche in der Arcade-State aufgerufen wird.
 * @author Yannic
 */
public class CollisionCircle implements Collision{
    public double x;
    public double y;
    public double radius;
    private double add = 0.01;
    private double cur = 1;
    
    public CollisionCircle(double x, double y, double radius){
        setCoordinates(x, y);
        setRadius(radius);
    }
    
    @Override
    public void setCoordinates(double x, double y) {
        this. x = x;
        this.y = y;
    }
    
    public void render(Graphics g){
        /*g.setColor(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
        //double r = Math.random()*radius*10; Quatsch
        if(cur >= 7)cur = 1;
        cur += add;
        g.drawOval((int)(x-cur*radius),(int)( y-cur*radius),(int) (cur*radius*2),(int) (cur*radius*2));
        if(cur>=2)g.drawOval((int)(x-(cur-1)*radius),(int)( y-(cur-1)*radius),(int) ((cur-1)*radius*2),(int) ((cur-1)*radius*2));
        if(cur>=3)g.drawOval((int)(x-(cur-2)*radius),(int)( y-(cur-2)*radius),(int) ((cur-2)*radius*2),(int) ((cur-2)*radius*2));
        */
        g.setColor(Color.blue);
        g.drawOval((int)(x-radius),(int) (y-radius),(int) (2*radius),(int) (2*radius));
    }
    
    
    public void setRadius(double r){
        radius = r;
    }
    
    /**
     * 
     * @param c <b>Collidable</b>-Objekt, gegen das die Kollision geprüft wird.
     * @return Gibt <b>true</b> zurück, falls es zu einer Kollision kommt, und <b>false</b>, falls nicht.
     */
    
    public boolean checkCollision(CollisionCircle c){
        double xDist = Math.abs(c.x-x);
        double yDist = Math.abs(c.y-y);
        if(Math.sqrt(xDist*xDist+yDist*yDist)<(c.radius+radius)){
        return true;
        }
        return false;
    }
    
}
