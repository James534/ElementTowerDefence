import greenfoot.*;
/**
 * The class to hold various data
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

    /** -------------------------- Other Random Stuff ------------------------**/
    /** The elemental damage chart*/
    public static final float[][] elementDamage = 
        //2 supper effective, 1 normal, 0.75 not really effective, 0.5 not effective
        //            defender                    //attacker
        {   //air,  water,  fire,   earth       
            {0.75f, 2,      1,      0.5f},        //air
            {0.5f,  0.75f,  2,      1},           //water
            {1,     0.5f,   0.75f,  2},           //fire
            {2,     1,      0.5f,   0.75f}        //earth
        };
    public static final String[] elementName = 
        {
            "Air", "Water", "Fire", "Earth"
        };

    public static final GreenfootImage[] debuffs = 
        {
            new GreenfootImage ("Debuff/stunned.png"), new GreenfootImage ("Debuff/lightning.png"),
            new GreenfootImage ("Debuff/freeze.png"), new GreenfootImage ("Debuff/burning.png"),
            new GreenfootImage ("Debuff/stone.png")
        };

    public static final int[][] debuffDuration = {
            //stun   lightning, freeze, burn,   earth
            {   11,     100,    100,    100,    100}
        };

    public static final double[] debuffChance = {
            0.05f, 0.1f, 0.15f, 0.07f, 0.09f
        };

    public static final GreenfootImage[] stun = {
            new GreenfootImage ("Debuff/stun/1.png"), new GreenfootImage ("Debuff/stun/2.png"),
            new GreenfootImage ("Debuff/stun/3.png"), new GreenfootImage ("Debuff/stun/4.png"),
            new GreenfootImage ("Debuff/stun/5.png"), new GreenfootImage ("Debuff/stun/6.png")
        };

    public static final GreenfootImage[] air = {
            new GreenfootImage ("Debuff/air/1.png"),  new GreenfootImage ("Debuff/air/1.png")
        };

    public static final GreenfootImage[] freeze = {
            new GreenfootImage ("Debuff/freeze/1.png"), new GreenfootImage ("Debuff/freeze/1.png")
        };

    public static final GreenfootImage[] burn = {
            new GreenfootImage ("Debuff/burn/1.png"), new GreenfootImage ("Debuff/burn/1.png")
        };

    public static final GreenfootImage[] stone = {
            new GreenfootImage ("Debuff/stone/1.png"), new GreenfootImage ("Debuff/stone/1.png")
        };

    public Data(){
        q = new mobQueue();
        populate();
    }

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

    /**
     * Updates this class with the next level enemy stats
     */
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
                    armor       = i;
                    speed       = 2;
                }
                else if (currentElement == 2){      //water
                    type        = 2;
                    nextType    = 3;
                    spawnRate   = 20;
                    maxSpawn    = 7;

                    hp          = i * 20;
                    armor       = i / 3 * 4;
                    speed       = 1.7f;
                }
                else if (currentElement == 3){      //fire
                    type        = 3;
                    nextType    = 4;
                    spawnRate   = 5;
                    maxSpawn    = 20;

                    hp          = i * 10;
                    armor       = i / 2;
                    speed       = 2.4f;
                }
                else if (currentElement == 0){      //earth
                    type        = 4;
                    nextType    = 1;
                    spawnRate   = 20;
                    maxSpawn    = 10;

                    hp          = i * 30;
                    armor       = i * 2;
                    speed       = 1;
                }
                hp = Math.round(hp * i * 0.3f);
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
                    speed   = 2.5f;
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
                hp =Math.round( hp * 0.5f*i + 800) ;
            }

            q.enqueue (type, nextType, spawnRate, maxSpawn, hp, armor, speed, isBoss, isFlying);
        }
    }

    /** ------------------------------- methods that return data -------------------------------*/
    /**
     * Returns the type of the current enemy
     */
    public int getType(){
        return type;
    }

    /**
     * Returns the type of the next wave enemy
     */
    public int getNextType(){
        return nextType;
    }

    /**
     * Returns the spawn rate of the current enemy
     */
    public int getSpawnRate(){
        return spawnRate;
    }

    /**
     * Returns the max amount of the current enemy that will spawn
     */
    public int getMaxSpawn(){
        return maxSpawn;
    }

    /**
     * Returns the max hp of the current enemy
     */
    public int getHp(){
        return hp;
    }

    /**
     * Returns the armor of the current enemy
     */
    public int getArmor(){
        return armor;
    }

    /**
     * Returns the speed of the current enemy
     */
    public float getSpeed(){
        return speed;
    }

    /**
     * Returns true if the currenent emeny is a boss
     */
    public boolean ifBoss(){
        return isBoss;
    }

    /**
     * Returns true if the currenent enemy is flying
     */
    public boolean ifFlying(){
        return isFlying;
    }

    /**
     * Returns the max number of enemies in the next wave
     */
    public int getNextWaveSpawnCount(){
        return q.first.next.maxSpawn;
    }

    /**
     * Returns the current max spawn count without dequing
     */
    public int getCurrentMaxSpawn(){
        return q.first.maxSpawn;
    }

    /**================================= Data Structure Class ==================================**/
    private class mobQueue{
        private mobNode first;
        private mobNode last;
        private int length;
        public mobQueue(){
            first   = null;
            last    = null;
            length  = 0;
        }

        /**
         * Returns true if the queue is empty
         */
        public boolean isEmpty(){
            return length == 0;
        }

        /**
         * Enques a level's worth of information <br>
         * Each level's mob stats is stored here
         */
        public void enqueue(int type, int nextType, int spawnRate, int maxSpawn, 
        int hp, int armor, float speed, boolean isBoss, boolean flying){
            mobNode temp = new mobNode (type, nextType, spawnRate, maxSpawn, hp, armor, speed, isBoss, flying);
            last.next = temp;
            last      = temp;
            length++;
        }

        /**
         * Only called once; for the first time to initalize the queue <br>
         * The stats of the first level is stored in here
         */
        public void enqueueFirst(int type, int nextType, int spawnRate, int maxSpawn, 
        int hp, int armor, float speed, boolean isBoss, boolean flying){
            first = new mobNode (type, nextType, spawnRate, maxSpawn, hp, armor, speed, isBoss, flying);
            last  = first;
        }

        /**
         * Returns a mobNode that stores the data of the current wave
         */
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

        /**
         * Creates a new mobNode with the passed on parameters <br>
         * It holds the data for each wave
         */
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