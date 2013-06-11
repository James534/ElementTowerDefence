import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the mobs of air element <br>
 * they move fast, but are easy to kill
 * 
 * @author James Lu
 * @version 1.0
 */
public class AirMob extends Enemy
{
    public AirMob(int hp, int armor, float speed, boolean isBoss, boolean isFlying)
    {
        super (hp, armor, speed, isBoss, isFlying);
        if (isBoss){
            bg = new GreenfootImage ("Enemy/airBoss.png");
        }else{
            bg = new GreenfootImage ("Enemy/airMob.png");            
        }

        this.type        = 1;
        stringType       = "Air";
        name             = "Air Mob";
        this.setImage (bg);
    }
}