/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.fundamentals.Image;
import java.util.HashMap;

/**
 *
 * @author nilsg
 */
public abstract class Ship extends GameObject
{

    protected final Image img;
    protected ShipGenerator eng;
    protected double accel;
    protected final double maxAccel;
    protected final double maxVelocity;

    protected double dx;
    protected double dy;
    
    protected HashMap<Weapon.Type,Weapon> weapons;
    
    public Ship(double x, double y, double maxAccel, double maxVel, float rot,
            int w, int h, Image img)
    {
        super(x, y, rot, w, h);
        this.maxAccel = maxAccel;
        this.maxVelocity = maxVel;
        this.img = img;
        weapons = new HashMap<>();
    }

    @Override
    public void update(double delta)
    {
        img.update(delta);
        eng.update(delta);
        if (dx >= maxVelocity)
        {
            dx = maxVelocity;
        } else if (dx <= -maxVelocity)
        {
            dx = -maxVelocity;
        }
        if (dy >= maxVelocity)
        {
            dy = maxVelocity;
        } else if (dy <= -maxVelocity)
        {
            dy = -maxVelocity;
        }
        x += dx * delta;
        y += dy * delta;
        for(Weapon w : weapons.values())
        {
          w.update(delta);
        }
    }

    public final void setGenerator(ShipGenerator generator)
    {
        this.eng = generator;
    }
    
    public final void setWeapon(Weapon.Type t,Weapon w)
    {
      this.weapons.put(t, w);
    }
    
    abstract public void fire(Weapon w);
}
