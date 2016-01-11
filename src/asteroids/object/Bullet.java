/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.Asteroids;
import asteroids.camera.Camera;
import asteroids.fundamentals.CollisionBox;
import asteroids.fundamentals.CollisionCircle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *  Klasse für grundlegende Bullet-Objekte mit einer Kollision, einer Lebenszeit-Spanne, einer Grafik, Schaden, Position, Rotation und Geschwindigkeit.
 * @author nilsg
 */
public class Bullet extends GameObject implements Collidable
{
    final BufferedImage bulletImage;
    public final boolean playerBullet;
    final int damage;
    double lifeTime;
    final int maxLifetime;
    double dx;
    double dy;
    public boolean travel = true;
    float initialRot;
    double initialx;
    double initialy;
    
    PlayerShip p;
    
    public boolean collided;
    CollisionBox c;
    CollisionCircle cc;
    long creationTime;
    
    public Bullet(BufferedImage bulletImage, boolean playerBullet, int damage,
            int maxLifetime, double x, double y,double dx, double dy,float rot, char faction)
    {
        super(x, y,rot,bulletImage.getWidth(),bulletImage.getWidth());
        this.faction = faction;
        this.bulletImage = bulletImage;
        this.playerBullet = playerBullet;
        this.damage = damage;
        this.maxLifetime = maxLifetime;
        this.dx = dx;
        this.dy = dy;
        initialRot = rot;
        initialx = x;
        initialy = y;
        creationTime = System.currentTimeMillis();
        this.p = p;
        cc = new CollisionCircle(x,y,Math.sqrt(bulletImage.getHeight()*bulletImage.getHeight()+bulletImage.getWidth()*bulletImage.getWidth())/2);
    }


    @Override
    public void update(double delta)
    {
        if (!collided /*&& lifeTime<600*/) {
            lifeTime++;
            //rotation += 5*delta;
            //rotation %= 360;
            if (travel) {
                //dx+= 0.005;
                //dy+= 0.005;
                x += dx * Math.sin(Math.toRadians(rotation)) * delta;
                y -= dy * Math.cos(Math.toRadians(rotation)) * delta;
                
                Dimension d = Asteroids.getInstance().getDimensions();
                /*if (x < 0) {
                    x += d.width;

                } else if (x > d.width) {
                    x -= d.width;

                }
                if (y < 0) {
                    y += d.height;

                } else if (y > d.height) {
                    y -= d.height;

                }*/
                if(x<0||y<0||x>d.width||y>d.height)destroyMe = true;
                cc.setCoordinates(x, y);
            }
        }
    }

    @Override
    public void render(Graphics2D g, Camera c)
    {
        if(!collided){
        AffineTransform save = g.getTransform();
        g.rotate(Math.toRadians(this.rotation), x, y);
        g.drawImage(bulletImage , (int) (-c.x + x - bulletImage.getWidth(
                null) / 2),
                (int) (-c.y + y - bulletImage.getHeight(null) / 2), null);
        g.setTransform(save);
        g.setColor(Color.green);
        g.drawOval((int)(cc.x - cc.radius ), (int)(cc.y - cc.radius), (int)this.cc.radius*2,(int) this.cc.radius*2);
        }
    }
    


    @Override
    public void collided(Collidable c) {
        collided = true;
        destroyMe = true;
    }

    @Override
    public boolean collisionAvailable() {
        return !collided;
    }

    @Override
    public CollisionCircle getCollisionCircle() {
        return cc;
    }
    
}
