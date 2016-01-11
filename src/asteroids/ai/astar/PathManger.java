package asteroids.ai.astar;

import java.util.ArrayList;
import java.util.HashMap;

import asteroids.ai.AstroidsAI;
import asteroids.ai.Enemy.Moveable;
import asteroids.ai.astar.quick.FasterAStar;
import asteroids.ai.astar.slow.AStar;
import asteroids.ai.astar.slow.Path;
import asteroids.ai.behavior.Behaviorable;

public class PathManger 
{
	HashMap<Behaviorable, Search> paths = new HashMap<Behaviorable, Search>();
	ArrayList<Search> active = new ArrayList<Search>();
	
	public void searchnewPath(Behaviorable npc,int startX, int startY, int goalX, int goalY) 
	{
		Search s = new Search(npc);
		paths.put(npc, s);
		active.add(s);
		s.pathFinder.startcalc(startX, startY, goalX, goalY);
		System.out.println(npc);
	}
	public void update() 
	{
		
			
		if(active.size()>0)
		{
			for (int i = 0; i < 200; i++) 
			{
				active.get(0).s = active.get(0).pathFinder.updatecalc();
				
				if(active.get(0).s==PathStatus.complete)
				{
					setComplete();
					active.remove(0);
					return;
				}
			}
			
		}
	}
	public void setComplete() 
	{
		active.get(0).p = active.get(0).pathFinder.reconstructPath();
	}
	
	public PathStatus getStatus(Behaviorable npc) 
	{
		if(paths.containsKey(npc))
				return paths.get(npc).s;
		return PathStatus.failed;
	}
	public Path getPath(Behaviorable npc) 
	{
		if(paths.containsKey(npc))
			return paths.get(npc).p;
		return null;
	}
	private class Search
	{
		Behaviorable npc;
		PathStatus s;
		Path p;
		FasterAStar pathFinder;
		public Search(Behaviorable npc) 
		{
			this.npc = npc;
			s = PathStatus.active;
			pathFinder = new FasterAStar(AstroidsAI.getAi().astriodsmap, AstroidsAI.getAi().heuristic);
		}
		
	}
}
