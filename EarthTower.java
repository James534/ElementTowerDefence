import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Creates a Earth Tower, and changes mutates the characteristics of a generic <br>
 * tower when constructed. All actions are done within the superclass.
 * 
 * @author (Terence Lai) 
 */
public class EarthTower extends Tower
{
    public EarthTower() 
    {
        fileName = "Towers/e";
        setImage("Towers/e1.png");
        attackRate = 45; // rate at which it attak
        range = 500; // maxiumum range of the tower 
        speed = 15;// speed of the projectile
        power = 25;
        counter = attackRate;

        element = 4;
        elementString = "earth";

        name = "Earth Tower";
        cost = 10;

        desc.add ("Earth Tower Description");
    }
}
