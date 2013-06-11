import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Credits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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