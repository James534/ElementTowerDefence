import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterMob here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
