import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TowerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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