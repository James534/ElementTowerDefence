import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the mobs of fire element
 * 
 * @James Lu
 * @0.01
 */
public class FireMob extends Enemy
{
    public FireMob(int hp, int armor, float speed, boolean isBoss, boolean isFlying){
        super (hp, armor, speed, isBoss, isFlying);
        if (isBoss){
            bg = new GreenfootImage ("Enemy/fireBoss.png");
        }else{
            bg = new GreenfootImage ("Enemy/fireMob.png");            
        }

        this.type        = 3;
        stringType       = "Fire";
        name             = "Fire Mob";
        this.setImage (bg);
    }
}
