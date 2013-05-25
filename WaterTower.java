import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterTower extends Tower
{
    public WaterTower() 
    {
        super();
        setImage("Towers/waterTower.png");

        attackRate = 2;
        range = 50;
        speed = 4;
        power = 20; 
        counter = attackRate;

        element = 2;
        elementString = "water";

        name = "Water Tower";

        desc.add ("Insert Description Here");
        desc.add ("A description of the Tower");
    }
}
