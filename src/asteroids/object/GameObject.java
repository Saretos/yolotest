package asteroids.object;

import asteroids.Asteroids;
import asteroids.camera.Camera;
import asteroids.state.ArcadeState;
import asteroids.state.State;
import java.awt.Graphics2D;

/**
 *
 * @author Raildex
 */
public abstract class GameObject
{

    public double x;
    public double y;
    public float rotation;
    public int width;
    public int height;
    public char faction;
    public boolean destroyMe = false;

    public GameObject(double x, double y,float rotation,int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.faction = 'N';
    }
    
    abstract public void update(double delta);

    abstract public void render(Graphics2D g, Camera c);

}
