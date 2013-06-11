import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button the user clicks to enter the credits screen
 * 
 * @author James Lu
 * @version 1.0
 */
public class Credits extends SSButtons
{
    public Credits(){
        bg[0] = new GreenfootImage ("Buttons/credits1.png");
        bg[1] = new GreenfootImage ("Buttons/credits2.png");
        bg[2] = new GreenfootImage ("Buttons/credits3.png");
        this.setImage (bg[0]);
    }
}