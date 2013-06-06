import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;
/**
 * The parent class for the creatures that appear
 * 
 * @author James Lu
 * @version 0.02
 */
public abstract class Enemy extends Actor
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
    protected float slowX, slowY;
    protected float armorRedu;
    protected float dot;
    protected ArrayList<Debuff> debuff;
    protected DebuffVisu dv;

    protected Enemy(int hp, int armor, float speed, boolean isBoss, boolean isFlying){
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
        dv = new DebuffVisu(this);
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
     * Damages the mob <br>
     * The first int is the damage, the second is the damage type <br>
     * Returns the amount of damage after armor reduction
     */
    public int damageCalc(int d, int dmgType){
        float dmg;
        if (dmgType == -1){
            return d;
        }else{
            dmg = d * Data.elementDamage[type-1][dmgType-1];
        }
        float dmgRdu;       //dmg reduction
        float tempArmor = armor - armorRedu;
        if (tempArmor >= 0){
            //dmgRdu = ( (0.06f * armor) / (1 + 0.06f * armor)) * 100f;     //dota armor values
            dmgRdu = dmg / ((100f + tempArmor) / 100f);
            if (dmgRdu > 1){
                return Math.round (dmgRdu);
            }
            else{
                return 1;
            }
        } else{
            dmgRdu = (float) (1.0d - Math.pow (0.94d, -tempArmor)) * 100;
            return Math.round (dmgRdu);
        }
    }

    /**
     * Damages the enemy <br>
     * First int is the damage, second int is the damage type
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
     * changes the targetting hp, NOT the actual hp <br>
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
        for (Debuff d : debuff){
            if (d.getId() == id){
                //increase duration
                d.increasseDuration();
                return;
            }
        }
        debuff.add (new Debuff (id, debuffLv, this));
        dv.setDebuff (id);
    }

    private void debuffs(){
        slowX = 0;
        slowY = 0;
        dot = 0;
        for (int i = 0; i < debuff.size(); i++){
            Debuff d = debuff.get (i);
            d.run();

            //movement speed
            slowX = accX * d.getSlow();
            slowY = accY * d.getSlow();

            //armor redu
            armorRedu = d.getRedu();

            //damage
            damage (d.getDmg(), -1);
            dot += d.getDOT();
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

    /**
     * Returns the max hp of the enemy
     */
    public int getMaxHp(){
        return maxHp;
    }

    /**
     * Returns the current hp of the enemy
     */
    public int getHp(){
        return currentHp;
    }

    /**
     * call to check the targetting hp, to see if the enemy would of died already
     */
    public int getTargetHp(){
        return targetHp;
    }

    /**
     * Returns the amount of armor the enemy has
     */
    public int getArmor(){
        return armor;
    }

    /**
     * Returns the enemy's speed
     */
    public float getSpeed(){
        return speed;
    }

    /**
     * Returns the type of the enemy
     */
    public int getType(){
        return type;
    }

    /**
     * Returns the type of the enemy, in string
     */
    public String getStringType(){
        return stringType;
    }

    /**
     * Returns the name of the enemy
     */
    public String getName(){
        return name;
    }

    /**
     * Returns an arraylist of debuffs that the enemy currently has
     */
    public ArrayList<Debuff> getDebuffs(){
        return debuff;
    }

    /**
     * Returns an arraylist of int ids of debuffs the enemy currently has
     */
    public ArrayList<Integer> getIntDebuffs(){
        ArrayList<Integer> al = new ArrayList<Integer> (debuff.size());
        for (int i = 0; i < debuff.size(); i++){
            al.add (debuff.get(i).getId());
        }
        return al;
    }

    /**
     * Returns the amount of slow this enemy has
     */
    public float getSlow(){
        if (accX != 0){
            return slowX;
        }else{
            return slowY;
        }
    }

    /**
     * Returns the armor reduction of the enmemy
     */
    public float getRedu(){
        return armorRedu;
    }

    /**
     * Returns the DOT (Damage Over Time) of the enemy
     */
    public float getDOT(){
        return dot;
    }
}