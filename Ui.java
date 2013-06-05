import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
/**
 * The User Interface; the screen that shows up on the bottom
 * <br>
 * It changes every time the user selects something
 * <br>
 * It creates buttons when the user selects specific objects, and removes them when they are not needed <br>
 * 
 * @author James Lu, Terence Lai
 * @version (1.0)
 */
public class Ui extends Actor
{
    private Map map;

    private GreenfootImage bg;
    private GreenfootImage[] cache;
    private GreenfootImage[] elements;

    private Font generalFont;
    private Font name;
    private Font descFont;
    private Font statFont;

    private Color goldColor;
    private Color lifeColor;
    private Color nameColor;
    private Color descColor;
    private Color statColor;
    private Color debuffColor;
    private int id;                 //which picture to display

    private int gold;
    private int income;
    private int lives;
    private String wave;
    private String next;

    private Tower tower;
    private Enemy mob;
    private ArrayList<String> desc;

    private Button[][] buttons;
    private SellButton sellButton;
    private UpgradeButton upgradeButton;
    private DebuffButton[] debuffs;

    private DummyDebuff[] dumbDebuff;
    private Reload reload;
    private Element[] waveElement;
    public Ui(){
        bg = new GreenfootImage (1000, 230);
        cache = new GreenfootImage [6];
        cache[0] = new GreenfootImage ("UI/general.png");
        cache[1] = new GreenfootImage ("UI/buildTower.png");
        cache[2] = new GreenfootImage ("UI/sendMobs.png");
        cache[3] = new GreenfootImage ("UI/selectTower.png");
        cache[4] = new GreenfootImage ("UI/selectTower.png");
        cache[5] = new GreenfootImage ("UI/selectMob.png");

        elements = new GreenfootImage [4];
        elements[0] = new GreenfootImage ("UI/fire2.png");
        elements[1] = new GreenfootImage ("UI/water2.png");
        elements[2] = new GreenfootImage ("UI/air2.png");
        elements[3] = new GreenfootImage ("UI/earth2.png");

        generalFont = new Font ("Times New Roman", 1, 20);
        name        = new Font ("Vrinda"         , 1, 35);
        descFont    = new Font ("Vrinda"         , 0, 20);
        statFont    = new Font ("Vrinda"         , 1, 25);

        lifeColor   = new Color (225, 0  , 0);
        goldColor   = new Color (225, 175, 55);
        nameColor   = new Color (20 , 210, 245);
        descColor   = new Color (255, 255, 255);
        statColor   = new Color (45 , 200, 45 );
        debuffColor = new Color (255, 50, 0);

        sellButton    = new SellButton();
        upgradeButton = new UpgradeButton();
        buttons = new Button[2][12];
        for (int i = 0; i < 12; i++){
            buttons[0][i] = new SendCreeps(i+1);
        }
        for (int i = 0; i < 12; i++){
            buttons[1][i] = new BuildTowers(i+1);
        }
        debuffs = new DebuffButton [5];
        for (int i = 0; i < 5; i++){
            debuffs[i] = new DebuffButton(i);
        }
        dumbDebuff = new DummyDebuff[5];
        for (int i = 0; i < 5; i++){
            dumbDebuff[i] = new DummyDebuff(i);
        }
        reload = new Reload();
        waveElement = new Element[3];
        waveElement[0] = new Element();     //current wave
        waveElement[1] = new Element();     //next wave
        waveElement[2] = new Element();     //mob element

        id = 0;
        lives   = 20;
        gold    = 100;
        wave    = "Air";
        next    = "Water";

        refresh();
    }

    protected void addedToWorld(World world){
        map = (Map)world;
    }

    public void act(){
        if (id == 3 || id == 5){           
            //if its describing a mob, always refresh to change hp
            //if its selecting a tower, refresh to show cooldown
            refresh();
        }
    }

    /**
     * changes the ui to fit the user's selection <br>
     * 0 for general ui                           <br>
     * 1 for building a tower                     <br> 
     * 2 for sending creeps                       <br>
     * 3 for selected a tower                     <br>
     * 4 for selecting a tower during the buy     <br>
     * 5 for selecting a mob
     */
    public void changeUi (int id){
        if       (this.id == 1 && id != 1){
            map.removeObjects (map.getObjects (BuildTowers.class) );
        }else if (this.id == 2 && id != 2){
            map.removeObjects (map.getObjects (SendCreeps.class) );
        }else if (this.id == 3 && id != 3){
            map.removeObjects (map.getObjects (UIButton.class) );
            map.removeObjects (map.getObjects (Reload.class) );
        }else if (this.id == 5 && id != 5){
            map.removeObjects (map.getObjects (DummyImage.class) );
            map.removeObject (waveElement[2]);
        }
        this.id = id;
        if (id == 1){               //build towers
            int tempCounter = 0;
            for (int n = 0; n < 4; n++){
                for (int i = 0; i < 3; i++){
                    map.addObject (buttons[1][tempCounter], 350 + 189*n, 610+50*i);
                    tempCounter++;
                }
            }
        }
        else if (id == 2){           //send creeps
            int tempCounter = 0;
            for (int n = 0; n < 4; n++){
                for (int i = 0; i < 3; i++){
                    map.addObject (buttons[0][tempCounter], 340 + 190*n, 610+50*i);
                    tempCounter++;
                }
            }
        }
        else if (id == 3){       //adds buttons when its selecting a tower
            sellButton      .clicked (false);
            upgradeButton   .clicked (false);
            map.addObject (sellButton   , 850, 675);
            map.addObject (upgradeButton, 850, 730);

            map.addObject (debuffs[0]   , 800, 620);
            map.addObject (debuffs[tower.getElement()], 860, 620);

            map.addObject (reload       , 525, 720);
            int[] debuffsBought = tower.getDebuffs();
            for (int n = 0; n < debuffs.length; n++){
                debuffs[n].bought (false);
            }
            for (int i = 0; i < debuffsBought.length; i++){
                debuffs[debuffsBought[i]].bought (true);
            }
        }
        else if (id == 5){      //selecting a mob
            waveElement[2].setId (mob.getType());
            map.addObject (waveElement[2], 525, 625);
        }
        refresh();
    }

    /**
     * Sets the general data of the ui: <br>
     * The number of lives <br>
     * The current gold <br>
     * and the income
     */
    public void setGeneralData(int lives, int gold, int income){
        this.lives = lives;
        this.gold  = gold;
        this.income= income;
        refresh();
    }

    /**
     * Sets the wave data of the current and next wave <br>
     * The paramaters are int ids of the current and next wave
     */
    public void setWaveData(int current, int Next){
        waveElement[0].setId (current);
        waveElement[1].setId (Next);

        map.addObject (waveElement[0], 150, 650);
        map.addObject (waveElement[1], 150, 700);

        refresh();
    }

    /**
     * Sets the tower data for the Ui <br>
     * The Ui calls upon the passed tower to display it's stats
     */
    public void setTowerData (Tower t){
        tower = t;
        desc = t.getDesc();
    }

    /**
     * Sets the enemy data for the Ui <br>
     * The Ui calls upon the passed enemy to display it's stats
     */
    public void setMobData(Enemy e){
        mob = e;
    }

    /**
     * Returns the current mob that the Ui is displaying
     */
    public Enemy getMob(){
        return mob;
    }

    private void refresh(){ 
        if (bg != null){
            bg.clear();
        }
        bg.drawImage (cache[id], 0, 0);

        int tempX;          //to calculate the middle of the row to draw the strings
        String tempString;  //temp strings to hold the casted int values to calculate tempX

        /**-----------------------------------general data---------------------------------**/
        bg.setFont (generalFont);
        //lives
        tempString = Integer.toString (lives);
        //calculating the x coordinates to center the lives     
        tempX = 140 - tempString.length() * 5;                     
        bg.setColor (lifeColor);
        bg.drawString (tempString, tempX, 40);

        //money
        tempString = Integer.toString (gold);
        //calculating the x coordinates to center the gold
        tempX = 110 - tempString.length() * 5;
        bg.setColor (goldColor);
        bg.drawString (tempString, tempX, 75);

        //income
        tempString = "+ " + Integer.toString (income);
        tempX = 190 - tempString.length() * 5;
        bg.drawString (tempString, tempX, 75);

        if        (id == 0){

        } else if (id == 1){                    //building towers
            /*bg.drawImage (elements[0], 320, 30);
            bg.drawImage (elements[1], 510, 30);
            bg.drawImage (elements[2], 700, 30);
            bg.drawImage (elements[3], 890, 30);*/
        } else if (id == 2){                    //sending creeps
        } else if (id == 3 || id == 4){         //tower selection
            //name of the tower
            bg.setColor (nameColor);
            bg.setFont  (name);
            tempString = tower.getName();
            bg.drawString (tempString, 250, 60);

            //tower description
            bg.setColor (descColor);
            bg.setFont  (descFont);
            //description
            for (int i = 0; i < desc.size(); i++){
                tempString = desc.get(i);
                bg.drawString (tempString, 490, 85 + i * 20);
            }
            //level of the tower
            tempX = tower.getLevel();
            tempString = "Level " + Integer.toString(tempX);
            bg.drawString (tempString, 260, 100);      

            //cooldown of weapon
            reload.setId (tempX-1);
            reload.setRate (tower.getAttackSpeed());
            reload.setCD (tower.getCD());

            //stats
            bg.setColor (statColor);
            bg.setFont  (statFont);
            tempString = Integer.toString(tower.getDmg() );      //damage
            bg.drawString (tempString, 375, 152);
            tempString = Integer.toString(tower.getRange() );    //range
            bg.drawString (tempString, 375, 200);
            tempString = Integer.toString(tower.getAttackSpeed());  //attack speed
            bg.drawString (tempString, 550, 152);
        } else if (id == 5){                    //mob selection
            //name of the mob
            bg.setColor (nameColor);
            bg.setFont  (name);
            tempString = mob.getName();
            tempX = 320 - tempString.length() * 6;
            bg.drawString (tempString, tempX, 60);

            //stats
            //hp
            bg.setColor (statColor);
            bg.setFont  (statFont);
            tempString = Integer.toString (mob.getHp()) + "/" + Integer.toString (mob.getMaxHp());
            bg.drawString (tempString, 350, 152);

            //armor
            tempString = Integer.toString (mob.getArmor());
            bg.drawString (tempString, 350, 200);

            //speed
            tempString = Float.toString (mob.getSpeed());
            bg.drawString (tempString, 545, 200);

            //debuffs
            ArrayList<Debuff> debuffs = mob.getDebuffs();
            for (int i = 0; i < debuffs.size(); i++){
                Debuff d = debuffs.get(i);
                DummyDebuff dd = dumbDebuff [d.getId()];
                dd.setDuration (d.getDuration());
                if (d.getDuration() > 1){
                    map.addObject (dd, 750 + 50*i, 620);
                }
                else{
                    map.removeObject (dd);
                }
            }

            //negative stuff
            bg.setColor (debuffColor);

            //dot
            if (mob.getDOT() != 0.0f){
                tempString = "-" + Float.toString (mob.getDOT());
                bg.drawString (tempString, 420, 152);
            }
            //negative armor
            if (mob.getRedu() != 0.0f){
                tempString = "-" + Float.toString (mob.getRedu());
                bg.drawString (tempString, 380, 200);
            }
            //negative speed
            if (mob.getSlow() != 0.0f){
                tempString = "-" + Float.toString (mob.getSlow());
                bg.drawString (tempString, 600, 200);
            }
        }
        this.setImage (bg);
    }

    /**
     * returns the stage of this Ui <br>
     * 
     * 0 for general ui                           <br>
     * 1 for building a tower                     <br> 
     * 2 for sending creeps                       <br>
     * 3 for selected a tower                     <br>
     * 4 for selecting a tower during the buy     <br>
     * 5 for selecting a mob
     * 
     */
    public int getId(){
        return id;
    }

    /**
     * returns a certain button <br>
     * n = 0 is for the buttons to send creeps <br>
     * n = 1 is for the buttons to build towers <br>
     */
    public Button getButton(int n, int id){
        return buttons[n][id];
    }
}
