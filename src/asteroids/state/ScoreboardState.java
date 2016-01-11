/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.state;

import asteroids.fundamentals.Input;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import asteroids.object.gui.Highscore;
/**
 *
 * @author Tobias
 */
public class ScoreboardState implements State
{
    private Input input;
    private StateMachine stateMachine;

    private int gameId = 0;
    private int calc = 30;
    private final int width = 1280;
    private int lastGameId;

    private final String scoreFile = "highscores.txt";

    private ArrayList<Highscore> highscores = new ArrayList<>();

    public ScoreboardState(StateMachine stateMachine)
    {
        this.stateMachine = stateMachine;
    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.blue);
        g.drawString("Name", width/2-100,25);
        g.drawString("Score", width/2+50 ,25);
        g.setColor(Color.lightGray);
        g.drawLine(width/2-100, 30, width/2+150, 30);
        Collections.sort(highscores);
        for(int i = 1; i  <= highscores.size(); i++)
        {
            colorSwitch(g,i);
            if(i <= 10)
            {
                if(highscores.get(i-1).getIntID() == lastGameId)
                {
                    g.setColor(Color.MAGENTA);
                    g.drawString("<< Letztes Spiel", width/2+150 , i*calc+50);
                    colorSwitch(g,i);
                }
                g.drawString(highscores.get(i-1).name, width/2-100 , i*calc+50);
                g.drawString(highscores.get(i-1).getScore(), width/2+50 , i*calc+50);
                g.setColor(Color.lightGray);
                g.drawLine(width/2-100, i*calc+55, width/2+150, i*calc+55);
            }
        }
    }

    public void colorSwitch(Graphics2D g, int i)
    {
        switch(i)
        {
            case 1:
                g.setColor(Color.green);
                break;
            case 2:
                g.setColor(Color.yellow);
                break;
            case 3:
                g.setColor(Color.red);
                break;
            default:
                g.setColor(Color.white);
                break;
        }
    }

    @Override
    public void update(double delta)
    {
        if(input.abortDown)
        {
            stateMachine.setState(new TitleState(stateMachine));
        }
    }

    @Override
    public boolean initState(Input i)
    {
        String name;
        int score;
        this.input = i;
        try
        {
            Scanner scanner = new Scanner(new File(scoreFile));
            while ((scanner.hasNextLine()))
            {
                if(scanner.hasNext())
                {
                    name = scanner.next();
                    score = scanner.nextInt();
                    lastGameId = scanner.nextInt();
                    Highscore temp = new Highscore(name, score, lastGameId);
                    highscores.add(temp);
                }
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean exitState()
    {
        return true;
    }

}
