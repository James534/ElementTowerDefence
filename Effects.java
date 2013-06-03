import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effects here.
 * 
 * @author (Terence Lai) 
 * @version (a version number or a date)
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
        blank = new GreenfootImage (1,1);
    }

    protected void addedToWorld(World world){
        m = (Map) world;
    }
    
    public void show(boolean b){
        show = b;
    }
}