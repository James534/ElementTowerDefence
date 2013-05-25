import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreepButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
