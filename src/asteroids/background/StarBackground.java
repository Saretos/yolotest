/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.background;

import asteroids.camera.Camera;
import asteroids.fundamentals.Updateable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nilsg
 */
public class StarBackground extends Background implements Updateable
{

    private final int maxStars;
    private final Color starColor;
    final int starSize;
    final float starSpeed;
    ArrayList<Star> stars;

    public StarBackground(int maxStars, Color starColor, int starSize,
            float starSpeed)
    {
        this.maxStars = maxStars;
        this.starColor = starColor;
        stars = new ArrayList<>();
        this.starSize = starSize;
        this.starSpeed = starSpeed;
    }

    @Override
    public void render(Graphics2D g, Camera c)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        for (int i = 0; i < stars.size(); i++)
        {
            Star s = stars.get(i);
            int alpha;
            if (s.time <= 0.5f)
            {
                alpha =(int) ((s.time * 2f)*255);
            }else
            {
                alpha =(int) ((1f-(s.time-0.5f)*2)*255);
            }

            Color starColor = new Color(this.starColor.getRed(),
                    this.starColor.getGreen(),
                    this.starColor.getBlue(), (int) (alpha));
            g.setColor(starColor);
            g.fillOval((int) s.x - s.size / 2,
                    (int) s.y - s.size / 2,
                    s.size, s.size);
        }
    }

    @Override
    public void update(double delta)
    {
        int x = (int) (Math.random() * 1280);
        int y = (int) (Math.random() * 720);
        if (stars.size() < maxStars)
        {
            float dx = (float) (-starSpeed / 2 + Math.random() * starSpeed);
            float dy = (float) (-starSpeed / 2 + Math.random() * starSpeed);
            stars.add(new Star(x, y, (float) Math.random(), dx, dy,(int)(this.starSize*Math.random())));
        }
        for (int i = 0; i < stars.size(); i++)
        {
            stars.get(i).update(delta);
            if (stars.get(i).time >= 1)
            {
                stars.remove(i);
            }
        }
    }

    private static class Star implements Updateable
    {

        float x;
        float y;
        final float speed;
        final float dx;
        final float dy;
        final int size;
        public Star(int x, int y, float speed, float dx, float dy,int size)
        {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dx = dx;
            this.dy = dy;
            this.size=size;
        }

        float time;

        @Override
        public void update(double delta)
        {
            this.x += dx;
            this.y += dy;
            time += 0.01 * speed * delta;
        }
    }
}
