package asteroids.fundamentals;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author nilsg
 */
public class Input implements KeyListener
{

  public final int LEFT = KeyEvent.VK_A;
  public final int RIGHT = KeyEvent.VK_D;
  public final int UP = KeyEvent.VK_W;
  public final int DOWN = KeyEvent.VK_S;

  public final int SPACE = KeyEvent.VK_SPACE;

  public final int LEFT2 = KeyEvent.VK_LEFT;
  public final int RIGHT2 = KeyEvent.VK_RIGHT;
  public final int UP2 = KeyEvent.VK_UP;
  public final int DOWN2 = KeyEvent.VK_DOWN;

  public boolean leftDown;
  public boolean rightDown;
  public boolean upDown;
  public boolean downDown;

  public boolean actionDown;
  public boolean jumpDown;
  public boolean abortDown;

  public boolean spaceDown;

  @Override
  public void keyTyped(KeyEvent e)
  {
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    switch (e.getKeyCode())
    {
      case KeyEvent.VK_CONTROL:
        actionDown = true;
        break;
      case KeyEvent.VK_ALT:
        jumpDown = true;
        break;
      case KeyEvent.VK_ESCAPE:
        abortDown = true;
        break;
      case KeyEvent.VK_ENTER:
        actionDown = true;
        break;
      case LEFT:
        leftDown = true;
        break;
      case RIGHT:
        rightDown = true;
        break;
      case DOWN:
        downDown = true;
        break;
      case UP:
        upDown = true;
        break;
      case LEFT2:
        leftDown = true;
        break;
      case RIGHT2:
        rightDown = true;
        break;
      case DOWN2:
        downDown = true;
        break;
      case UP2:
        upDown = true;
        break;
      case SPACE:
        spaceDown = true;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    switch (e.getKeyCode())
    {
      case KeyEvent.VK_CONTROL:
        actionDown = false;
        break;
      case KeyEvent.VK_ALT:
        jumpDown = false;
        break;
      case KeyEvent.VK_ESCAPE:
        abortDown = false;
        break;
      case KeyEvent.VK_ENTER:
        actionDown = false;
        break;
      case LEFT:
        leftDown = false;
        break;
      case RIGHT:
        rightDown = false;
        break;
      case DOWN:
        downDown = false;
        break;
      case UP:
        upDown = false;
        break;
      case LEFT2:
        leftDown = false;
        break;
      case RIGHT2:
        rightDown = false;
        break;
      case DOWN2:
        downDown = false;
        break;
      case UP2:
        upDown = false;
        break;
      case SPACE:
        spaceDown = false;
        break;
    }
  }

}
