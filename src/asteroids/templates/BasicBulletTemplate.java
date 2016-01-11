/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.templates;

/**
 *
 * @author Yannic
 */
public class BasicBulletTemplate extends BulletTemplate{
    
    /**
     * Useless comment, Template is useless
     * Closed bullet issue (#6)
     * @param speed
     * @param survivalTime 
     */
    public BasicBulletTemplate()
    {
        this.speed = 15;
        this.survivalTime = 5000;
        System.out.println(this.speed);
    }
    
}
