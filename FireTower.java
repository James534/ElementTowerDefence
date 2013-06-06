import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a Fire Tower, and changes the characteristics of a generic <br>
 * tower when constructed. All actions are done within the superclass.
 * 
 * @author (Terence) 
 */
public class FireTower extends Tower
{
    public FireTower() 
    {
        fileName = "Towers/f";
        setImage("Towers/f1.png");
        attackRate = 12; // rate at which it attak
        range = 300; // maxiumum range of the tower 
        speed = 10;// speed of the projectile
        power = 20; 
        counter = attackRate;

        element = 3;
        elementString = "fire";

        name = "Fire Tower";
        cost = 10;

        desc.add ("Tower with average power,");
        desc.add ("range and attack range");

    }
}
