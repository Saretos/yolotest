/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.fundamentals.Input;
import java.awt.Graphics2D;

/**
 *
 * @author Raildex
 */
public abstract interface State
{
  /**
   * Renders the State
   * @param g the Back Buffer
   */
  public void render(Graphics2D g);
  /**
   * Updates the State
     * @param delta the interpolation delta Time
   */
  public void update(double delta);
  /**
   * Inits the State
   * @param i
   * @return 
   */
  public boolean initState(Input i);
  /**
   * Cleans up memory when removing the state
   * @return 
   */
  public boolean exitState();

}
