import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; 

/**
 * The parent class for the buttons that the user can click on
 * 
 * @author James Lu
 * @version 1.0
 */
public class Button extends Actor
{
    protected GreenfootImage[] bg;
    protected Map map;
    protected boolean selected;
    protected boolean clicked;

    public Button(){
        bg = new GreenfootImage [3];
        selected = false;
    }

    protected void addedToWorld(World world){
        map = (Map) world;
    }

    /**
     * Changes the image to be selected or not <br>
     * Pass true if the button is selected
     */
    public void changeImg(boolean s){
        selected = s;
    }

    /**
     * Change the image to be clicked <br>
     * Pass true if the button is clicked
     */
    public void clicked (boolean b){
        clicked = b;
    }

    /**
     * Sets the coordinates of the hovermenu <br>
     * returns the X and Y coordinates to place the hover menu
     */
    protected int[] setCo(){
        int[] coordinates = new int[2];
        int tempX = this.getX();
        int tempY = this.getY();
        if (tempX < 600){
            coordinates[0] = tempX + 160;
        }else{
            coordinates[0] = tempX - 160;
        }
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