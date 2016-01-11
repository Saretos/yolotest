/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids;

/**
 * Merged
 */
import asteroids.fundamentals.Input;
import asteroids.state.StateMachine;
import asteroids.state.TitleState;

import javax.swing.*;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author YannicK
 */
public class Asteroids implements Runnable
{

  private static Asteroids instance;
  public String title;
  private Input input;
  private boolean running;

  /**
   * Screen Width
   */
  public int WIDTH = 1280;
  /**
   * Screen Height
   */
  public int HEIGHT = 720;
  /**
   * State Machine to control the Game
   */
  public StateMachine stateMachine;

  /**
   * The Window everything is drawn on.
   */
  JFrame window;
  /**
   * The Backbuffer for double buffering
   */
  VolatileImage backBuffer;
  /**
   * The Graphics Object to draw on the BackBuffer
   */
  Graphics2D backBufferGraphics;

  /**
   * Private Constructor
   */
  private Asteroids()
  {
    this.title = "Asteroids";
  }

  @Override
  public void run()
  {
    init();
    gameLoop();
    cleanUp();
  }

  /**
   * Init Method. Here we detect our currently running device to configure the
   * Game automatically
   */
  private void init()
  {
    window = new JFrame(title);
    window.setSize(WIDTH, HEIGHT);
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    GraphicsConfiguration gc = gd.getDefaultConfiguration();
    backBuffer = gc.createCompatibleVolatileImage(WIDTH, HEIGHT,
      VolatileImage.OPAQUE);
    backBufferGraphics = (Graphics2D) backBuffer.getGraphics();
    input = new Input();
    stateMachine = new StateMachine(input);
    //stateMachine.setState(new ScoreboardState(stateMachine));
    stateMachine.setState(new TitleState(stateMachine));
    window.setResizable(false);
    window.setUndecorated(true);
    window.setLocation(50, 50);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.addKeyListener(input);
    //TODO: Vollbild
    /*
      if(gd.isFullScreenSupported())
    {
        gd.setFullScreenWindow(window);
        this.WIDTH = gd.getDisplayMode().getWidth();
        this.HEIGHT = gd.getDisplayMode().getHeight();
    }
     */
    window.setVisible(true);
    running = true;
  }

  /**
   * GameLoop Method. Is running at TARGET_FPS FPS and uses interpolated logic
   * with delta Time
   */
  private void gameLoop()
  {
//    long lastLoopTime = System.currentTimeMillis();
//    final long TARGET_FPS = 30;
//    final long OPTIMAL_TIME = 1000000L / TARGET_FPS;
//    double delta = 0;
//    long updateLength;
//    long now;
//    while (running)
//    {
//      now = System.currentTimeMillis();
//      delta = (double) (now - lastLoopTime);
//      update(delta / ((double) OPTIMAL_TIME));
//      render(backBufferGraphics);
//      lastLoopTime = now;
////      try
////      {
////        Thread.sleep(
////          (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000000);
////      }
////      catch (Exception e)
////      {
////      }
//    }
      long lastLoopTime = System.nanoTime();
      final int TARGET_FPS = 60;
      final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

      while (running)
      {
          long now = System.nanoTime();
          long updateLength = now - lastLoopTime;
          lastLoopTime = now;
          double delta = updateLength / ((double) OPTIMAL_TIME);
          update(delta);
          render(backBufferGraphics);
          try
          {
              Thread.sleep(
                      (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000000);
          }
          catch (Exception e)
          {
          }
      }
  }

  /**
   * Clean up memory and destroy the game
   */
  private void cleanUp()
  {
  }

  /**
   * Update Method to update Game Logic
   *
   * @param delta the interpolation delta time
   */
  private void update(double delta)
  {
    stateMachine.update(delta);
  }

  /**
   * Render method to draw onto screen q
   *
   * @param g the Buffer Buffer
   */
  private void render(Graphics2D g)
  {

    try
    {
      Font arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File(
        "res/font/PressStart2P.ttf")).deriveFont(15f);
      g.setFont(arcadeFont);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (FontFormatException e)
    {
      e.printStackTrace();
    }

    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    stateMachine.render(g);
    window.getGraphics().drawImage(backBuffer, 0, 0, WIDTH, HEIGHT, window);
  }

  /**
   * Entry Point for the Game
   *
   * @param args Console Parameters
   */
  public static void main(String[] args)
  {
    Asteroids a = Asteroids.getInstance();
    a.run();
  }

  /**
   * Singleton Method
   *
   * @return the instance
   */
  public static Asteroids getInstance()
  {

    if (instance != null)
    {
      return instance;
    }
    else
    {
      instance = new Asteroids();
      return instance;
    }
  }

  public Dimension getDimensions()
  {
    return new Dimension(WIDTH, HEIGHT);
  }

}
