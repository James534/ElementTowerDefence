import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Classes for individual tiles
 * 
 * @author James Lu
 * @version 0.01
 */
public class Tile extends Actor
{
    private GreenfootImage background;
    private GreenfootImage[] bgCache;

    private int x, y;
    private int[] movementX, movementY;

    //-1 = has tower, 0 = normal, 1 = has movement, 2 = start, 3 = 1st/2nd checkpoint, 4 = end
    private int tileId;    

    private boolean hasMovement;
    private boolean walkable;
    private boolean placeable;
    private boolean end;
    private Map map;

    public Tile()
    {
        background  = new GreenfootImage (20, 20);
        bgCache     = new GreenfootImage [5];
        bgCache[0]  = new GreenfootImage ("Tiles/grass.png");
        bgCache[1]  = new GreenfootImage ("Tiles/road.png");        

        tileId      = 0;         //the tile id
        walkable    = true;    //if this tile is walkable
        placeable   = true;  //you can put stuff on this tile

        hasMovement = false;        //if this tile has any movement
        movementX   = new int [3];    //the movement for all 3 stages
        movementY   = new int [3];
        
        for (int i = 0; i < 3; i++){movementX [i] = 0; movementY[i] = 0;}

        refresh();              //refresh it
    }

    public Tile(int x, int y, Map map){
        this();
        this.x = x;
        this.y = y;
        this.map = map;
    }
    
    /**
     * refreshes the tile after any changes to the sprite
     */
    private void refresh(){    
        background   = bgCache [tileId];
        this.setImage (background);
    }

    /**
     * resets the tile back to the inital settings
     */
    public void reset(){       
        for (int i = 0; i < 3; i++){movementX [i] = 0; movementY[i] = 0;}
        hasMovement = false;

        if (tileId < 2){
            tileId = 0;
        }

        refresh();
    }

    /**
     * sets the color of the tile
     */
    public void setId(int i){
        if (tileId < 2){
            tileId = i;
        }
        refresh();
    }

    /**
     * only called for the special tiles
     * start, checkpoint, and end
     */
    public void setId (int i, boolean special){
        tileId = i;        
        bgCache[2] = new GreenfootImage ("Tiles/start.png");
        bgCache[3] = new GreenfootImage ("Tiles/checkPoint.png");
        bgCache[4] = new GreenfootImage ("Tiles/end.png");
        placeable  = false;
        refresh();
    }

    /**
     * sets the vector movement of this tile
     */
    public void setMovement (int x, int y, int checkpoint){
        movementX[checkpoint] = x;
        movementY[checkpoint] = y;
        if (end){
            movementX[2] = 0;
            movementY[2] = 1;
        }
        hasMovement = true;
    }

    /**
     * sets the vector movement for the end tile
     * will be called for only the end tile
     */
    public void setMovement (boolean End){
        end          = true;
        movementX[2] = 0;
        movementY[2] = 1;
        hasMovement  = true;
        tileId       = 4;
    }
    
    /**
     * sets if you can place a tower on this tile
     */
    public void setPlaceable (boolean b){
        placeable = b;
    }

    /**
     * returns true if you can place a tower on this tile
     */
    public boolean getPlaceable(){
        return placeable;
    }
    
    public int getMovementX (int checkpoint){
        return movementX[checkpoint];
    }

    public int getMovementY (int checkpoint){
        return movementY[checkpoint];
    }

    public void setWalkable (boolean w){
        walkable = w;
    }
    
    public boolean getWalkable(){
        return walkable;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}