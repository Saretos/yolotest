package asteroids.ai.Enemy;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.sun.javafx.geom.Vec2d;

import asteroids.ai.astar.slow.Node;
import asteroids.ai.behavior.Behavior;
import asteroids.ai.behavior.Behaviorable;
import asteroids.ai.behavior.simple.MoveStraigtandShoot;
import asteroids.ai.behavior.simple.MoveToBehavior;
import asteroids.ai.goal.Think;
import asteroids.fundamentals.CollisionCircle;
import asteroids.fundamentals.Image;
import asteroids.fundamentals.Input;
import asteroids.fundamentals.Spawner;
import asteroids.object.Bullet;
import asteroids.object.Collidable;
import asteroids.object.PlayerShip;
import asteroids.object.Weapon;

public abstract class TestEnemyShip extends PlayerShip implements Collidable,Moveable,Behaviorable{
	Vec2d move;
	Node n;
	private Spawner s;
	Think gc;
	Behavior b;
	MoveToBehavior mt;
	public TestEnemyShip(double x, double y, double maxAccel, double maxVel,
			float rot, int w, int h, Image img, Input i, Spawner s) {
		super(x, y, maxAccel, maxVel, rot, w, h, img, i, s);
		this.faction='E';
		this.s =s;
//		for (Weapon ww: weapons.values())
//		{
//			ww.interval = 2000;
//		}
		gc = new Think(this);
		mt = new MoveToBehavior(this);
		setBehavior(new MoveStraigtandShoot(this,s,weapons.values()));
	}
	
	@Override
	public void update(double delta) 
	{
//		if(move!= null)
//			if(!isatNode(n))
//			{
//				x=x+Math.signum(move.x)*10*0.1*delta;
//				y=y+Math.signum(move.y)*10*0.1*delta;
////				System.out.println(delta);
//		        for (Weapon w : weapons.values())
//		        {
//		            if (w.fireTimer >= w.interval)
//		            {
////		            	System.out.println(w.interval);
//		                fire(w);
//			            try {
//		                Bullet b = new Bullet(ImageIO.read(new File("res/gfx/FIRE_0.png")),true,5,5000,x,y,5,5,rotation,this.faction);
//		                s.spawn(b);
//		            } catch (IOException ex) {
//		                Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
//		            }  
//		            }
//		        }
//   
//			}
		
		 this.getCollisionCircle().setCoordinates(x, y);
		 if(b!=null)
		 b.process(delta);
//		 gc.process();
	}

	@Override
	public MoveToBehavior getMovetoBehavior() {
		return this.mt;
	}
	@Override
	public Behavior getBehavior() {
		return this.b;
	}
	@Override
	public void setBehavior(Behavior b) {
		this.b=b;
	}
	
}
