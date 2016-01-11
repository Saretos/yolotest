/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroids.object.gui;

import asteroids.camera.Camera;
import asteroids.fundamentals.Image;
import asteroids.fundamentals.Renderable;
import java.awt.Graphics2D;

/**
 *
 * @author Raildex
 */
public class Window implements Renderable
{
  int x;
  int y;
  int w;
  int h;
  Image background;
  Image borderImage;

  @Override
  public void render(Graphics2D g, Camera c)
  {
    g.drawImage(background.getImage(), x-w/2, y-h/2,w,h,null);
  }

}
