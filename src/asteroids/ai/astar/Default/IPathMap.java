package asteroids.ai.astar.Default;

import asteroids.ai.astar.slow.Node;

public interface IPathMap {
	public float getDistanceBetween(INode node1, INode node2);
	public int getMapWith();
	public int getMapHeight();
	
}
