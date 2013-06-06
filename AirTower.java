import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a Air Tower, and changes the characteristics of a generic <br>
 * tower when constructed. All actions are done within the superclass.
 * 
 * @author (Terence) 
 */
public class AirTower extends Tower
{
    public AirTower()
    {
        fileName = "Towers/a";
        setImage("Towers/a1.png");
        attackRate = 15; // rate at which it attak
        range = 400; // maxiumum range of the tower 
        speed = 10;// speed of the projectile
        power = 4;
        counter = attackRate;

        element = 1;
        elementString = "air";

        name = "Air Tower";
        cost = 10;

        desc.add ("lightweight tower");
        desc.add ("Average range and power");
    }

    private void upgradeImg(){

    }
}
