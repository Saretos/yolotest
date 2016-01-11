package asteroids.ai.goal;

import java.util.ArrayList;
import java.util.Stack;

import asteroids.ai.Enemy.Moveable;
import asteroids.ai.behavior.Behaviorable;

abstract class GoalComposite extends Goal
{
	Stack<Goal> subgoals ;
	@Override
	public void activate() 
	{
		status=GoalStatus.Active;
	}
	@Override
	public GoalStatus process() {
		if(status==GoalStatus.inActive)
			activate();
		
		return processSubgoal();
	}
	public GoalComposite(Behaviorable npc)
	{
		super(npc);
		subgoals = new Stack<Goal>();
	}
	public void addSubgoal(Goal goal)
	{
		subgoals.push(goal);
	}
	public void removeallSubgoals()
	{
		for (Goal goal : subgoals) 
		{
			goal.termiate();
		}
		subgoals.clear();
	}
	public GoalStatus processSubgoal() 
	{
		while(!subgoals.empty()&&(subgoals.peek().isComplete()||subgoals.peek().hasFailed()))
		{
			subgoals.peek().termiate();
			subgoals.pop();
		}
		
		if(!subgoals.empty())
		{
			GoalStatus status = subgoals.peek().process();
			if(status==GoalStatus.Complete && subgoals.size() > 1)
			{
				return GoalStatus.Active;
			}
			return status;
		}
		else
		{
			return GoalStatus.Complete;
		}	
	}
}
