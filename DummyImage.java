import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main class for dummy buttons
 * buttons that dont do anything, just show stuff when the user hover overs
 * 
 * @author James Lu 
 * @version 1.0
 */
public class DummyImage extends Actor implements HoverInfo
{
    protected Map map;

    protected GreenfootImage bg;

    protected int hoverCounter;
    protected boolean selected;
    public DummyImage(){
        hoverCounter = 0;
    }

    protected void addedToWorld(World world){
        map = (Map) world;
    }

    public void act(){
        if (selected){
            selected = false;
            if (hoverCounter >= 50){
                map.hm.setData (this);
                int[] co = setCo();
                map.addObject (map.hm, co[0], co[1]);
            }
        }
        this.setImage (bg);        
    }

    protected int[] setCo(){
        int[] coordinates = new int[2];
        int tempX = this.getX();
        int tempY = this.getY();
        if (tempX < 600){
            coordinates[0] = tempX + tempX/2;
        }else{
            coordinates[0] = tempX - 150;
        }
        coordinates[1] = tempY - map.hm.getHeight()/2;
        return coordinates;
    }

    /** interface methods */
    public void hoverOver(){
        hoverCounter++;
    }

    public void changeImg(boolean s){
        selected = s;
    }

    public void resetCounter(){
        hoverCounter = 0;
    }
}