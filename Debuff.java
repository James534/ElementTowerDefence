/**
 * The class of debuffs
 * each debuff is it's own class
 * 
 * @author James Lu
 * @version 1.0
 */
public class Debuff 
{
    private Enemy enemy;        //the enemy this debuff belongs to
    private int id;             //id of the debuff
    private int lv;
    private int duration;       //duration of the debuff
    private int maxDuration;    //the max duration
    private int dmg;            //damage of the debuff
    private int rate;           //rate of the dot
    private float slow;         //slow of the debuff, precentage
    private float armorRedu;    //armor reduction
    private String name;
    public Debuff(int id, int lv, Enemy e)
    {
        this.id = id;
        this.lv = lv;
        enemy = e;
        if (id == 0){        //stun
            if (lv == 1){
                dmg = 0;
                rate = 100;
                slow = 1f;
                armorRedu = 0;
                name = "Stun";
            }
        }else if (id == 1){     //lightning
            if (lv == 1){
                dmg = 3;
                rate = 30;
                slow = 0.05f;
                armorRedu = 2;
                name = "lightning";
            }
        }else if (id == 2){     //freeze
            if (lv == 1){
                dmg = 1;
                rate = 20;
                slow = 0.5f;
                armorRedu = 1;
                name = "Freeze";
            }
        }else if (id == 3){     //burn
            if (lv == 1){
                dmg = 10;
                rate = 10;
                slow = 0.005f;
                armorRedu = 1;
                name = "Burn";
            }
        }else if (id == 4){     //stone
            if (lv == 1){
                dmg = 3;
                rate = 30;
                slow = 0.75f;
                armorRedu = 5;
                name = "Stone";
            }
        }
        maxDuration = Data.debuffDuration[lv-1][id];
        duration = maxDuration;
    }

    public Debuff (int id, int lv){
        this (id, lv, null);
    }

    /**
     * the "act" method for the debuff class
     * have to call this class manually since its not an actor
     */
    public void run(){
        duration--;
        if (duration == 0){
            enemy.removeDebuff (this);
        }
    }

    /**
     * increases the duration
     */
    public void increasseDuration (){
        duration = maxDuration;
    }

    public int getId(){
        return id;
    }

    public int getDuration(){
        return duration;
    }

    public int getMaxDuration(){
        return maxDuration;
    }

    public int getDmg(){
        if (duration > 0 && rate % duration == 0){
            return dmg;
        }
        else{
            return 0;
        }
    }

    public int getRate(){
        return rate;
    }

    public float getDOT(){
        float r = rate;
        return dmg/r;
    }

    public float getSlow(){
        return slow;
    }

    public float getRedu(){
        return armorRedu;
    }
    public String getName()
{
return name; 
}

   
}