import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the earth creeps that the user sends
 * 
 * @author James Lu
 * @version 1.0
 */
public class EarthCreep extends Creep
{
    public EarthCreep(int id){
        if (id > 0 && id < 4){
            if (id == 1){
                maxHp           = 50;
                armor           = 10;
                speed           = 3f;
            }else if (id == 2){
                maxHp           = 500;
                armor           = 15;
                speed           = 3.2f;
            }else if (id == 3){
                maxHp           = 1500;
                armor           = 25;
                speed           = 4f;
            }
            bg = new GreenfootImage ("Enemy/earth"+id+".png");
            type            = 4;        
            name            = "Earth Creep " + id;
            stringType      = "Earth";

            currentHp       = maxHp;
            targetHp        = maxHp + maxHp/20;

            isBoss          = false;
            flying          = false;
            hpBar = new HealthBar (maxHp, currentHp);
            this.setImage (bg);
        }
    }
}