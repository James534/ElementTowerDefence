import greenfoot.*;
/**
 * The class to hold all the data for mobs and towers
 * 
 * @author James Lu
 * @version 0.01
 */
public class Data  
{
    /** ------------------------------- Mob Data ------------------------------**/
    private mobQueue q;
    private mobNode mob;

    private int type;
    private int nextType;
    private int spawnRate;
    private int maxSpawn;

    private int hp;
    private int armor;
    private float speed;

    private boolean isBoss;
    private boolean isFlying;

    /** ---------------------------- Creep Data ------------------------------**/
    private mobNode[] creepList;
    public static final GreenfootImage[] creepButtonImg = 
        {
            new GreenfootImage ("Buttons/sendFC1.png"), new GreenfootImage ("Buttons/sendFC2.png"),
            new GreenfootImage ("Buttons/sendFC3.png")
        };

    /** -------------------------- Other Random Stuff ------------------------**/
    public static final float[][] elementDamage = 
        //2 supper effective, 1 normal, 0.75 not really effective, 0.5 not effective
        //            defender                    //attacker
        {   //air,  water,  fire,   earth       
            {0.75f, 2,      1,      0.5f},        //air
            {0.5f,  0.75f,  2,      1},           //water
            {1,     0.5f,   0.75f,  2},           //fire
            {2,     1,      0.5f,   0.75f}        //earth
        };

    public static final GreenfootImage[] debuffs = 
        {
            new GreenfootImage ("UI/stunned.png"), new GreenfootImage ("UI/lightning.png"),
            new GreenfootImage ("UI/iceDebuff.png"), new GreenfootImage ("UI/burning.png"),
            new GreenfootImage ("UI/stone.png")
        };

    public static final GreenfootImage[] stun = {
            new GreenfootImage ("debuff/stun/1.png"), new GreenfootImage ("debuff/stun/2.png"),
            new GreenfootImage ("debuff/stun/3.png"), new GreenfootImage ("debuff/stun/4.png"),
            new GreenfootImage ("debuff/stun/5.png"), new GreenfootImage ("debuff/stun/6.png")
        };

    public Data(){
        q = new mobQueue();
        populate();

        creepList = new mobNode[4];
        setData();
    }

    /** ---------------------------- Creep Data Methods ----------------------**/
    public int getCreepHp(int id){
        return creepList[id].hp;
    }

    private void setData(){
        int tempType;
        int tempHp;
        int tempArmor;
        int tempSpeed;
        boolean tempFlying;
        creepList[0] = new mobNode ();
    }

    /** ---------------------------- Mob Data Methods ------------------------**/
    private void updateData(){
        this.type       = mob.type;
        this.nextType   = mob.nextType;
        this.spawnRate  = mob.spawnRate;
        this.maxSpawn   = mob.maxSpawn;

        this.hp         = mob.hp;
        this.armor      = mob.armor;
        this.speed      = mob.speed;

        this.isBoss     = mob.isBoss;
        this.isFlying   = mob.flying;
    }

    public void nextLevel(){
        mob = q.dequeue();
        updateData();
    }

    /**
     * populates the mobs queue with 50 levels worth of data
     */
    public void populate(){
        int currentElement = 1;
        boolean isBoss = false;
        //hard code first level
        q.enqueueFirst (1, 2, 20, 12, 20, 1, 3, false, false);
        for (int i = 2; i <= 50; i++){
            currentElement = i%4;
            isBoss = i%10 == 0;
            if (!isBoss){                           //if its not a boss
                if      (currentElement == 1){      //air
                    type        = 1;
                    nextType    = 2;
                    spawnRate   = 20;
                    maxSpawn    = 12;

                    hp          = i * 20;
                    armor       = i/10 + 1;
                    speed       = 2;
                }
                else if (currentElement == 2){      //water
                    type        = 2;
                    nextType    = 3;
                    spawnRate   = 20;
                    maxSpawn    = 7;

                    hp          = i * 20;
                    armor       = i;
                    speed       = 1.7f;
                }
                else if (currentElement == 3){      //fire
                    type        = 3;
                    nextType    = 4;
                    spawnRate   = 5;
                    maxSpawn    = 20;

                    hp          = i * 10;
                    armor       = i/10 + 2;
                    speed       = 3;
                }
                else if (currentElement == 0){      //earth
                    type        = 4;
                    nextType    = 1;
                    spawnRate   = 20;
                    maxSpawn    = 10;

                    hp          = i * 30;
                    armor       = i + 1;
                    speed       = 1;
                }
            }
            else{                                   //if its a boss
                if      (currentElement == 1){      //air boss
                    type        = 1;
                    nextType    = 2;

                    hp          = i     * 20;
                    armor       = i/10  + 1;
                    speed       = 4;
                }
                else if (currentElement == 2){      //water boss
                    type        = 2;
                    nextType    = 3;

                    hp          = i * 20;
                    armor       = i + i/5;
                    speed       = 0.3f;
                }
                else if (currentElement == 3){      //fire boss
                    type    = 3;
                    nextType= 4;

                    hp      = i    * 20;
                    armor   = i/10 + 2;
                    speed   = 5;
                }
                else if (currentElement == 0){      //earth boss
                    type    = 4;
                    nextType= 1;

                    hp      = i * 60;
                    armor   = i + 1;
                    speed   = 0.5f;
                }
                spawnRate   = 5;
                maxSpawn    = 1;
            }

            q.enqueue (type, nextType, spawnRate, maxSpawn, hp, armor, speed, isBoss, isFlying);
        }
    }

    /** ------------------------------- methods that return data -------------------------------*/
    public int getType(){
        return type;
    }

    public int getNextType(){
        return nextType;
    }

    public int getSpawnRate(){
        return spawnRate;
    }

    public int getMaxSpawn(){
        return maxSpawn;
    }

    public int getHp(){
        return hp;
    }

    public int getArmor(){
        return armor;
    }

    public float getSpeed(){
        return speed;
    }

    public boolean ifBoss(){
        return isBoss;
    }

    public boolean ifFlying(){
        return isFlying;
    }

    /**================================= Data Structure Classes ==================================**/
    public class mobQueue{
        private mobNode first;
        private mobNode last;
        private int length;
        public mobQueue(){
            first   = null;
            last    = null;
            length  = 0;
        }

        public boolean isEmpty(){
            return length == 0;
        }

        public void enqueue(int type, int nextType, int spawnRate, int maxSpawn, 
        int hp, int armor, float speed, boolean isBoss, boolean flying){
            mobNode temp = new mobNode (type, nextType, spawnRate, maxSpawn, hp, armor, speed, isBoss, flying);
            last.next = temp;
            last      = temp;
            length++;
        }

        public void enqueueFirst(int type, int nextType, int spawnRate, int maxSpawn, 
        int hp, int armor, float speed, boolean isBoss, boolean flying){
            first = new mobNode (type, nextType, spawnRate, maxSpawn, hp, armor, speed, isBoss, flying);
            last  = first;
        }

        public mobNode dequeue(){
            if (length != 0){
                mobNode temp = first;
                first        = first.next;
                length --;
                return temp;
            }
            return null;
        }
    }
    private class mobNode{
        private mobNode next;

        private int     type;
        private int     nextType;
        private int     spawnRate;
        private int     maxSpawn;
        private int     hp;
        private int     armor;
        private float   speed;

        private boolean isBoss;
        private boolean flying;

        public mobNode(){
            next = null;
        }

        public mobNode(int type, int nextType, int spawnRate, int maxSpawn, 
        int hp, int armor, float speed, boolean isBoss, boolean flying){
            this();
            this.type       = type;
            this.nextType   = nextType;
            this.spawnRate  = spawnRate;
            this.maxSpawn   = maxSpawn;
            this.hp         = hp;
            this.armor      = armor;
            this.speed      = speed;
            this.isBoss     = isBoss;
            this.flying     = flying;
        }
    }
}