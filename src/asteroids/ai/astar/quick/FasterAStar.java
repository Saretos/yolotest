package asteroids.ai.astar.quick;

import java.util.ArrayList;
import java.util.Collections;

import asteroids.ai.astar.PathStatus;
import asteroids.ai.astar.Default.IWayFinder;
import asteroids.ai.astar.heurastic.AStarHeuristic;
import asteroids.ai.astar.slow.AreaMap;
import asteroids.ai.astar.slow.Node;
import asteroids.ai.astar.slow.Path;



public class FasterAStar{
		public Node endnode;
        private AreaMap map;
        private AStarHeuristic heuristic;
//        private int startX;
//        private int startY;
//        private int goalX;
//        private int goalY;
        /**
         * closedList The list of Nodes not searched yet, sorted by their distance to the goal as guessed by our heuristic.
         */
        private ArrayList<Node> closedList;
        private SortedNodeList openList;
        private Path shortestPath;

        public FasterAStar(AreaMap map, AStarHeuristic heuristic) {
                this.map = map;
                this.heuristic = heuristic;

                closedList = new ArrayList<Node>();
                openList = new SortedNodeList();
                map.clear();
                
        }
        public PathStatus startcalc(int startX, int startY, int goalX, int goalY) 
        {
            map.setStartLocation(startX, startY);
            map.setGoalLocation(goalX, goalY);
            
            if (map.getNode(goalX, goalY).isObsticale()) {
                return PathStatus.failed;
            }

            map.getStartNode().setDistanceFromStart(0);
            map.getStartNode().setPreviousNode(null);
            closedList.clear();
            openList.clear();
            openList.add(map.getStartNode());
            return PathStatus.active;
        }
        public PathStatus updatecalc()
        {
        	if(openList.size() != 0) {
        	
                    //get the first Node from non-searched Node list, sorted by lowest distance from our goal as guessed by our heuristic
                    Node current = openList.getFirst();
                    if(current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY()) {
                    		endnode=current;
                    		return PathStatus.complete;
//                            return reconstructPath(current);
                    }

                    //move current Node to the closed (already searched) list
                    openList.remove(current);
                    closedList.add(current);
                    //go through all the current Nodes neighbors and calculate if one should be our next step
                    for(Node neighbor : current.getNeighborList()) {
                            boolean neighborIsBetter;

                            //if we have already searched this Node, don't bother and continue to the next one 
                            if (closedList.contains(neighbor))
                                    continue;

                            //also just continue if the neighbor is an obstacle
                            if (!neighbor.isObsticale()) {

                                    // calculate how long the path is if we choose this neighbor as the next step in the path 
                                    float neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor));

                                    //add neighbor to the open list if it is not there
                                    if(!openList.contains(neighbor)) {
                                            openList.add(neighbor);
                                            neighborIsBetter = true;
                                            //if neighbor is closer to start it could also be better
                                    } else if(neighborDistanceFromStart < current.getDistanceFromStart()) {
                                            neighborIsBetter = true;
                                    } else {
                                            neighborIsBetter = false;
                                    }
                                    // set neighbors parameters if it is better
                                    if (neighborIsBetter) {
                                            neighbor.setPreviousNode(current);
                                            neighbor.setDistanceFromStart(neighborDistanceFromStart);
                                            neighbor.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));
                                    }
                            }
                            
                    }
                    return PathStatus.active;
            }
        	return PathStatus.failed;
        }
        
        public void printPath() {
                Node node;
                for(int x=0; x<map.getMapWith(); x++) {

                        if (x==0) {
                                for (int i=0; i<=map.getMapWith(); i++)
                                        System.out.print("-");
                                System.out.println();   
                        }
                        System.out.print("|");

                        for(int y=0; y<map.getMapHeight(); y++) {
                                node = map.getNode(x, y);
                                if (node.isObsticale()) {
                                        System.out.print("X");
                                } else if (node.isStart) {
                                        System.out.print("s");
                                } else if (node.isGoal) {
                                        System.out.print("g");
                                } else if (shortestPath.contains(node.getX(), node.getY())) {
                                        System.out.print("¤");
                                } else {
                                        System.out.print(" ");
                                }
                                if (y==map.getMapHeight())
                                        System.out.print("_");
                        }

                        System.out.print("|");
                        System.out.println();
                }
                for (int i=0; i<=map.getMapWith(); i++)
                        System.out.print("-");
        }

        public Path reconstructPath() {
                Path path = new Path();
                while(!(endnode.getPreviousNode() == null)) {
                        path.prependWayPoint(endnode);
                        endnode = endnode.getPreviousNode();
                }
                this.shortestPath = path;
                return path;
        }

        private class SortedNodeList {

                private ArrayList<Node> list = new ArrayList<Node>();

                public Node getFirst() {
                        return list.get(0);
                }

                public void clear() {
                        list.clear();
                }

                public void add(Node node) {
                        list.add(node);
                        Collections.sort(list);
                }

                public void remove(Node n) {
                        list.remove(n);
                }

                public int size() {
                        return list.size();
                }

                public boolean contains(Node n) {
                        return list.contains(n);
                }
        }

}
