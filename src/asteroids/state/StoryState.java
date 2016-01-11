/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.fundamentals.Input;
import asteroids.object.GameObject;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nilsg
 */
public class StoryState implements State
{
    ArrayList<GameObject> objects;
    public StoryState()
    {
        objects = new ArrayList<GameObject>();
    }
    @Override
    public void render(Graphics2D g)
    {
        for(int i = 0; i < objects.size();i++)
        {
            GameObject o = objects.get(i);
        }
    }

    @Override
    public void update(double delta)
    {
    }

    @Override
    public boolean initState(Input i)
    {
        return true;
    }

    @Override
    public boolean exitState()
    {
        return true;
    }
    
}
