/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.object.bullet.Bullet;

/**
 *
 * @author Raildex
 */
public abstract class Weapon
{
  /**
   * Test Weapon
   */
  public static Weapon testWeapon = new Weapon(1,1) {
    @Override
    public void setLevel(int level)
    {
    }
  };
  
  /**
   * Level of the Weapon
   */
  public int level;
  /**
   * Cost (Shop?) per Level Upgrade
   */

  public final int costPerLevel;

  /**
   * Shotgun styled Spray
   */
  public boolean spray;
  /**
   * Shots into the Direction the ship is heading and 180°
   */
  public boolean doubleSide;
  
  /**
   * The number of Bullets shot when fired
   */
  public byte numBullets;
  /**
   * The Power Consumption per shot per Level
   */
  public int powerPerLevel;
  /**
   * Damage dealt per Level
   */
  public final int damagePerLevel;
  /**
   * The Bullet to be fired
   */
  public Bullet bullet;
  /**
   * The amount of time until the weapon can be fired again
   */
  public int interval;
  /**
   * timer for shooting again
   */
  public double fireTimer;
  /**
   * The Sound file to be played when the weapon is fired
   */
  public String fireSound;
  /**
   * The amount of inaccuracy
   */
  public float inaccuracy;

  public Weapon(int costPerLevel, int damagePerLevel)
  {
    this.costPerLevel = costPerLevel;
    this.damagePerLevel = damagePerLevel;
    setLevel(0);
  }

  public abstract void setLevel(int level);

  void update(double delta)
  {
    this.fireTimer += delta;
    if (fireTimer >= interval)
    {
      fireTimer = interval;
    }
  }

  public static enum Type
  {

    FRONT, REAR, LEFT, RIGHT
  }
}
