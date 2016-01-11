/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.bullet;

import asteroids.camera.Camera;
import asteroids.fundamentals.Image;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Raildex
 */
public class Rocket extends Bullet
{

  public Rocket(Image bulletImage, boolean playerBullet, int damage, int maxLife,
                double dx, double dy, int w,
                int h, BulletListener listener)
  {
    super(bulletImage, playerBullet, damage, maxLife, dx, dy, w, h,
            listener, BulletType.ROCKET);
  }

  @Override
  public void render(Graphics2D g, Camera c)
  {
    AffineTransform save = g.getTransform();
    g.rotate(Math.toRadians(rotation), x, y);
    g.drawImage(bulletImage.getImage(), (int) x-width/2, (int) y-height/2, width, height, null);
    g.setTransform(save);
  }

  @Override
  public void update(double delta)
  {
    if (lifeTime <= 0)
    {
      listener.doAction(new BulletEvent(BulletType.ROCKET, x, y, this.rotation,
              BulletEvent.BULLET_FIRED, this.bulletImage));
    }
    if (lifeTime % 5 == 0)
    {
      listener.doAction(new BulletEvent(BulletType.ROCKET, x, y, this.rotation,
              BulletEvent.BULLET_TIMER, this.bulletImage));
    }
    this.dx = Math.sin(Math.toRadians(rotation)) *(-20+lifeTime)*0.2;
    this.dy = -Math.cos(Math.toRadians(rotation)) *(-20+lifeTime)*0.2;
    this.dx+= Math.sin(Math.toRadians(rotation+90))*(2-lifeTime/20);
    this.dy+= -Math.cos(Math.toRadians(rotation+90))*(2-lifeTime/20);
    super.update(delta);
  }

  @Override
  public Bullet copy()
  {
    return new Rocket(this.bulletImage, this.playerBullet, this.damage,
            this.maxLife, this.dx, this.dy, this.width, this.height, this.listener);
  }

}
