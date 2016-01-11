package asteroids.ai.goal;

import asteroids.ai.AstroidsAI;
import asteroids.ai.Enemy.Moveable;
import asteroids.ai.Enemy.TestEnemyShip;
import asteroids.ai.astar.PathStatus;
import asteroids.ai.astar.slow.AStar;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.astar.slow.Path;
import asteroids.ai.behavior.Behaviorable;

public class FindPath extends GoalComposite{
	boolean path = false;
	public FindPath(Behaviorable npc) 
	{
		super(npc);
	}

	@Override
	public void activate() {
		status=GoalStatus.Active;
		System.out.println("ziel"+(int)((TestEnemyShip)npc).x/20+5);
		AstroidsAI.getAi().pm.searchnewPath(npc,(int)((TestEnemyShip)npc).x/20, (int)((TestEnemyShip)npc).y/20,(int)((TestEnemyShip)npc).x/20+5, (int)((TestEnemyShip)npc).y/20-5);
	}

	@Override
	public GoalStatus process() 
	{
		if(status==GoalStatus.inActive)
			activate();
		if(path)	
			return processSubgoal();
		
		if(AstroidsAI.getAi().pm.getStatus(npc)==PathStatus.complete)
		{
			addSubgoal(new FollowPath(npc, AstroidsAI.getAi().pm.getPath(npc)));
			path=true;
			System.out.println("fertig");
		}
		return GoalStatus.Active;
		
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
