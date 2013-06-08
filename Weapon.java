import greenfoot.*;
import java.util.List;
/**
 * Generic Weapon class, contains moving and collision detection. <br>
 * Abstract class and does not get built in game
 * 
 * @author Terence Lai
 */
public abstract class Weapon extends Actor
{
    protected int speed;
    protected int power; 
    protected int aoe;
    protected int elementId;
    protected boolean active;
    protected Enemy target; 
    protected Map m;
    protected String element, type, name; 
    protected int[] debuffList;
    protected Weapon(int speed, int power,int aoe, int elementId, Enemy target ,String element,String type)
    {
        this.speed = speed;
        this.power = power;
        this.aoe = aoe;
        this.elementId = elementId;
        this.target = target; 
        this.active = true;
        this.element = element; 
        this.type = type; 
        String name = "Weapon/" + element + type + ".png";
        this.setImage(name);  

    }

    /**
     * passes map and turns the weapon towards the target when weapon is added to the world.
     */
    protected void addedToWorld(World world){
        m = (Map) world;
        if (target!= null && m.checkEnemy(target) == true){
            turnTowards(target.getX(), target.getY()); 
        }

    }

    /**
     * moves the weapon and checks to see if it hit any enemy.
     * Also checks to see thew type of weapon and applies special characteristics.
     **/
    protected void move() 
    {
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
        checkHit(); 

    }

    /**
     * Returns whether the tower is active in the game or not
     */
    public boolean getActive()
    {
        return active; 
    }

    /**
     * checks to see what the rocket hit, and removes it, aoe is splash damage.
     */
    private void checkHit() 
    {
        // Enemy e = (Enemy) getOneIntersectingObject(Enemy.class);
        List<Enemy> hit = getObjectsInRange(10,Enemy.class);
        if ( hit.size() != 0 && hit.contains (target) )
        {
            if (debuffList != null  ){
                for (int i = 0; i < this.debuffList.length; i++){

                    target.addDebuff(debuffList[i], 1, elementId, power);
                    //add this to the weapon class

                }
            }
            target.damage(power, elementId); 
            active = false ; //removes the object in the world]

        }
        if(m.withinField(this.getX(), this.getY()) == false )
        {
            active = false; 
        }

    }

    /**
     * add the debuff list from towers to the weapon shot itself, apple it, when it hits
     */
    public void addDebuff(int []dl , int level ){
        debuffList = dl; 
    }
}