import greenfoot.*;
/** 
 * The laser Weaopon available as the last upgrade from tower.<br>
 * overides the superclass checkhit method <br>
 * 
 * @author (Terence Lai) 
 */
public class Laser extends Weapon
{
    public Laser(int speed, int power,int aoe, int eleId, Enemy target, String element)
    {
        super( speed,  power, aoe, eleId, target, element,"Lazer"); //calls the super classes constructtor, nothign changed
    }

    public void act()
    {
        move (speed);
        checkHit(1);
    }

    /**
     * checks to see what the rocket hit, and removes it, aoe is splash damage.
     */
    private void checkHit(int aoe) 
    {
        Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        if (e!= null)
        {
            if (debuffList != null  ){
                for (int i = 0; i < this.debuffList.length; i++){

                    e.addDebuff(debuffList[i], 1, elementId, power);
                    //add this to the weapon class

                }
            }
            e.damage(power, 1);
        }
        if(m.withinField(this.getX(), this.getY()) == false )
        {
            m.removeObject (this);
        }
    }
}