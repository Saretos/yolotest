/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object.particle;

import asteroids.camera.Camera;
import asteroids.fundamentals.Image;
import asteroids.object.GameObject;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nilsg
 */
public class ParticleEmitter extends GameObject
{

    private final float sprayAngle;
    private final int particleAmount;
    private final float random;
    private final Image particleSprite;
    ArrayList<Particle> particles;
    private final int maxParticles;
    private int sizeOfParticles;
    private final int rate;
    private final float speedOfParticles;
    private int timer;
    private final int particleLife;

    public ParticleEmitter(double x, double y, float rotation, int width,
            int height, int maxParticles, int rate, float speedOfParticles,
            float sprayAngle, int particleAmount, int particleLife, float random,
            Image particleSprite)
    {
        super(x, y, rotation, width, height);
        particles = new ArrayList<>(maxParticles);
        this.maxParticles = maxParticles;
        this.rate = rate;
        this.speedOfParticles = speedOfParticles;
        this.sprayAngle = sprayAngle;
        this.particleAmount = particleAmount;
        this.particleLife = particleLife;
        this.random = random;
        this.particleSprite = particleSprite;
    }

    @Override
    public void update(double delta)
    {
        timer++;
        if (timer >= rate)
        {
            emitt();
            timer = 0;
        }
    }

    @Override
    public void render(Graphics2D g, Camera c)
    {
    }

    private void emitt()
    {
        for (int i = 0; i < particleAmount; i++)
        {
            if (particles.size() >= this.maxParticles)
            {
                Particle p = new Particle(
                        (-width / 2 + Math.random() * width + this.x),
                        (-height / 2 + Math.random() * height + this.y), 0,
                        sizeOfParticles, sizeOfParticles, null,
                        (int) (particleLife + Math.random() * random),
                        particleSprite);
            }
        }
    }

}
