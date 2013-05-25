import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireTower extends Tower
{
    public FireTower() 
    {
        setImage("Towers/fireTower.png");
        attackRate = 20; // rate at which it attak
        range = 300; // maxiumum range of the tower 
        speed = 10;// speed of the projectile
        power = 10; 
        counter = attackRate;

        element = 3;
        elementString = "fire";

        name = "Fire Tower";

        desc.add ("Tower with average power,");
        desc.add ("range and attack range");
    }
}
