import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a water Tower, and changes the characteristics of a generic <br>
 * tower when constructed. All actions are done within the superclass.
 * 
 * 
 * @author (Terence) 
 */
public class WaterTower extends Tower
{//fast tower  short range
    public WaterTower() 
    {
        fileName = "Towers/w";
        setImage("Towers/w1.png");

        attackRate = 10;
        range = 50;
        speed = 6;
        power = 3; 
        counter = attackRate;

        element = 2;
        elementString = "water";

        name = "Water Tower";
        cost = 10;

        desc.add ("Insert Description Here");
        desc.add ("A description of the Tower");
    }
}
