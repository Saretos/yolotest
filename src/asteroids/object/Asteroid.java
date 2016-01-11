package asteroids.object;

import asteroids.object.collect.ShieldUpgrade;
import asteroids.object.collect.FireRateUpgrade;
import asteroids.camera.Camera;
import asteroids.fundamentals.CollisionCircle;
import asteroids.fundamentals.Spawner;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Asteroid extends GameObject implements Collidable {

    private Image img;
    private double dx;
    private double dy;
    private int width;
    private int height;
    private double random;

    public boolean collided = false;

    private CollisionCircle collisionCircle;
    private Spawner s;

    public Asteroid(double x, double y, float rotation, int width, int height, double dx, double dy, Image img, double randomDrehung, Spawner s) {
        super(x, y, rotation, width, height);
        faction = 'A';
        this.img = img;
        /**
         * Berechnet zufällig ein dx und dy im Intervall [-0.4,0.4]
         */
        this.dx = dx;
        this.dy = dy;
        /**
         * Speichert die Sprite Größe (Breite, Höhe) zwischen um später keine verzerrten Sprites zu haben
         */
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.random = randomDrehung;
        this.s = s;
        collisionCircle = new CollisionCircle(x, y, Math.sqrt(this.width * this.width + this.height * this.height) / 2);
    }

    @Override
    public void update(double delta) {
        /**
         * Zufällige Drehgeschwindigkeit
         */
        this.rotation += -.2 + random * 0.4;
        x += dx * delta;
        y += dy * delta;

        /*Kollisions zeugs hier */
        collisionCircle.setCoordinates(x, y);

    }

    @Override
    public void render(Graphics2D g, Camera c) {
        AffineTransform save = g.getTransform();
        /**
         * Lässt das Sprite anhand eines vergleichs mit randomDrehung entweder
         * im Uhrzeigersinn oder entgegen dem Uhrzeigersinn drehen
         */
        if (random < 0.5) {
            g.rotate(Math.toRadians(rotation), x, y);
        } else {
            g.rotate(-Math.toRadians(rotation), x, y);
        }
        g.drawImage(img, (int) x - width / 2, (int) y - height / 2, width, height, null);
        g.setTransform(save);
        collisionCircle.render(g);
    }

    @Override
    public CollisionCircle getCollisionCircle() {
        return collisionCircle;
    }

    @Override
    public void collided(Collidable c)
    {
        if(Math.random()<0.5){
            s.spawn(new FireRateUpgrade(x, y, rotation, width, height));
        }else{
            s.spawn(new ShieldUpgrade(x, y, rotation, width, height));
        }
        collided = true;
        destroyMe = true;
    }

    @Override
    public boolean collisionAvailable() {
        return !collided;
    }

    @Override
    public String toString(){
        return "Asteroid @ X:" + x + " Y:" + y + " H:"+ height + " W:"+width;
    }
}
