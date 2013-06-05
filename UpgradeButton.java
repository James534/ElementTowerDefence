import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button to upgrade the current selected tower
 * 
 * @author James Lu
 * @version 1.0
 */
public class UpgradeButton extends UIButton
{
    public UpgradeButton(){
        bg[0] = new GreenfootImage ("Buttons/upgrade1.png");
        bg[1] = new GreenfootImage ("Buttons/upgrade2.png");
        bg[2] = new GreenfootImage ("Buttons/upgrade3.png");
    }
}
