import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterTower extends Tower
{//fast tower  short range
    public WaterTower() 
    {
        fileName = "Towers/w";
        setImage("Towers/w1.png");

        attackRate = 5;
        range = 50;
        speed = 4;
        power = 1; 
        counter = attackRate;

        element = 2;
        elementString = "water";

        name = "Water Tower";
        cost = 10;

        desc.add ("Insert Description Here");
        desc.add ("A description of the Tower");
    }
}
