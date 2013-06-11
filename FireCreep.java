import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the fire creeps that the user sends
 * 
 * @author James Lu
 * @version 1.0
 */
public class FireCreep extends Creep
{
    /**
     * The int id is the level of the creep (1/2/3)
     */
    public FireCreep(int id){
        if (id >= 1 && id <= 3){
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
            bg = new GreenfootImage ("Enemy/fire" + id + ".png");
            type            = 3;        
            stringType      = "Fire";
            name            = "Fire Creep " + id;

            currentHp       = maxHp;
            targetHp        = maxHp + maxHp/20;

            isBoss          = false;
            flying          = false;
            hpBar = new HealthBar (maxHp, currentHp);
            this.setImage (bg);
        }
    }
}