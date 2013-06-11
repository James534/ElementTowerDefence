import greenfoot.*; 
/**
 * The basic weapon of each tower. Used for all level 1 towers <br>
 * 
 * @author Terence Lai
 */
public class Normal extends Weapon
{
    String name ;

    public Normal(int speed, int power,int aoe, int eleId, Enemy target, String element)
    {
        super( speed,  power, aoe, eleId, target, element ,"Bullet"); //calls the super classes constructtor, nothign changed
    }

    public void act()
    {
        if (active) // move the weapon when active, called from the superclass
        {
            move();
        }
        else
        {
            m.removeObject(this);
        }
    }
}