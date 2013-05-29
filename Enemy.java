import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;
/**
 * The mobs that appear
 * 
 * CHANGELOG:
 * included the elemental damage
 * 
 * @author James Lu
 * @version 0.02
 */
public class Enemy extends Actor
{
    protected Map world;

    protected GreenfootImage bg;
    protected HealthBar hpBar;

    //movemnet variables
    private int x, y;
    private float realX, realY;
    private float accX, accY;
    private int stage;

    //stats
    protected String name;
    protected String stringType;
    protected int type;
    protected int maxHp;
    protected int currentHp;
    protected int targetHp;
    protected int armor;
    protected float speed;

    protected boolean flying;
    protected boolean isBoss;

    //debuffs
    private float slowX, slowY;
    protected ArrayList<Debuff> debuff;
    protected DebuffVisu dv;

    public Enemy(int hp, int armor, float speed, boolean isBoss, boolean isFlying){
        this();
        this.maxHp       = hp;
        this.currentHp   = maxHp;
        this.targetHp    = maxHp + maxHp/20;

        this.armor       = armor;
        this.speed       = speed;

        this.isBoss      = isBoss;
        this.flying      = isFlying;

        hpBar = new HealthBar (maxHp, currentHp);
    }

    public Enemy(){
        debuff = new ArrayList<Debuff>();
        dv = new DebuffVisu();
    }

    protected void addedToWorld(World world){
        this.world = (Map)world;
        x       = 0;
        y       = 0;
        stage   = 0;
        realX   = getX();
        realY   = getY();
        accX    = 0;
        accY    = 0;

        world.addObject (hpBar, Math.round (realX), Math.round (realY));
        world.addObject (dv, Math.round (realX), Math.round (realY));
    }

    public void act(){
        chooseMovement();
        debuffs();
    }

    /**
     * damages the mob
     * damage is the raw damage
     * NEED TO INCLUDE the type of damage
     */
    public int damageCalc(int d, int dmgType){
        float dmg;
        if (dmgType == -1){
            return d;
        }else{
            dmg = d * Data.elementDamage[type-1][dmgType-1];
        }
        float dmgRdu;       //dmg reduction
        if (armor >= 0){
            //dmgRdu = ( (0.06f * armor) / (1 + 0.06f * armor)) * 100f;     //dota armor values
            dmgRdu = dmg / ((100f + armor) / 100f);
            if (dmgRdu > 1){
                return Math.round (dmgRdu);
            }
            else{
                return 1;
            }
        } else{
            dmgRdu = (float) (1.0d - Math.pow (0.94d, -armor)) * 100;
            return Math.round (dmgRdu);
        }
    }

    /**
     * damages the enemy
     * 
     * includes elemental damage reduction/increase
     */
    public void damage (int dmg, int dmgType){
        int realDmg = damageCalc (dmg, dmgType);

        currentHp -= realDmg;
        hpBar.changeHp (currentHp);
        if (currentHp <=0){
            world.removeObject (hpBar);
            world.removeObject (dv);
            world.mobDie (this, false);
        }
    }

    /**
     * changes the targetting hp, NOT the actual hp
     * 
     * call after shooting a projectile at the enemy
     */
    public void targetDmg (int dmg, int dmgType){
        int realDmg = damageCalc (dmg, dmgType);
        targetHp -= realDmg;
    }

    /**
     * called when the weapon with a debuff hits the enemy
     */
    public void addDebuff (int id, int debuffLv, int element, int orgDmg){
        if (debuff.contains (id)){             //if there is already a debuff on the mob
            //increase debuff duration here
        }
        else{
            debuff.add (new Debuff (id, debuffLv, this));
            dv.setDebuff (id);
        }
    }

    private void debuffs(){
        slowX = 0;
        slowY = 0;
        for (int i = 0; i < debuff.size(); i++){
            Debuff d = debuff.get (i);
            d.run();

            //movement speed
            slowX = accX * d.getSlow();
            slowY = accY * d.getSlow();

            //damage
            damage (d.getDmg(), -1);
        }
    }

    /**
     * called by the debuff class to remove itself once its run out
     */
    public void removeDebuff(Debuff d){
        debuff.remove (d);
        dv.show (false);
    }

    private void chooseMovement(){
        if (x == 49 && y == 25){     
            world.removeObject (hpBar);
            world.removeObject (dv);
            currentHp = 0;
            world.mobDie (this, true);
        }
        else{
            if (x == 10 && y == 12 && stage == 0){
                stage = 1;
            }
            else if (x == 39 && y == 12 && stage == 1){
                stage = 2;
            }
            movement();
        }
    }

    /**
     * the method for the movement of the mob
     */
    private void movement(){
        Tile tempTile = world.getTile (x, y);
        int smoothMoveX, smoothMoveY;
        int intX, intY;

        if (realX-getX() > 1.5){
            realX = getX();
        }
        if (realY-getY() > 1.5){
            realY = getY();
        }

        accX = (tempTile.getMovementX(stage) * speed);
        accY = (tempTile.getMovementY(stage) * speed);

        //adds the acceleration to the real x and y
        realX += (accX - slowX);
        realY += (accY - slowY);

        //smooth movment variables, for making it stay in the grid
        smoothMoveX = Math.round((realX-12) % 20) - 10;
        smoothMoveY = Math.round((realY-15) % 20) - 10;

        //change the scale x and y values
        if (speed > 3){
            x = Math.round(((realX)-22) /20.0f);
            y = Math.round(((realY)-25) /20.0f);
        }
        else{
            if (accX > 0){
                x = Math.round( ((realX)-32) /20.0f);                
            }
            else if (accX < 0){
                if ((realX-22) % 20 >= 10){
                    x = Math.round ( ((realX)-22) /20.0f);
                }
                else if ( (realX-22) % 20 < 10){
                    x = (int)( ((realX)-3) /20.0f);
                }
            }
            if (accY > 0){                
                y = Math.round( ((realY)-35) /20.0f);
            }
            else if (accY < 0){
                if ((realY-25) % 20 >= 10){
                    y = Math.round ( ((realY)-25) /20.0f);
                }
                else if ( (realY-25) % 20 < 10){
                    y = (int)( ((realY)-6) /20.0f);
                }
            }
        }
        //if x is not moving, change x to fit in the grid, same for y
        if (accX == 0){
            realX -= smoothMoveX;
        }
        else if (accY == 0){
            realY -= smoothMoveY;
        }

        intX = Math.round (realX);
        intY = Math.round (realY);
        hpBar.changeLocation (intX, intY);
        dv   .changeLocation (intX, intY);
        setLocation (intX, intY);
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getHp(){
        return currentHp;
    }

    /**
     * call to check the targetting hp, to see if the enemy would of died already
     */
    public int getTargetHp(){
        return targetHp;
    }

    public int getArmor(){
        return armor;
    }

    public float getSpeed(){
        return speed;
    }

    public int getType(){
        return type;
    }

    public String getStringType(){
        return stringType;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Debuff> getDebuffs(){
        return debuff;
    }

    public ArrayList<Integer> getIntDebuffs(){
        ArrayList<Integer> al = new ArrayList<Integer> (debuff.size());
        for (int i = 0; i < debuff.size(); i++){
            al.add (debuff.get(i).getId());
        }
        return al;
    }
}