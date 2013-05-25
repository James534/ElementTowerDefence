import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireCreep here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireCreep extends Creep
{
    public FireCreep(int id){
        if (id == 1){
            bg = new GreenfootImage ("Enemy/fireCreep1.png");
            type            = 3;        
            stringType      = "Fire";
            name            = "Fire Creep";

            maxHp           = 50;
            currentHp       = maxHp;
            targetHp        = maxHp + maxHp/20;

            armor           = 2;
            speed           = 3f;
            isBoss          = false;
            flying          = false;
        }
        hpBar = new HealthBar (maxHp, currentHp);
        this.setImage (bg);
    }
}