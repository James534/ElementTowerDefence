import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button that starts the game
 * 
 * @author James Lu
 * @version 1.0
 */
public class StartButton extends SSButtons
{
    public StartButton(){
        bg[0] = new GreenfootImage ("Buttons/StartGame1.png");
        bg[1] = new GreenfootImage ("Buttons/StartGame2.png");
        bg[2] = new GreenfootImage ("Buttons/StartGame3.png");
        this.setImage (bg[0]);
    }
}
