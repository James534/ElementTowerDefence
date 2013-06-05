import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The parent class for the effects that show up
 * 
 * @author (James Lu) 
 * @version (1.0)
 */
public class Effects extends Actor
{
    protected Map m;
    
    protected GreenfootImage bg;
    protected GreenfootImage blank;
    protected GreenfootImage[] cache;

    protected int counter;            //increases every act

    protected boolean show;           //whether to show this or not

    public Effects(){
        blank = new GreenfootImage ("blank.png");
    }

    protected void addedToWorld(World world){
        m = (Map) world;
    }
    
    /**
     * Passes true to make the effect show on the screen
     */
    public void show(boolean b){
        show = b;
    }
}