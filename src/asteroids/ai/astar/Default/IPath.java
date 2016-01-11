package asteroids.ai.astar.Default;

import java.util.ArrayList;

import asteroids.ai.astar.slow.Node;

public interface IPath {
	
	public int getLength();

	public INode getWayPoint(int index);

	public int getX(int index);

	public int getY(int index);

	public void appendWayPoint(INode n);

	public void prependWayPoint(INode n);

	public boolean contains(int x, int y);
}
