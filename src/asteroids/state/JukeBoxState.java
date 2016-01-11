/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.fundamentals.Input;
import asteroids.sound.MidiPlayer;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Yannic
 */
public class JukeBoxState implements State{
    String[] musics = {"Test","Credits_Theme","Credits_Theme","Credits_Theme"};
    String[] entries = {"Test","Credits Theme","Test2","Test3"};
    MidiPlayer player = MidiPlayer.getInstance();
    int currentSelection = 0;
    Input in;
    Color selectedEntryColor = new Color(1f, 1f, 1f);
    Color deselectedEntryColor = new Color(.5f, .5f, .5f);
    boolean upPressed = true;
    boolean downPressed = true;
    boolean actionPressed = true;
    public JukeBoxState(StateMachine s){
        
    }
  public void activateEntry()
  {
    switch(currentSelection)
    {
      case 0:
        if(player.running())player.stop();
        player.loadMidiFile(musics[0]);
        player.play();
        break;
      case 1:
        if(player.running())player.stop();
        player.loadMidiFile(musics[1]);
        player.play();
        break;
    }
  }

    @Override
    public void render(Graphics2D g) {
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
    public void update(double delta) {
        if (in.upDown)
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
    if (in.downDown)
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
    if(in.actionDown)
    {
      activateEntry();
    }
    }
    @Override
    public boolean initState(Input i) {
        in = i;
        in.actionDown = false;
        return true;
    }

    @Override
    public boolean exitState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
