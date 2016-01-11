/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.bullet;

import asteroids.fundamentals.Image;
import asteroids.object.bullet.Bullet.BulletType;

/**
 *
 * @author Raildex
 */
public class BulletEvent
{
  /**
   * Event Type for The Bullet when Colliding with an enemy
   */
  public static final int COLLIDING_ENEMY = 1;
  /**
   * Event Type for the Bullet when Colliding with the Player
   */

  public static final int COLLIDING_PLAYER = 10;
  /**
   * Event Type for the Bullet when fired
   */
  public static final int BULLET_FIRED = 100;
  /**
   * Event Type for the Bullet when a certain Time is passed
   */
  public static final int BULLET_TIMER = 1000;
  /**
   * Event Type for the Bullet when the Bullet dies
   */

  public static final int BULLET_DIED = 10000;
  /**
   * The Event Type
   */
  public int type;
  /**
   * The Bullet Type
   */
  public BulletType t;
  
  public Image bulletImage;
  /**
   * The X where the Event is called
   */
  public double x;
  /**
   * The Y where the Event is called
   */
  public double y;
  /**
   * The Rotation of the Event caller
   */
  public float rot;

  public BulletEvent(BulletType bulletType, double x, double y, float rot,
                     int type,Image bulletImage)
  {
    this.rot = rot;
    this.t = bulletType;
    this.type = type;
    this.x = x;
    this.y = y;
    this.bulletImage = bulletImage;
  }

}
