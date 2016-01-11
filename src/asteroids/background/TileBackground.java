/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.background;

import asteroids.camera.Camera;
import java.awt.Graphics2D;

/**
 *
 * @author nilsg
 */
public class TileBackground extends Background
{
    char[][] tiles;
    
    @Override
    public void render(Graphics2D g, Camera c)
    {
        for(int i =0 ; i< tiles.length;i++)
        {
            for(int j = 0; j < tiles[i].length;j++)
            {
                
            }
        }
    }

    @Override
    public void update(double delta)
    {
        
    }
    
}
