/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.fundamentals.Input;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Raildex
 */
public class StateMachine
{

  private final Input i;
  public State currentState;

  public StateMachine(Input i)
  {
    this.i = i;
  }

  public void setState(State e)
  {
    if (currentState != null)
    {
      currentState.exitState();
    }
    e.initState(i);
    currentState = e;
  }

  public State getState()
  {
    return currentState;
  }

  public void render(Graphics2D g)
  {
    currentState.render(g);
  }

  public void update(double delta)
  {
    currentState.update(delta);
  }

}
