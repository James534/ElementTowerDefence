import java.util.ArrayList;
/**
 * The class to find a path from the start point to the end point
 * 
 * @author James Lu
 * @version 1.0
 */
public class Pathfind  
{
    protected Node[][] map;
    protected Map world;
    protected int startX, startY;
    protected int endX, endY;
    protected int sizeX, sizeY;

    private ArrayList<Node> open;
    private ArrayList<Node> close;
    private ArrayList<Node> path;
    private ArrayList<Integer> intPath;

    public Pathfind(int startX, int startY, int endX, int endY, Map world){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.world = world;
        sizeX = world.getSizeX();
        sizeY = world.getSizeY();

        reset();
    }

    private void reset(){
        map = new Node [sizeY][sizeX];

        open = new ArrayList<Node>();
        close = new ArrayList<Node>();
        path = new ArrayList<Node>();
        intPath = new ArrayList<Integer>();

        map[startY][startX] = new Node (startX,startY,this, true);
        map[endY][endX] = new Node (endX, endY, this, true);

        open.add (map[startY][startX]);
        map[endY][endX].setEnd();
    }

    /**
     * finds the path from start to end <br>
     * the main A* method <br>
     * if there is no path, path will be null
     */
    public void findPath(){
        while (open.size() > 0){
            Node best = findBestNode();
            open.remove (best);
            close.add (best);

            if (best.isEnd()){          //if the best square is the end square
                //insert code here for what to do after path is fouind
                //System.out.println ("Path Found");

                setPath (best);
                return;
            }
            else{
                ArrayList<Node> adj = best.getAdj();

                for (Node neighbour: adj){
                    if (open.contains (neighbour)){
                        Node temp = new Node (neighbour.getX(), neighbour.getY(), this, true);
                        temp.setParent (best);
                        if (temp.getFinalCost() >= neighbour.getFinalCost()){
                            continue;
                        }
                    }

                    if (close.contains (neighbour)){
                        Node temp = new Node (neighbour.getX(), neighbour.getY(), this, true);
                        temp.setParent (best);
                        if (temp.getFinalCost() >= neighbour.getFinalCost()){
                            continue;
                        }
                    }

                    neighbour.setParent (best);
                    open.remove (neighbour);
                    close.remove (neighbour);
                    open.add (0, neighbour);    //changes behaviour for pathfinding
                    //open.add (neighbour);         //this one makes it go down first
                }
            }
        }
        //insert code for finding no path
        // System.out.println ("No path" + startX + " " + endX);
        path = null;
        intPath = null;
    }

    /**
     * checks if the x and y coodinates are legit, then makes a new node there
     */
    private Node makeNode (int x, int y, boolean passible){        
        if (x >= 0 && x < sizeX){
            if (y >= 0 && y < sizeY){           
                if (map [y][x] == null)
                    map[y][x] = new Node (x, y, this, passible);
                return map[y][x];
            }
        }
        return null;
    }

    /**
     * finds the best node, the closest node to the finish
     */
    private Node findBestNode(){
        Node best = open.get (0);

        for (int i = 0; i < open.size(); i++){
            Node temp = open.get (i);
            if (best.getFinalCost() > temp.getFinalCost()){
                best = temp;
            }
        }
        return best;
    }

    /**
     * after finding the path, recrusively sets the path
     */
    private void setPath(Node node){
        path.add (node);
        intPath.add (node.getX());
        intPath.add (node.getY());
        if (node.getParent() != null){
            setPath (node.getParent());
        }
    }

    /**
     * call upon this to generate a path <br>
     * it returns the best possible path, with the A* pathfinding algorithm
     */
    public ArrayList<Integer> generatePath(){
        reset();

        findPath();
        //printScore();
        //printFinalPath();
        return intPath;
    }

    /**
     * prints out the path it takes
     * debugging purposes
     */
    private void printFinalPath(){
        System.out.println();
        int [][] tempMap = new int [25][50];
        for (int i = 0; i < path.size(); i++){
            Node temp = path.get (i);            
            tempMap[temp.getY()][temp.getX()] = temp.getStartCost();
        }

        tempMap[endY][endX] = -1;

        for (int Y = 0; Y < 25; Y++){
            for (int X = 0; X < 50; X++){
                if (tempMap[Y][X] != 0){
                    System.out.print (tempMap[Y][X] + "  ");
                }
                else{
                    System.out.print (0 + "  ");
                }
            }
            System.out.println();
        }
    }

    /**
     * prints out the paths that it searched through
     * debugging purposes
     */
    private void printScore(){
        System.out.println();
        int[][] temp = new int [25][50];
        for (int Y = 0; Y < 25; Y++){
            for (int X = 0; X < 50; X++){
                Node tempNode = map[Y][X];
                if (tempNode != null){
                    temp[Y][X] = tempNode.getFinalCost();}
                else {
                    temp[Y][X] = 0;}
                if (Y == endY && X == endX){
                    System.out.print ("F ");
                }
                else{
                    System.out.print (temp[Y][X] + " ");
                }
            }
            System.out.println();
        }
    }

    private class Node{
        private int x, y;
        private boolean walkable;
        private boolean end;
        private Pathfind map;
        private int startCost;
        private int endCost;        

        private Node parent;

        public Node (int x, int y, Pathfind map, boolean walkable){
            this.x = x;
            this.y = y;
            this.map = map;
            this.walkable = walkable;

            if (x == map.endX && y == map.endY){
                return;}
        }

        /**
         * start cost; g(n)
         */
        private int setStartCost(){
            if (parent == null){        //if there is no parent, this is the head node, cost = 0
                return 0;}
            else{
                startCost = (parent.getStartCost() +1);
            }
            return startCost;
        }

        /**
         * end cost; h(n)
         * manhattan distance
         */
        private void setEndCost(){
            if (x == map.startX && y == map.startY){
                endCost = 0;}
            endCost = ( (Math.abs (x-map.endX)) + (Math.abs (y-map.endY)) );
        }

        /**
         * final cost; f(n)
         */
        public int getFinalCost(){
            if (parent == null){
                return endCost;}

            startCost = setStartCost();
            setEndCost();
            return startCost + endCost;
        }

        /**
         * return the starting cost
         * the cost that it takes from the starting point to this point
         */
        public int getStartCost(){
            return startCost;
        }

        /**
         * returns the ending cost
         * the estimated cost to the ending point from this point
         */
        public int getEndCost(){
            return endCost;
        }

        /**
         * finding the neighbouring blocks that are walkthrough
         */
        public ArrayList<Node> getAdj(){
            ArrayList<Node> adj = new ArrayList<Node>();
            boolean upTrue = false;
            boolean rightTrue = false;
            boolean downTrue = false;
            boolean leftTrue = false;
            Tile temp = world.getTile (x, y+1);
            if (temp != null){
                upTrue = temp.getWalkable();
            }
            temp = world.getTile (x+1, y);
            if (temp != null){
                rightTrue = temp.getWalkable();
            }
            temp = world.getTile (x, y-1);
            if (temp != null){
                downTrue = temp.getWalkable();
            }
            temp = world.getTile (x-1, y);
            if (temp != null){
                leftTrue = temp.getWalkable();
            }

            Node up = map.makeNode (x, y+1, upTrue);
            Node right = map.makeNode (x+1, y, rightTrue);
            Node down = map.makeNode (x, y-1, downTrue);
            Node left = map.makeNode (x-1, y, leftTrue);

            if (up != null){
                if (upTrue){
                    adj.add (up);
                }
            }
            if (right != null){
                if (rightTrue){
                    adj.add (right);
                }
            }
            if (down != null){
                if (downTrue){
                    adj.add (down);
                }
            }
            if (left != null){
                if (leftTrue){
                    adj.add (left);
                }
            }
            return adj;
        }

        //-----------------------------methods to set/return variables-----------------------------

        /**
         * setting the final point of the map
         */
        public void setEnd (){
            end = true;
        }

        public boolean isEnd(){
            return end;
        }

        public boolean getWalkable(){
            return walkable;
        }

        public void setParent (Node t){
            parent = t;
        }

        public Node getParent ()
        {
            return parent;        
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }
}