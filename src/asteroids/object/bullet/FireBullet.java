package asteroids.object.bullet;

import asteroids.camera.Camera;
import asteroids.fundamentals.Image;
import asteroids.fundamentals.blend.BlendingComposite;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author nilsg
 */
public class FireBullet extends Bullet
{

  public FireBullet(Image bulletImage, boolean playerBullet, int damage,
                    int maxLife, double dx,
                    double dy,
                    int w, int h)
  {
    super(bulletImage, playerBullet, damage, maxLife, dx, dy, w,
            h, null, BulletType.FIRE);
  }

  @Override
  public void render(Graphics2D g, Camera c)
  {
    g.setComposite(BlendingComposite.add);
    AffineTransform save = g.getTransform();
    g.rotate(Math.toRadians(this.rotation), this.x, this.y);
    g.drawImage(this.bulletImage.getImage(), (int) (-c.x + this.x) - width / 2,
            (int) (-c.y + this.y) - height / 2, width, height, null);
    g.setTransform(save);
    g.setComposite(AlphaComposite.SrcOver);
  }

  @Override
  public void update(double delta)
  {
    if (lifeTime == 50 && listener != null)
    {
      listener.doAction(new BulletEvent(BulletType.FIRE, x, y, rotation,
              BulletEvent.BULLET_TIMER, this.bulletImage));
    }
    this.dx = Math.sin(Math.toRadians(rotation)) * 5 * delta;
    this.dy = -Math.cos(Math.toRadians(rotation)) * 5 * delta;
    super.update(delta);
  }

  @Override
  public Bullet copy()
  {
    FireBullet copy = new FireBullet(this.bulletImage, this.playerBullet,
            this.damage, this.maxLife, this.dx,
            this.dy, this.width, this.height);
    return copy;
  }
}
