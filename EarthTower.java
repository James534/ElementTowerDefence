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
        fileName = "Towers/e";
        setImage("Towers/e1.png");
        attackRate = 10; // rate at which it attak
        range = 500; // maxiumum range of the tower 
        speed = 15;// speed of the projectile
        power = 1;
        counter = attackRate;
        
        element = 4;
        elementString = "earth";
        
        name = "Earth Tower";
        
        desc.add ("Earth Tower Description");
    }
}
