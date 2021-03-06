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
        attackRate = 20; // rate at which it attak
        range = 500; // maxiumum range of the tower 
        speed = 15;// speed of the projectile
        power = 2;
        counter = attackRate;

        element = 4;
        elementString = "earth";

        name = "Earth Tower";
        cost = 10;

        desc.add ("A long range tower");
        desc.add ("Low damage,");
        desc.add ("Medium attack rate");
    }
}
