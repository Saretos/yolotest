package asteroids.ai.Enemy;

import asteroids.ai.astar.slow.Node;
import asteroids.ai.behavior.simple.MoveToBehavior;

public interface Moveable {
	public MoveToBehavior getMovetoBehavior();
}
