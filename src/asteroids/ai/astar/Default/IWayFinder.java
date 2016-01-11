package asteroids.ai.astar.Default;


public interface IWayFinder {
	public IPath calcShortestPath(int startX, int startY, int goalX, int goalY);
}
