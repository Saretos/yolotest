/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.background;

import asteroids.fundamentals.Renderable;
import asteroids.fundamentals.Updateable;

/**
 *
 * @author nilsg
 */
public abstract class Background implements Renderable,Updateable
{
  /**
   * The Distance factor. the lower the slower the background moves
   */
    protected float movingFactor;
    /**
     * The Current X
     */
    protected float x;
    /**
     * The Current Y
     */
    protected float y;
}
