package asteroids.ai.goal;

import asteroids.ai.Enemy.Moveable;
import asteroids.ai.behavior.Behaviorable;

abstract class Goal 
{
	protected Behaviorable npc;
	protected GoalStatus status;
	
	public Goal(Behaviorable npc) 
	{
		this.npc = npc;
		this.status = GoalStatus.inActive;
	}
	public abstract void activate();
	public abstract GoalStatus process();
	public abstract void termiate();
	public abstract boolean handleMessage(String msg);
	boolean isActive() {
		if(status == GoalStatus.Active)
			return true;
		return false;
	}
	boolean isInactive() {
		if(status == GoalStatus.inActive)
			return true;
		return false;
	}
	boolean isComplete() {
		if(status == GoalStatus.Complete)
			return true;
		return false;
	}
	boolean hasFailed() {
		if(status == GoalStatus.Failed)
			return true;
		return false;
	}
}
