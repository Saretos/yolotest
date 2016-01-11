/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.particle;

import asteroids.camera.Camera;
import asteroids.fundamentals.Image;
import asteroids.fundamentals.blend.BlendingComposite;
import asteroids.object.GameObject;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Raildex
 */
public class Particle extends GameObject
{
    
    public boolean dead;

    Image particleImage;

    int lifeTime;

    final int maxTime;
    
    float size;

    Map<Integer, ParticleEnvelope> envelopes;

    public Particle(double x, double y, float rotation, int width, int height,
            Map<Integer, ParticleEnvelope> envelopes, int maxTime,
            Image img)
    {
        super(x, y, rotation, width, height);

        if (envelopes == null)
        {
            envelopes = new HashMap<>();
        } else
        {
            this.envelopes = envelopes;
        }
        this.particleImage = img;
        this.maxTime = maxTime;
    }

    @Override
    public void update(double delta)
    {
        lifeTime++;
        if (lifeTime >= maxTime)
        {
            dead = true;
        }
        particleImage.update(delta);
    }

    @Override
    public void render(Graphics2D g, Camera c)
    {
        g.setComposite(BlendingComposite.add);
        AffineTransform save = g.getTransform();
        g.rotate(Math.toRadians(this.rotation), x, y);
        g.drawImage(particleImage.getImage(), (int) x - width, (int) y - height,
                width, height, null);
        g.setTransform(save);
        g.setComposite(AlphaComposite.SrcOver);
    }

    public class ParticleEnvelope
    {
        public static final int KEY_ROTATION = 1;
        public static final int KEY_VALUE = 2;
        public static final int KEY_HUE = 3;
        public static final int KEY_SATURATION = 4;
        final float[] keys;
        final float[] values;

        public ParticleEnvelope(float[] keys, float[] values)
        {
            this.keys = keys;
            this.values = values;
        }

        /**
         * Interpolation Method for smooth Envelopes
         *
         * @param time the lifeTime of this particle
         * @param keys
         * @param values
         * @return the value
         */
        public float interpolateValue(float time, float[] keys, float[] values)
        {

            int l = 0;
            for (int i = 0; i < keys.length; i++)
            {
                if (time > keys[i])
                {
                    l = i;
                }
            }
            float interval_len = keys[l + 1] - keys[l]; //r = l + 1
            float k2_a = (time - keys[l]) / interval_len; //k1_a = 1 - k2_a
            return k2_a * values[l + 1] + (1 - k2_a) * values[l];
        }
    }
    protected BufferedImage tint(float r, float g, float b, float a,
                               BufferedImage sprite)
  {
    BufferedImage tintedSprite = new BufferedImage(sprite.getWidth(), sprite.
            getHeight(), BufferedImage.TRANSLUCENT);
    Graphics2D graphics = tintedSprite.createGraphics();
    graphics.drawImage(sprite, 0, 0, null);
    graphics.dispose();

    for (int i = 0; i < tintedSprite.getWidth(); i++)
    {
      for (int j = 0; j < tintedSprite.getHeight(); j++)
      {
        int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
                getDataElements(i, j, null));
        int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
                getDataElements(i, j, null));
        int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
                getDataElements(i, j, null));
        int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
                getDataElements(i, j, null));
        rx *= r;
        gx *= g;
        bx *= b;
        ax *= a;
        tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
      }
    }
    return tintedSprite;
  }
}
