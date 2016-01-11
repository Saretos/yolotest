/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.gui;

import asteroids.camera.Camera;
import asteroids.object.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author nilsg
 */
public class Menu extends GameObject
{
  private final Color areaColor;
  private final Color borderColor;

  float currentWidth;
  float currentHeight;

  boolean isConstructing;
  boolean isConstructed;
  boolean isClosing;
  boolean isClosed;

  MenuEntry[] entries;

  /**
   *
   * @param x
   * @param y
   * @param rotation
   * @param width
   * @param height
   * @param c
   * @param entries
   */
  public Menu(double x, double y, float rotation, int width, int height, Color c,
              MenuEntry[] entries)
  {
    super(x, y, rotation, width, height);
    this.areaColor = c;
    this.borderColor = new Color(areaColor.getRed(),areaColor.getGreen(),areaColor.getGreen()).brighter();
    this.entries = entries;
    this.isClosed = true;
    this.isClosing = false;
    this.isConstructed = false;
    this.isConstructing = false;
  }

  @Override
  public void update(double delta)
  {
    if (isConstructing)
    {
      if (open(delta))
      {
        isConstructed = true;
        isConstructing = false;
      }
    }
    else if (isClosing)
    {
      if (close(delta))
      {
        isClosed = true;
        isClosing = false;
      }
    }
    else if (isConstructed)
    {
      
    }
  }

  @Override
  public void render(Graphics2D g, Camera c)
  {
    g.setColor(this.areaColor);
    g.fillRect((int) x, (int) y, (int)currentWidth,(int)currentHeight);
    g.setColor(borderColor);
    g.drawRect((int)x-1, (int)y-1, (int)currentWidth+1,(int)currentHeight+1);
  }

  public boolean open(double delta)
  {
    this.currentWidth += 3*delta;
    this.currentHeight += 3*delta;
    if (this.currentWidth >= width)
    {
      this.currentWidth = width;
    }
    if (this.currentHeight >= height)
    {
      this.currentHeight = height;
    }
    return currentHeight == height && currentWidth == width;
  }

  public boolean close(double delta)
  {
    this.currentWidth -= 3*delta;
    this.currentHeight -= 3*delta;
    if (this.currentWidth <= 0)
    {
      this.currentWidth = 0;
    }
    if (this.currentHeight <= 0)
    {
      this.currentHeight = 0;
    }
    if(currentHeight == 0 && currentWidth == 0)
    {
      return true;
    }
    return false;
  }
  
  public void setConstructing()
  {
    this.isConstructing = true;
  }
}
