/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.fundamentals.Input;
import java.awt.Graphics2D;

/**
 *
 * @author nilsg
 */
public class SplashScreenState implements State
{

    @Override
    public void render(Graphics2D g)
    {

    }

    @Override
    public void update(double delta)
    {
    }

    @Override
    public boolean initState(Input i)
    {
        return false;
    }

    @Override
    public boolean exitState()
    {
       return false;
    }

}
