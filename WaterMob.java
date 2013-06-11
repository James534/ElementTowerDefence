import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the water type enemies <br>
 * they have the 2nd highest armor and hp, and moves faster than earth mobs
 * 
 * @author James Lu
 * @version 1.0
 */
public class WaterMob extends Enemy
{
    public WaterMob(int hp, int armor, float speed, boolean isBoss, boolean isFlying){
        super (hp, armor, speed, isBoss, isFlying);
        if (isBoss){
            bg = new GreenfootImage ("Enemy/waterBoss.png");
        }else{
            bg = new GreenfootImage ("Enemy/waterMob.png");            
        }

        this.type        = 2;
        stringType       = "Water";
        name             = "Water Mob";
        this.setImage (bg);
    }
}
