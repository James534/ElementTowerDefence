import greenfoot.*;
import java.util.List;
public class Weapon extends Actor
{
    protected int speed;
    protected int power; 
    protected int aoe;
    protected int elementId;
    protected boolean active;
    protected Enemy target; 
    protected Map m;
    protected String element, type, name; 
    public Weapon(int speed, int power,int aoe, int elementId, Enemy target ,String element,String type)
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
        //this.setImage("laser.png"); 
    }

    /**
     * moves the weapon and checks to see if it hit any enemy.
     * Also checks to see thew type of weapon and applies special characteristics.
     **/
    protected void move() 
    {
        m= (Map) getWorld();

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
        checkHit(1); 

    }

    public boolean getActive()
    {
        return active; 
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
                Enemy e = hit.get(0);
                if (this instanceof Artillery )
                {
                    List<Enemy> temp = getObjectsInRange(aoe,Enemy.class);
                    for (int i = 0; i < temp.size();i++)
                    {           
                        Enemy x = temp.get(i);
                        if (x!= null)
                        {
                            x.damage(power, 1); 
                        }

                    }
                }
                else 
                {  target.damage(power, elementId); }

                active = false ; //removes the object in the world

        }
        if(m.withinField(this.getX(), this.getY()) == false )
        {
            active = false; 
        }

    }

}