import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; 

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    protected GreenfootImage[] bg;
    protected Map map;
    protected boolean selected;
    protected boolean clicked;
    protected int hoverCounter;

    public Button(){
        bg = new GreenfootImage [3];
        selected = false;
    }

    protected void addedToWorld(World world){
        map = (Map) world;
    }

    public void changeImg(boolean s){
        selected = s;
    }

    public void clicked (boolean b){
        clicked = b;
    }

    public void hoverOver(){
        hoverCounter++;
    }

    public void resetCounter(){
        hoverCounter = 0;
    }

    protected int[] setCo(){
        int[] coordinates = new int[2];
        int tempX = this.getX();
        int tempY = this.getY();
        coordinates[0] = tempX + tempX/2;
        coordinates[1] = tempY - map.hm.getHeight()/2;
        return coordinates;
    }
    
    public void act(){
        if (clicked){
            this.setImage (bg[2]);
        }
        else if (selected){
            selected = false;
            this.setImage (bg[1]);
        }
        else{
            this.setImage (bg[0]);
        }
    }
}