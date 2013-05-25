import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the mobs of air element
 * they move fast, but are squishy
 * 
 * 
 * @James Lu
 * @0.01
 */
public class AirMob extends Enemy
{
    public AirMob(int hp, int armor, float speed, boolean isBoss, boolean isFlying)
    {
        super (hp, armor, speed, isBoss, isFlying);
        bg = new GreenfootImage ("Enemy/airMob.png");

        this.type        = 1;
        stringType       = "Air";
        name             = "Air Mob";
        this.setImage (bg);
    }
}