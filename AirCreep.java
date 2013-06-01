import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireCreep here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AirCreep extends Creep
{
    public AirCreep(int id){
        if (id > 0 && id < 4){
            if (id == 1){
                maxHp           = 50;
                armor           = 2;
                speed           = 3f;
            }else if (id == 2){
                maxHp           = 250;
                armor           = 10;
                speed           = 3f;
            }else if (id == 3){
                maxHp           = 500;
                armor           = 20;
                speed           = 3f;
            }
            bg = new GreenfootImage ("Enemy/air"+id+".png");
            type            = 1;        
            name            = "Air Creep " + id;
            stringType      = "Air";

            currentHp       = maxHp;
            targetHp        = maxHp + maxHp/20;

            isBoss          = false;
            flying          = false;
            hpBar = new HealthBar (maxHp, currentHp);
            this.setImage (bg);
        }
    }
}