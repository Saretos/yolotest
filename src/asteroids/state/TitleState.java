package asteroids.state;

import asteroids.background.Background;
import asteroids.background.PictureBackground;
import asteroids.fundamentals.Input;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author nilsg
 */
public class TitleState implements State
{
  
  Background[] backgrounds;
  private final StateMachine stateMachine;
  Color selectedEntryColor = new Color(1f, 1f, 1f);
  Color deselectedEntryColor = new Color(.5f, .5f, .5f);
  Input input;
  int currentSelection;

  boolean upPressed;
  boolean downPressed;

  String[] entries = new String[]
  {
    "Arcade", "Highscores","Options", "Credits" ,"Jukebox", "Exit"
  };

  public TitleState(StateMachine stateMachine)
  {
    this.stateMachine = stateMachine;
    backgrounds = new Background[]{new PictureBackground("Title")};

  }

  @Override
  public void render(Graphics2D g)
  {
    for(Background bg : backgrounds)
    {
      bg.render(g, null);
    }
    for (int i = 0; i < entries.length; i++)
    {
      if (i == currentSelection)
      {
        g.setColor(selectedEntryColor);
      }
      else
      {
        g.setColor(deselectedEntryColor);
      }
      g.drawString(entries[i], 300, 300 + i * 40);
    }
  }

  @Override
  public void update(double delta)
  {
    if (input.upDown)
    {
      if (!upPressed)
      {
        currentSelection--;
        if (currentSelection < 0)
        {
          currentSelection = entries.length - 1;
        }
        upPressed=true;
      }
    }
    else
    {
      upPressed = false;
    }
    if (input.downDown)
    {
      if (!downPressed)
      {
        currentSelection++;
        if (currentSelection >= entries.length)
        {
          currentSelection = 0;
        }
        downPressed=true;
      }
    }
    else
    {
      downPressed = false;
    }
    if(input.actionDown)
    {
      activateEntry();
    }
  }

  @Override
  public boolean initState(Input i)
  {
    this.input = i;
    return true;
  }

  @Override
  public boolean exitState()
  {
    return false;
  }

  private void activateEntry()
  {
    switch(currentSelection)
    {
      case 0: // Arcade
        stateMachine.setState(new ArcadeState(stateMachine));
        break;
      case 1: // Highscores
        stateMachine.setState(new ScoreboardState(stateMachine));
        break;
      case 2:
         stateMachine.setState(new OptionsState(stateMachine));
      case 3: // Credits
        stateMachine.setState(new CreditsState(stateMachine));
        break;
      case 4: // Jukebox
        stateMachine.setState(new JukeBoxState(stateMachine));
        break;
      case 5: // Exit
        System.exit(0);
        break;
    }
  }

}