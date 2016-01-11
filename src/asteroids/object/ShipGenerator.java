/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.fundamentals.Updateable;

/**
 *
 * @author nilsg
 */
public class ShipGenerator implements Updateable
{
    String generatorName;
    final float powerReplenish;
    float power;
    final int maxPower;

    public ShipGenerator(String name,float powerReplenish, int maxPower)
    {
        this.generatorName = name;
        this.powerReplenish = powerReplenish;
        this.maxPower = maxPower;
    }
    

    @Override
    public void update(double delta)
    {
        power+= powerReplenish*delta;
    }
    
    @Override
    public String toString()
    {
        return "Ship Generator["+generatorName+" @ "+maxPower+" Power Units ]";
    }
}
