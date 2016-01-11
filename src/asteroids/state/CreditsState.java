/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.Asteroids;
import asteroids.fundamentals.Input;
import asteroids.sound.MidiPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Raildex
 */
public class CreditsState implements State
{

  private final String CREDITS_THEME;
  private final CreditsSection[] credits;
  private int w;
  private int h;
  private Input input;
  private final StateMachine stateMachine;
  final Font titleFont;
  final Font creditFont;
  int currentSection = -1;
  int creditTimer = 0;
  

  public CreditsState(StateMachine stateMachine)
  {
    CREDITS_THEME = "Credits_Theme";

    titleFont = new Font("Arial", Font.BOLD, 64);
    creditFont = new Font("Times New Roman", Font.ITALIC, 32);
    w = Asteroids.getInstance().WIDTH;
    h = Asteroids.getInstance().HEIGHT;
    credits = new CreditsSection[]
    {
      new CreditsSection(Asteroids.getInstance().title, new String[]
      {
      }),
      new CreditsSection("Idee", new String[]
      {
        "Super-Felix", "Nils", "Silas", "Tobias", "Torben", "Yannic"
      }),
      new CreditsSection("Musik", new String[]
      {
        "Nils", "Spiele aus den Neunzigern", "F-Zero?"
      }),
      new CreditsSection("Titelbildschirm", new String[]
      {
        "Tobias"
      }),
      new CreditsSection("Schiff-Mechaniken", new String[]
      {
        "Nils"
      }),
      new CreditsSection("Schuss-Mechaniken", new String[]
      {
        "Yannic"
      }),
      new CreditsSection("AI", new String[]
      {
        "Irgendwer, der's kann"
      }),
      new CreditsSection("Hintergründe", new String[]
      {
        "Nils"
      }),
      new CreditsSection("Story Design", new String[]
      {
        "Super-Felix"
      }),
      new CreditsSection("Erfrischung", new String[]
      {
        "Eistee", "Kakao", "Kaffee"
      }),
      new CreditsSection("Noch eine Sektion",
      new String[]
      {
        "Um eine Array Index Out of Bounds Exception zu vermeiden"
      }),
      new CreditsSection("Unser Bester Freund",
      new String[]
      {
        "NullPointerException"
      }),
      new CreditsSection("Besonderer Dank",
      new String[]
      {
        "Git Gud, weil nacheinander Arbeiten keine Option war"
      }),
    };
    this.stateMachine = stateMachine;
  }

  @Override
  public void render(Graphics2D g)
  {
    if (currentSection > -1)
    {
      g.setFont(titleFont);

      g.setColor(Color.WHITE);
      FontMetrics fm = g.getFontMetrics();
      g.drawString(credits[currentSection].title, (w / 2) - (fm.stringWidth(
              credits[currentSection].title) / 2),
              (h / 2) - (fm.
              getHeight() / 2));
      g.setFont(creditFont);
      fm = g.getFontMetrics();
      for (int i = 1; i <= credits[currentSection].credit.length; i++)
      {
        g.drawString(credits[currentSection].credit[i - 1], (w / 2) - (fm.
                stringWidth(
                        credits[currentSection].credit[i - 1]) / 2),
                (h / 2) - (fm.
                getHeight() / 2) + fm.getHeight() * i);
      }
    }
  }

  @Override
  public void update(double delta)
  {
    switch (currentSection)
    {
      case -1:
        if (MidiPlayer.getInstance().getTickPosition() > 7385000 / 2)
        {
          currentSection = 0;
        } break;
      case 0:
        if (MidiPlayer.getInstance().getTickPosition() > 7385000)
        {
          currentSection = 1;
        } break;
      default:
        creditTimer++;
        if (creditTimer >= 10000)
        {
          currentSection++;
          creditTimer = 0;
        } break;
    }
    if(input.abortDown)
    {
      stateMachine.setState(new TitleState(stateMachine));
    }
  }

  @Override
  public boolean initState(Input i)
  {
    this.input = i;
    MidiPlayer.getInstance().loadMidiFile(CREDITS_THEME);
    MidiPlayer.getInstance().play();
    return true;
  }

  @Override
  public boolean exitState()
  {
    MidiPlayer.getInstance().stop();
    return false;
  }

  private static class CreditsSection
  {

    public final String title;
    public final String[] credit;

    public CreditsSection(String title, String[] credit)
    {
      this.title = title;
      this.credit = credit;
    }
  }

}