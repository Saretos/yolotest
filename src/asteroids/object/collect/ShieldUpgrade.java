/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.collect;

import asteroids.fundamentals.CollisionCircle;
import asteroids.object.sprite.Sprite;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Yannic
 */
public class ShieldUpgrade extends Collectible{

    public ShieldUpgrade(double x, double y, float rotation, int width, int height) {
        super(x, y, rotation, width, height);
        try{
            image = new Sprite(ImageIO.read(this.getClass().getResource("/gfx/bullets.png")).getSubimage(72, 155, 12, 12));
        }catch(IOException ioe){
        }
        this.height = image.getImage().getHeight(null);
        this.width = image.getImage().getWidth(null);
        c = new CollisionCircle(x,y,Math.sqrt(this.width*this.width+this.height*this.height)/2);
    }
    
}
