/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.fundamentals.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author nilsg
 */
public class Sprite implements Image
{
    BufferedImage img;
    public Sprite(BufferedImage img)
    {
        this.img = img;
    }
    @Override
    public java.awt.Image getImage()
    {
        return img;
    }

    @Override
    public void update(double delta)
    {
    }
    
}
