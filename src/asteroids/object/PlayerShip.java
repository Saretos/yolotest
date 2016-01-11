/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.object.collect.ShieldUpgrade;
import asteroids.object.collect.FireRateUpgrade;
import asteroids.object.bullet.Bullet;
import asteroids.Asteroids;
import asteroids.camera.Camera;
import asteroids.fundamentals.CollisionCircle;
import asteroids.fundamentals.Image;
import asteroids.fundamentals.Input;

import asteroids.fundamentals.Spawner;
import asteroids.sound.SoundPlayer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author nilsg
 */
public class PlayerShip extends Ship implements Collidable
{

  final float MAXANGLE = 90;
  int shotGunShotAmount = 2;
  final Input i;
  private long lastAdded = System.currentTimeMillis();
  public boolean collided = false;
  private Spawner s;
  private CollisionCircle cc;
  private long spawnInvincibility;
  public ArrayList<Shield> shields;
  private double aoeCooldown = 500;
  private double cooldown = 200;

  public PlayerShip(double x, double y, double maxAccel, double maxVel,
    float rot, int w, int h, Image img,
    Input i, Spawner s)
  {
    super(x, y, maxAccel, maxVel, rot, w, h, img);
    this.s = s;
    this.i = i;
    this.faction = 'P';
    cc = new CollisionCircle(x, y,
      Math.sqrt(width * width + height * height) / 2);
    shields = new ArrayList<>();
    spawnInvincibility = System.currentTimeMillis();
  }

  @Override
  public void collided(Collidable c)
  {
    if (System.currentTimeMillis() - spawnInvincibility < 1000)
    {
      return;
    }
    if (c instanceof FireRateUpgrade)
    {
      cooldown -= 10;
      return;
    }
    if (c instanceof ShieldUpgrade)
    {
      Shield t = new Shield(this, 100, 0, faction, 1);
      s.spawn(t);
      shields.add(t);
      return;
    }
    /*
        if(c instanceof StaticObject){
            ((ArcadeState)Asteroids.getInstance().stateMachine.currentState).objects.add(t);
            for(int i = 0;i<shields.size();i++){
                shields.get(i).setRotation((float)i/shields.size()*360);
            }
        }*/
    collided = true;
    destroyMe = true;
  }

  @Override
  public void render(Graphics2D g, Camera c)
  {
    //TODO: Rendering PLayer Ship @ Zeugs
    //System.out.println("Rendering "+this);
    AffineTransform save = g.getTransform();
    g.rotate(Math.toRadians(this.rotation), x, y);
    g.drawImage(img.getImage(), (int) (-c.x + x - width / 2),
      (int) (-c.y + y - height / 2), width, height, null);
    g.setTransform(save);
    cc.render(g);
  }

  /**
   * Reaktion auf Eingaben des Benutzers.
   *
   * @param delta
   */
  @Override
  public void update(double delta)
  {
    if (!collided)
    {
      super.update(delta);
      if (i.spaceDown && System.currentTimeMillis() - lastAdded > cooldown)
      {
        lastAdded = System.currentTimeMillis();
        try
        {
          Bullet b = new Bullet(ImageIO.read(new File("res/gfx/FIRE_0.png")),
            true, 5, 5000, x, y, 5, 5, rotation, this.faction);
          s.spawn(b);
          SoundPlayer.getInstance().playSound(Weapon.testWeapon.fireSound);
        }
        catch (IOException ex)
        {
          Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null,
            ex);
        }
      }
      if (i.upDown)
      {
        //this.accel += accel*delta;
        this.accel += 0.004f * delta;
        if (this.accel >= this.maxAccel)
        {
          this.accel = maxAccel;
        }
        dx += Math.sin(Math.toRadians(rotation)) * accel * delta;

        dy -= Math.cos(Math.toRadians(rotation)) * accel * delta;

      }
      else if (i.downDown)
      {
        /*this.accel -= 0.004f * delta;
            if (this.accel <0)
            {
                this.accel = 0;
            }
            dx -= Math.sin(Math.toRadians(rotation)) * accel * delta;

            dy += Math.cos(Math.toRadians(rotation)) * accel * delta;
            if(dx < 0){
                dx = 0;
            }
            if(dy > 0){
                dy = 0;
            }
            dx = 0;
            dy = 0;
            accel = 0;
         */
        this.dx *= 0.99;
        this.dy *= 0.99;
      }
      else
      {
        this.accel -= 0.04;
        if (this.accel < 0)
        {
          this.accel = 0;
        }
      }
      if (i.actionDown && System.currentTimeMillis() - lastAdded > aoeCooldown)
      {
        lastAdded = System.currentTimeMillis();
        for (int i = 0; i <= shotGunShotAmount; i++)
        {
          try
          {
            Bullet b = new Bullet(ImageIO.read(new File("res/gfx/FIRE_0.png")),
              true, 5, 5000, x, y, 5, 5,
              rotation - MAXANGLE / 2 + (float) i / shotGunShotAmount * MAXANGLE,
              this.faction);
            s.spawn(b);
          }
          catch (IOException ex)
          {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null,
              ex);
          }
        }
        /*angle=maxed?angle/2:angle*2;
            if(angle>MAXANGLE){
                angle = MAXANGLE;
                maxed = true;
            }else if(angle<4){
                angle = 4;
                maxed = false;
            }*/
        //SoundPlayer.getInstance().playSound(Weapon.testWeapon.fireSound);
        /*
            for (Weapon w : weapons.values())
            {
                if (w.fireTimer >= w.interval)
                {
                    fire(w);
                }
            }*/
      }
      if (i.leftDown)
      {
        rotation -= 1.95f * delta;
      }
      else if (i.rightDown)
      {
        rotation += 1.95f * delta;
      }
      if (x < 0)
      {
        x = Asteroids.getInstance().getDimensions().width + x;
      }
      else if (x > Asteroids.getInstance().getDimensions().width)
      {
        x = x % Asteroids.getInstance().getDimensions().width;
      }
      if (y < 0)
      {
        y = Asteroids.getInstance().getDimensions().height + y;
      }
      else if (y > Asteroids.getInstance().getDimensions().height)
      {
        y = y % Asteroids.getInstance().getDimensions().height;
      }
      cc.setCoordinates(x, y);
    }

  }

  @Override
  public String toString()
  {
    return "Player Ship @ X:" + x + " Y:" + y;
  }

  @Override
  public boolean collisionAvailable()
  {
    return !collided;
  }

  @Override
  public CollisionCircle getCollisionCircle()
  {
    return cc;
  }

  @Override
  public void fire(Weapon w)
  {
    /**
     * s.spawn()...
     */
  }
}
