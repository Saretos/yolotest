/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.sprite;

import asteroids.fundamentals.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author nilsg
 */
public class AnimatedSprite implements Image
{

    final BufferedImage[] img;
    float time;
    final float playBackSpeed;

    public AnimatedSprite(BufferedImage img[],float playbackSpeed)
    {
        this.img = img;
        this.playBackSpeed= playbackSpeed;
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
