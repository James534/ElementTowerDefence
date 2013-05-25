import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AirTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AirTower extends Tower
{
    public AirTower()
    {
        super();
        setImage("Towers/airTower.png");
        attackRate = 20; // rate at which it attak
        range = 300; // maxiumum range of the tower 
        speed = 10;// speed of the projectile
        power = 50;
        counter = attackRate;
        
        element = 1;
        elementString = "air";
        
        name = "Air Tower";
        
        desc.add ("lightweight tower");
        desc.add ("Average range and power");
    }
}
