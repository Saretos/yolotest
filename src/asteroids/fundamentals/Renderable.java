/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.fundamentals;

import asteroids.camera.Camera;
import java.awt.Graphics2D;

/**
 *
 * @author nilsg
 */
public interface Renderable
{
    public void render(Graphics2D g, Camera c);
}
