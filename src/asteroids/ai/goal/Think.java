package asteroids.ai.goal;

import asteroids.ai.Enemy.Moveable;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.behavior.Behaviorable;

public class Think extends GoalComposite
{

	public Think(Behaviorable npc) {
		super(npc);
	}

	@Override
	public void activate() {
		System.out.println("oha");
		subgoals.add(new FindPath(npc));
		status=GoalStatus.Active;
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
