package asteroids.ai.goal;

import asteroids.ai.Enemy.Moveable;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.behavior.Behaviorable;

public class Moveto extends Goal
{
	Node n;
	public Moveto(Behaviorable npc,Node n) {
		super(npc);
		this.n=n;
		
	}

	@Override
	public void activate() {
		if(!(npc instanceof Moveable))
		{
			status= GoalStatus.Failed;
			return;
		}
		((Moveable)npc).getMovetoBehavior().moveto(n);
		npc.setBehavior(((Moveable)npc).getMovetoBehavior());
		System.out.println("aloha");
		status=GoalStatus.Active;
		System.out.println(npc +"   "+n.getX()+" "+n.getY());
	}

	@Override
	public GoalStatus process() 
	{
		if(status==GoalStatus.inActive)
			activate();
		
		if(npc instanceof Moveable)
		if(((Moveable)npc).getMovetoBehavior().isatNode(n))
		{	
			status=GoalStatus.Complete;
		}
			
		
		return status;
	}

	@Override
	public void termiate() {
		System.out.println("naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
	}

	@Override
	public boolean handleMessage(String msg) {
		// TODO Auto-generated method stub
		return false;
	}


}
