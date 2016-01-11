/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.camera;

import asteroids.object.GameObject;

/**
 *
 * @author Raildex
 */
public class Camera
{
    public double x;
    public double y;

    public int width;
    public int height;

    public Camera(double x, double y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    
    /**
     * Returns, if a specified Location is within the view of the Camera
     * @param t the transformComponent to check
     * @return true if the Transform Component is within the sight of the Camera
     */
    public boolean isInView(GameObject t)
    {
        return ((t.x >= this.x && t.x <= this.x + this.width) && (t.y >= this.y && t.y <= this.y + this.height));
    }

}
