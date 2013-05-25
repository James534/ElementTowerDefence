import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EarthTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EarthTower extends Tower
{
    public EarthTower() 
    {
        super();
        setImage("Towers/earthTower.png");
        attackRate = 10; // rate at which it attak
        range = 500; // maxiumum range of the tower 
        speed = 15;// speed of the projectile
        power = 1;
        
        element = 4;
        elementString = "earth";
        
        name = "Earth Tower";
        
        desc.add ("A description of the Tower");
    }
}
