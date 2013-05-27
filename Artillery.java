import greenfoot.*; 
import java.util.List;
public class Artillery extends Weapon
{
    String name;

    public Artillery(int speed, int power,int aoe, int eleId, Enemy target, String element)
    {
        super( speed,  power, aoe, eleId, target, element ,"Shell"); //calls the super classes constructtor, nothign changed
    }

    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (active) // move the weapon when active, called from the superclass
        {
            move();
            checkHit(aoe);
        }
        else
        {
            m.removeObject(this);
        }
    }

    protected void move(){
        if (target!= null && m.checkEnemy(target) == true)
        {
            //System.out.println(target);

            turnTowards(target.getX(), target.getY()); 
            move(speed) ;
        }//IllegalActor here making it turn when its not there  (DID JAMES ADDD THIS?)
        else 
        {
            active = false; 
        }
    }

    /**
     * checks to see what the rocket hit, and removes it, aoe is splash damage.
     */
    private void checkHit(int aoe) 
    {
        // Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        List<Enemy> hit = getObjectsInRange(10,Enemy.class);
        if ( hit.size() != 0 && hit.get(0) == target)
        {
            List<Enemy> temp = getObjectsInRange(aoe,Enemy.class);
            for (int i = 0; i < temp.size();i++)
            {           
                Enemy x = temp.get(i);
                if (x!= null)
                {
                    x.damage(power, elementId); 
                }
            }

            active = false ; //removes the object in the world
        }
        if(m.withinField(this.getX(), this.getY()) == false )
        {
            active = false; 
        }

    }
}