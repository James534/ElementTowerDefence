import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The big creep button at the top <br>
 * Click on this button to send creeps
 * 
 * @author (James Lu) 
 * @version (0.01)
 */
public class CreepButton extends Button
{
    public CreepButton(){
        bg[0] = new GreenfootImage ("Buttons/CreepButton1.png");
        bg[1] = new GreenfootImage ("Buttons/CreepButton2.png");
        bg[2] = new GreenfootImage ("Buttons/CreepButton3.png");
        this.setImage (bg[0]);
    }    
}
