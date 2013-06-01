import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the earth type enemies
 * they have high hp and armor but low speed
 * 
 * @James Lu
 * @0.01
 */
public class EarthMob extends Enemy
{
    public EarthMob(int hp, int armor, float speed, boolean isBoss, boolean isFlying){
        super (hp, armor, speed, isBoss, isFlying);
        if (isBoss){
            bg = new GreenfootImage ("Enemy/earthBoss.png");
        }else{
            bg = new GreenfootImage ("Enemy/earthMob.png");            
        }

        this.type        = 4;
        stringType       = "Earth";
        name             = "Earth Mob";
        this.setImage (bg);
    }
}