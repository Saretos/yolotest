/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.background;

import asteroids.camera.Camera;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nilsg
 */
public class PictureBackground extends Background
{
    BufferedImage img;
    public PictureBackground(String img)
    {
        try
        {
            this.img = ImageIO.read(this.getClass().getResourceAsStream("/gfx/background/"+img+".png"));
        } catch (IOException ex)
        {
            Logger.getLogger(PictureBackground.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    @Override
    public void render(Graphics2D g, Camera c)
    {
        g.drawImage(img, 0, 0,null);
    }

    @Override
    public void update(double delta)
    {
    }
    
}
