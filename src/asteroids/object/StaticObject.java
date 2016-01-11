/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.camera.Camera;
import asteroids.fundamentals.CollisionBox;
import asteroids.fundamentals.CollisionCircle;
import asteroids.fundamentals.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *  Statisches Testobjekt mit Kollisionsbox zum Test vom Spielfunktionen (Aktuelle Grafik: Asteroid)
 * @author Yannic
 */
public class StaticObject extends GameObject implements Collidable{
    
    Image img;
    public boolean collided = false;
    CollisionBox c;
    CollisionCircle cc;
    double dx = 0;
    double dy = 0;
    
    public StaticObject(double x, double y, Image img){
        super(x,y,0,img.getImage().getWidth(null),img.getImage().getHeight(null));
        this.img = img;
        cc = new CollisionCircle(x,y,Math.max(img.getImage().getWidth(null), img.getImage().getHeight(null))/2);
        
    }
    
    @Override
    public void update(double delta) {
        
    }

    @Override
    public void render(Graphics2D g, Camera c) {
        if(!collided){
            AffineTransform save = g.getTransform();
        g.rotate(Math.toRadians(this.rotation), x, y);
        g.drawImage(img.getImage(), (int) (-c.x + x - img.getImage().getWidth(
                null) / 2),
                (int) (-c.y + y - img.getImage().getHeight(null) / 2), null);
        g.setTransform(save);
        g.setColor(Color.red);
        g.drawOval((int)(cc.x-img.getImage().getWidth(null)/2),(int) (cc.y - img.getImage().getHeight(null) / 2),(int) cc.radius*2,(int) cc.radius*2);
        }
    }


    @Override
    public void collided(Collidable c) {
        collided = true;
        destroyMe = true; //s. GameObject-Klasse
    }

    @Override
    public boolean collisionAvailable() {
        return !collided;
    }
    
    @Override
    public String toString(){
        return "StaticObject";
    }

    @Override
    public CollisionCircle getCollisionCircle() {
        return cc;
    }
}
