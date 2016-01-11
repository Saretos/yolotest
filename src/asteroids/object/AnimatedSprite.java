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
public class AnimatedSprite implements Image
{

    BufferedImage[] img;
    float time;
    float playBackSpeed;

    public AnimatedSprite(BufferedImage img[])
    {
        this.img = img;
    }

    @Override
    public java.awt.Image getImage()
    {
        return img[(int)time];
    }

    @Override
    public void update(double delta)
    {
        time += playBackSpeed * delta;
        if (time >= img.length)
        {
            time = 0;
        }
    }

}
