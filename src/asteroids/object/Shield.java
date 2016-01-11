/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.camera.Camera;
import asteroids.fundamentals.CollisionCircle;
import asteroids.object.sprite.Sprite;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Yannic
 */
public class Shield extends GameObject implements Collidable{
    double orbitDistance = 1;
    CollisionCircle c;
    GameObject fixPoint;
    final double RADIUS = 15;
    double rotSpeed;
    Sprite sprite;
    public Shield(GameObject fix,double distance, float rotation,char faction, double RotationSpeed) {
        super(fix.x, fix.y, rotation,0,0);
        orbitDistance = distance;
        this.faction = faction;
        x = fix.x+Math.cos(Math.toRadians(rotation))*distance;
        y = fix.y-Math.sin(Math.toRadians(rotation))*distance;
        fixPoint = fix;
        rotSpeed = RotationSpeed;
        try{
            sprite = new Sprite(ImageIO.read(this.getClass().getResource("/gfx/bullets.png")).getSubimage(84, 198, 12, 12));
        }catch(IOException ioe){
        }
        this.height = sprite.getImage().getHeight(null);
        this.width = sprite.getImage().getWidth(null);
        c = new CollisionCircle(x,y,this.height);
    }
    
    public void setRotation(float r){
        rotation = r;
    }
    
    @Override
    public void update(double delta) {
        rotation =(float) (rotation+delta*rotSpeed)%360;
        x = fixPoint.x+Math.cos(Math.toRadians(rotation))*orbitDistance;
        y = fixPoint.y-Math.sin(Math.toRadians(rotation))*orbitDistance;
        c.setCoordinates(x, y);
    }

    @Override
    public void render(Graphics2D g, Camera c) {
        g.drawImage(sprite.getImage(), (int) (-c.x + x - width),
                (int) (-c.y + y - height), width*2, height*2, null);
        this.c.render(g);
    }


    @Override
    public CollisionCircle getCollisionCircle() {
        return c;
    }

    @Override
    public void collided(Collidable c) {
        destroyMe = true;    
        /*
        if(fixPoint instanceof PlayerShip){
            ((PlayerShip)fixPoint).shields.remove(this);
        }
        destroy();
        */
    }

    @Override
    public boolean collisionAvailable() {
        return !destroyMe;
    }
    
}
