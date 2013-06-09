import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button that allows the user to go back to the main menu <br>
 * Used in the tutorial
 * 
 * @author James Lu
 * @version 1.0
 */
public class BackButton extends SSButtons
{
    public BackButton(){
        bg[0] = new GreenfootImage ("Buttons/back1.png");
        bg[1] = new GreenfootImage ("Buttons/back2.png");
        bg[2] = new GreenfootImage ("Buttons/back3.png");
        this.setImage (bg[0]);
    }
}
