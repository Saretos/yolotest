package asteroids.ai;

import java.util.ArrayList;

import asteroids.ai.astar.PathManger;
import asteroids.ai.astar.heurastic.AStarHeuristic;
import asteroids.ai.astar.heurastic.ClosestHeuristic;
import asteroids.ai.astar.slow.AStar;
import asteroids.ai.astar.slow.AreaMap;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.astar.slow.Path;
import asteroids.fundamentals.CollisionCircle;
import asteroids.object.Collidable;
import asteroids.object.GameObject;
import asteroids.object.PlayerShip;
import asteroids.object.bullet.Bullet;

public class AstroidsAI 
{
	ArrayList<GameObject> objects;
	private static AstroidsAI ai;
	public AreaMap astriodsmap;
	public AStarHeuristic heuristic;
	public PathManger pm;
	public PlayerShip pl;
	public AstroidsAI(ArrayList<GameObject> objects,PlayerShip pl)
	{
		this.objects = objects;
		ai = this;
		astriodsmap = new AreaMap(120,78);
		heuristic = new ClosestHeuristic();
		pm = new PathManger();
		this.pl = pl;
//		long time = System.currentTimeMillis();
//        AStar pathFinder = new AStar(astriodsmap, heuristic);
//        System.out.println(System.currentTimeMillis()-time);
//        Path p = pathFinder.calcShortestPath(0, 0, 59, 38);
//
//        if(p!=null)
//        {	
//        pathFinder.printPath();
//        }

	}
	public boolean chekcollision(Node n)
	{
				CollisionCircle cc = new CollisionCircle(n.getX()*20, n.getY()*20, 40);
	                
	                for(int j = 0;j<objects.size();j++){
	                	
	                    GameObject temp2 = objects.get(j);
	                	if(temp2.faction!='E')
	                    if(temp2 instanceof Collidable){
	                        Collidable t = (Collidable)temp2;
	                            if(cc.checkCollision(t.getCollisionCircle())){
	                            	if(t instanceof PlayerShip)
//	                            		System.out.println("muffin"+t);
	                            	return true;
	                        }
	                    }
	        }
		return false;
	}
	public static AstroidsAI getAi() 
	{
		return ai;
	}
	public void update() 
	{
		pm.update();
	}
}
