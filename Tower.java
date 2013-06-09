import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; 
import java.util.List; 
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * The generic tower that the user places onto the map.<br>
 * User can only build elemntal towers which inherites this tower <br>
 * contains the all enemy tracking, and firing algorithms.
 * 
 * 
 * @author (Terence Lai + James Lu) 
 * @version (2.00)
 */
public abstract class Tower extends Actor
{
    protected boolean  active = false; //will not fire unless it is true 

    protected Map map;
    protected int counter; //counter used to place the object on the second click (counter ==3 )
    protected int gridX, gridY ;// refrence to what grid it is on
    protected Weapon basicAttack; //change this when upgraded.  
    protected int attackRate;
    protected int power;
    protected int range;
    protected int speed;
    protected String type; 
    protected int level; 

    //added by james
    protected Enemy             targetedMob;    
    protected String            name;
    protected String            fileName;
    protected ArrayList<String> desc;   
    //the element of the tower (air, water, fire, earth)
    protected int               element;
    protected String            elementString;      //the string of the element    
    protected int[]             upgradeCost;
    //targetting priority, 1 for closest, 2 for furthest, etc
    protected int               targetPriority;     
    //the number of debuffs this tower has bought
    protected LinkedList<Debuff>debuffs;
    protected int               cost;         //the cost of the tower
    protected int               currentCost;    //how much money the user has spent into this tower
    protected Tower()
    {
        name = "";
        desc = new ArrayList<String>(5);
        type = "";

        targetedMob = null;
        targetPriority = 1;
        level = 1; 
        upgradeCost = new int[3];
        upgradeCost[0] = 50;
        upgradeCost[1] = 100;
        upgradeCost[2] = 150;
        debuffs = new LinkedList<Debuff>();
    }

    /**
     * passes map to all towers when all towers are added to world
     */
    protected void addedToWorld(World world){
        map = (Map) world;
        currentCost = cost;
    }

    /**
     * Set the tower to be live in the game. set false, to stop its functionality 
     */
    public void setActive(boolean n)
    {
        this.active=n;
        if (active){
            counter = attackRate;
        }
    }

    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    { 
        // System.out.println("Its Attacking with speed: " + attackrRate);
        if (active){
            attack();
        }
    }  

    /**
     * Allows the world to pass the grid coordinates to the object as a refrence
     */
    public void setGridX(int x)
    {
        this.gridX = x;
    }

    /**
     * Allows the world to pass the grid Coordinates to the object
     */
    public void setGridY(int y)
    {
        this.gridY = y;
    }

    /**
     * Access the grid X coordinates on field (Not actual coordinates)
     */
    public int getGridX()
    {
        return gridX;
    }

    /**
     * Acess the grid Y coordinates on field (Not actual Corrdinates)
     */
    public int getGridY()
    {
        return gridY;
    }

    /**
     * targeting and firing method for towers to attack the enemy.
     */
    protected void attack()
    {
        if (counter >= attackRate ) //used for the attack cycle of the weapon
        {
            if (targetedMob != null && targetedMob.getHp() > 0 && 
            getDistance (targetedMob) <= range/2){  
                //if the mob its attacking is not dead     
                launch();
            }
            else{
                //get a group of enemies that is within range
                Enemy attack = withinRange(targetPriority);
                if (attack != null){
                    targetedMob = attack;      
                    launch();
                }
            }
        }
        else{
            counter++;
        }
    }

    private void launch(){
        if (targetedMob.getTargetHp() >= 0){

            if (type.equals("pierce"))
            {//piercing strike
                basicAttack = new Laser(speed, power,1,element,targetedMob,elementString);  
            }
            else if (type.equals("artillery"))
            {
                basicAttack = new Artillery(speed, power, 150, element, targetedMob, elementString);    
                targetedMob.targetDmg (power, element);            
            }
            else
            {  //the default attack
                basicAttack = new Normal(speed, power,1, element, targetedMob, elementString);  
                targetedMob.targetDmg (power, element);
            }

            fireDebuff();
            map.s.play (element);
            map.addObject (basicAttack,this.getX(), this.getY());
            counter = 0 ; 
        }
    }

    private void fireDebuff() 
    {
        int[] debuffList = getDebuffs(); 
        ArrayList <Integer> numDebuffs = new ArrayList<Integer>();
        for (int i = 0; i < debuffList.length; i++){
            if (Math.random() <= Data.debuffChance[debuffList[i] ] ){
                numDebuffs.add (debuffList[i]);
                //add this to the weapon class
            }
        }
        int[] fDL = new int [numDebuffs.size()];
        for (int i = 0; i < fDL.length; i++){
            fDL[i] = numDebuffs.get (i);
        }
        basicAttack.addDebuff(fDL, 1 );
    }

    /**
     * refunds the money back to the player 
     */
    public void sell()
    {
        map.changeMoney(Math.round(currentCost*0.75f)) ;//add money 75% of them money back.
    }

    /**
     * Checks to see if any enemies are within range of the tower
     * 
     * int parameter is the targetting priority
     * 1 is closest enemy
     * 2 is furthest 
     */
    private Enemy withinRange(int tp)
    {
        List<Actor> temp = getObjectsInRange(range/2, Enemy.class);
        ArrayList<Enemy> enemyList = new ArrayList();
        Enemy targetEnemy = null;
        if (temp.size() > 0){
            if (tp == 1){
                Enemy e;
                double hyp = range;
                for (int i = 0; i < temp.size();i++)
                {
                    if (temp.get(i) instanceof Enemy) 
                    {
                        e = (Enemy) temp.get(i); 
                        if (e.getHp() > 0 && e.getTargetHp() > 0){
                            double h = getDistance (e);
                            if (h < hyp){       
                                //if the current hyp is less than the starting hyp
                                hyp = h;
                                targetEnemy = e;
                            }
                        }
                    }
                }
            }
        }
        //System.out.println(enemyList);
        return targetEnemy; 
    }

    private double getDistance(Enemy e){        
        int tempX = (e.getX() - this.getX());
        int tempY = (e.getY() - this.getY());
        int adj = tempX * tempX;
        int opp = tempY * tempY;
        double h = Math.sqrt ((adj + opp));
        return h;
    }

    /**
     * chanes the weapon of the tower.
     */
    private void changeWeapon(String type){
        this.type = type; 
    }

    /**
     * Upgrades the tower by changing base stats and changes weapon or add debuff
     */
    public void upgrade(boolean button)
    {
        if (level < 3)
        {
            power = power + Math.round(power * .5f);
            range = range + Math.round(range * .5f);

            if (attackRate >  0){
                attackRate-=3;
                if (attackRate <= 0){
                    attackRate = 1; 
                }
            }

            if (level == 1)
            {
                changeWeapon("pierce");
                //power = (int)Math.round(power*0.1f);
            }
            else if (level == 2)
            {
                changeWeapon("artillery");
                attackRate= 100;
            }

            cost = upgradeCost[level];
            currentCost += upgradeCost[level-1];
            level++;
            this.setImage (fileName + (level) + ".png");
            counter = attackRate;
            if (!button){
                map.displayMessage("Tower Upgraded", 2); 
            }
        }

        if (!button){
            map.resetUi(); //refreshes the ui after upgrade
        }
    }

    /**
     * passes the maximum attack range of the tower
     */
    public int getRange()
    {
        return range; 
    }

    /**
     * passes the weapon the tower is currently using
     */
    public String getWeaponType()
    {
        return type; 
    }

    //added by james
    /**
     * return the name of the tower
     */
    public String getName(){
        return name;
    }

    /**
     * returns the description of the tower
     */
    public ArrayList<String> getDesc(){
        return desc;
    }

    /**
     * returns the damage of the tower
     */
    public int getDmg(){
        return power;
    }

    /**
     * returns the attack speed of the tower
     */
    public int getAttackSpeed(){
        return attackRate;
    }

    /**
     * returns the current cooldown of the weapon
     */
    public int getCD(){
        return counter;
    }

    /**
     * return the cost to upgrade the tower
     */
    public int getUpgradeCost(){
        return upgradeCost[level-1];
    }

    /**
     * returns true if the tower can be upgraded further
     */
    public boolean upgradeable(){
        if (level < 3){
            return true;
        }
        return false;
    }

    /**
     * returns the current level of the tower
     */
    public int getLevel(){
        return level;
    }

    /**
     * returns the element of the tower
     */
    public int getElement(){
        return element;
    }

    /**
     * adds a debuff on the tower
     * called when the user buys a debuff
     */
    public void addDebuff(int id, int level){
        Debuff d = new Debuff (id, level);
        debuffs.add (d);
    }

    /**
     * returns the number of debuffs this tower currently has
     */
    public int[] getDebuffs(){
        int[] temp = new int[debuffs.size()];
        for (int i = 0; i < debuffs.size(); i++){
            temp[i] = debuffs.get(i).getId();
        }
        return temp;
    }

    /**
     * returns the cost of the tower
     */
    public int getCost(){
        return cost;
    }

    /**
     * returns the current level of the tower
     */
    public int getCurrentLevel()
    {
        return level;
    }

    /**
     * Returns how much money the user spent on this tower <br>
     * Used when the user sells the tower
     */
    public int getCurrentCost(){
        return currentCost;
    }
}