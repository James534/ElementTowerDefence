import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The big tower button at the top of the Ui<br>
 * Click on this to build towers
 * 
 * @author James Lu
 * @version 1.0
 */
public class TowerButton extends Button
{
    public TowerButton(){
        bg[0] = new GreenfootImage ("Buttons/TowerButton1.png");
        bg[1] = new GreenfootImage ("Buttons/TowerButton2.png");
        bg[2] = new GreenfootImage ("Buttons/TowerButton3.png");
        this.setImage (bg[0]);
    }
}