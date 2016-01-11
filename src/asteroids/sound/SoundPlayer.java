/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.sound;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 *
 * @author nilsg
 */
public class SoundPlayer
{

  private static SoundPlayer instance;

  private final String SFX_PATH;
  private HashMap<String, Clip> cache;

  private SoundPlayer()
  {
    cache = new HashMap<>();
    SFX_PATH = "/sfx/";
  }

  public boolean playSound(String soundFile)
  {
    if (cache.containsKey(soundFile))
    {
      Clip c = (cache.get(soundFile));
      c.setFramePosition(0);
      play(c);
      return true;

    }
    else
    {
      return false;
    }
  }

  /**
   * Searches in the res/sfx for the soundfile and plays it
   *
   * @param fileName the filename
   *
   * @return true if the sound could be started playing
   */
  public boolean playSoundFile(String fileName)
  {
    AudioInputStream ais = null;
    try
    {
      ais = AudioSystem.getAudioInputStream(
              this.getClass().getClassLoader().getResource(
                      SFX_PATH + fileName + ".wav"));

      Clip clip = AudioSystem.getClip();
      clip.open(ais);
      play(clip);
      return true;
    }
    catch (Exception ex)
    {
      Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null,
              ex);
    }
    finally
    {
      try
      {
        ais.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return false;
  }

  /**
   * Preloads a soundFile
   *
   * @param soundFile the name soundfile
   *
   * @return true if the Sound file could be loaded
   */
  public boolean loadSound(String soundFile)
  {
    AudioInputStream ais = null;
    try
    {
      ais = AudioSystem.getAudioInputStream(
              this.getClass().getResource(
                      SFX_PATH + soundFile + ".wav"));
      Clip clip = AudioSystem.getClip();
      clip.open(ais);
      cache.put(soundFile, clip);
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        ais.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return false;
  }

  private void play(final Clip clip)
  {

    clip.addLineListener(new LineListener()
    {

      @Override
      public void update(LineEvent event)
      {
        if (event.getType().equals(LineEvent.Type.STOP))
        {
          clip.stop();
        }
      }
    });
    clip.start();
  }

  public static SoundPlayer getInstance()
  {
    if (instance == null)
    {
      instance = new SoundPlayer();
    }
    return instance;
  }
}
