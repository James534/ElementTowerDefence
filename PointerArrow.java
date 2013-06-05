import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The arrow to indicate what the user is selecting
 * 
 * @author James Lu
 * @version 0.01
 */
public class PointerArrow extends Actor
{
    private Map map;
    private GreenfootImage bg;

    private Enemy mob;
    public PointerArrow(){
        bg = new GreenfootImage ("UI/pointer.png");
        mob = null;

        this.setImage (bg);
    }

    protected void addedToWorld (World world){
        map = (Map) world;
    }

    public void act(){
        if (mob != null){
            if (mob.getHp() > 0 && map.checkEnemy (mob)){
                this.setLocation (mob.getX(), mob.getY()-20);                
            }
            else{
                mob = null;
                map.resetDefaultUi ();
            }
        }
    }

    /**
     * Sets which mob this arrow is following
     */
    public void setMob(Enemy e){
        mob = e;
    }

    /**
     * Returns which mob this arrow is following
     */
    public Enemy getMob(){
        return mob;
    }
}