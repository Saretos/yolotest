/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.sound;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 *
 * @author Raildex
 */
public class MidiPlayer
{

  private static MidiPlayer instance;
  final String MIDI_PATH;
  Sequencer s;
  Sequence seq;
  private MidiPlayer()
  {
    MIDI_PATH = "/midi/";
    try
    {
      s = MidiSystem.getSequencer();
    }
    catch (MidiUnavailableException ex)
    {
      ex.printStackTrace();
    }
  }

  public void loadMidiFile(String name)
  {
    try
    {
      s.open();
      seq = MidiSystem.getSequence(this.getClass().getResourceAsStream(
              MIDI_PATH + name + ".mid"));
      s.setSequence(seq);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void play()
  {
    s.start();
  }

  public void stop()
  {
    s.stop();
  }
  
  public boolean running(){
      return s.isRunning();
  }
  
  public long getTickPosition()
  {
    return s.getMicrosecondPosition();
  }

  public static MidiPlayer getInstance()
  {
    if (instance == null)
    {
      instance = new MidiPlayer();
    }
    return instance;
  }

}
