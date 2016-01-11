/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.collect;

import asteroids.camera.Camera;
import asteroids.fundamentals.CollisionCircle;
import asteroids.object.Collidable;
import asteroids.object.GameObject;
import asteroids.object.PlayerShip;
import asteroids.object.sprite.Sprite;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Yannic
 */
public abstract class Collectible extends GameObject implements Collidable{

    Sprite image;
    CollisionCircle c;
    public Collectible(double x, double y, float rotation, int width, int height) {
        super(x, y, rotation, width, height);
    }

    @Override
    public void update(double delta) {
        
    }

    @Override
    public void render(Graphics2D g, Camera c) {
        g.drawImage(image.getImage(), (int) (-c.x + x - width / 2),
                (int) (-c.y + y - height / 2), width, height, null);
        this.c.render(g);
    }
    

    @Override
    public CollisionCircle getCollisionCircle() {
        return c;
    }

    @Override
    public void collided(Collidable c) {
        if(c instanceof PlayerShip)destroyMe = true;
    }

    @Override
    public boolean collisionAvailable() {
        return !destroyMe;
    }
}
