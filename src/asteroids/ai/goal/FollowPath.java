package asteroids.ai.goal;

import asteroids.ai.AstroidsAI;
import asteroids.ai.Enemy.Moveable;
import asteroids.ai.astar.slow.AStar;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.astar.slow.Path;
import asteroids.ai.behavior.Behaviorable;

public class FollowPath extends GoalComposite{

	Path p;
	public FollowPath(Behaviorable npc,Path p) 
	{
		super(npc);
		this.p=p;
	}

	@Override
	public void activate() {
		status=GoalStatus.Active;
//		AstroidsAI.getAi().pm.searchnewPath(npc, 32, 33, 50, 2);
		
//		AStar pathFinder = new AStar(AstroidsAI.getAi().astriodsmap, AstroidsAI.getAi().heuristic);
//		p = pathFinder.calcShortestPath(32, 33, 50, 2);
//		System.out.println(p);
//		
//		//pathFinder.printPath();
		System.out.println("bbbbbbbbbbbbbbbbb");
		if(p!=null)
		for (int i = p.getLength()-1; i>=0; i--) 
		{
//			System.out.println(p.getX(i)*20+" "+p.getY(i)*20);
			addSubgoal(new Moveto(npc,new Node(p.getX(i)*20,p.getY(i)*20) ));
			
		}
		else
			status=GoalStatus.Failed;
		
	}

	@Override
	public GoalStatus process() 
	{
		if(status==GoalStatus.inActive)
			activate();
		return processSubgoal();
	}

	@Override
	public void termiate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handleMessage(String msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
