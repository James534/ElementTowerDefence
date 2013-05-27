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
    private int duration;       //duration of the debuff
    private int dmg;            //damage of the debuff
    private float slow;         //slow of the debuff
    public Debuff(int id, int lv, Enemy e)
    {
        this.id = id;
        enemy = e;
        if (id == 0){        //stun
            if (lv == 1){
                duration = 10;
                dmg = 0;
                slow = 1f;
            }
        }
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

    public int getId(){
        return id;
    }

    public int getDuration(){
        return duration;
    }

    public int getDmg(){
        return dmg;
    }

    public float getSlow(){
        return slow;
    }
}