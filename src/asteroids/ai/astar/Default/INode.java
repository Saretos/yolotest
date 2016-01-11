package asteroids.ai.astar.Default;

import java.util.ArrayList;

public interface INode 
{
		
		public ArrayList<? extends INode> getNeighborList();

		public float getDistanceFromStart();
		
		public int getX();

		public void setX(int x);

		public int getY();

		public void setY(int y);
		
		public boolean isObsticale();

		public void setObstical(boolean isObstical);

		public boolean equals(INode node);
		
}
