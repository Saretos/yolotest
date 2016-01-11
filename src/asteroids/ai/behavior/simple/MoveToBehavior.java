package asteroids.ai.behavior.simple;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.sun.javafx.geom.Vec2d;

import asteroids.ai.AstroidsAI;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.behavior.Behavior;
import asteroids.object.Bullet;
import asteroids.object.GameObject;
import asteroids.object.PlayerShip;
import asteroids.object.Weapon;

public class MoveToBehavior implements Behavior{
	GameObject g;
	Node n;
	Vec2d move;
	public MoveToBehavior(GameObject g) 
	{
		this.g = g;
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(double delta) {
		if(move!= null)
			if(!isatNode(n))
			{
				g.x=g.x+Math.signum(move.x)*10*0.1*delta;
				g.y=g.y+Math.signum(move.y)*10*0.1*delta;
				lookat(new Node((int)AstroidsAI.getAi().pl.x,(int)AstroidsAI.getAi().pl.y));
//				System.out.println(delta);
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
   
			}
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	public void moveto(Node n) {
//		System.out.println(n.getX());
//		System.out.println(n.getY());
		lookat(n);
		move = new Vec2d((n.getX()-g.x),(n.getY()-g.y));
		this.n =n;

		
	}

	public boolean isatNode(Node n) 
	{
		if(Math.signum(move.x)>=0&&Math.signum(move.y)>=0)
			if(g.x>=n.getX()&&g.y>=n.getY())
				return true;
		
		if(Math.signum(move.x)<=0&&Math.signum(move.y)>=0)
			if(g.x<=n.getX()&&g.y>=n.getY())
				return true;
		
		if(Math.signum(move.x)>=0&&Math.signum(move.y)<=0)
			if(g.x>=n.getX()&&g.y<=n.getY())
				return true;
		
		if(Math.signum(move.x)<=0&&Math.signum(move.y)<=0)
			if(g.x<=n.getX()&&g.y<=n.getY())
				return true;

//		if(Vec2d.distance(n.getX(), n.getY(), this.x, this.y)<10)
			
		return false;
	}

	public void lookat(Node n)
	{
//		System.out.println("looak at "+n.getX());
//		System.out.println("looak at "+n.getY());
		g.rotation=(float)Math.toDegrees( Math.atan2(g.y - n.getY(), g.x - n.getX())- Math.PI / 2) ;
//		System.out.println(rotation);
	}
}
