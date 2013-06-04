import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button to buy debuffs on towers
 * 
 * also shows the debuffs on the mobs
 * 
 * @author James Lu
 * @version 0.1
 */
public class DebuffButton extends UIButton
{
    private boolean bought;
    private boolean dummy;

    private int id;
    private int level;
    private int cost;
    private String name;
    
    public DebuffButton (int id){
        if (id == 0){                   //stun
            bg[0] = new GreenfootImage ("Debuff/stunned.png");
            bg[1] = new GreenfootImage ("Debuff/stunned.png");
            bg[2] = new GreenfootImage ("Debuff/stunned.png");
            name = "Stun";
        }else if (id == 1){             //lightning
            bg[0] = new GreenfootImage ("Debuff/lightning.png");
            bg[1] = new GreenfootImage ("Debuff/lightning.png");
            bg[2] = new GreenfootImage ("Debuff/lightning.png");
            name = "Lightning";
        }else if (id == 2){             //water
            bg[0] = new GreenfootImage ("Debuff/freeze.png");
            bg[1] = new GreenfootImage ("Debuff/freeze.png");
            bg[2] = new GreenfootImage ("Debuff/freeze.png");
            name = "Freeze";
        }else if (id == 3){             //fire
            bg[0] = new GreenfootImage ("Debuff/burning.png");
            bg[1] = new GreenfootImage ("Debuff/burning.png");
            bg[2] = new GreenfootImage ("Debuff/burning.png");      
            name = "Burn";      
        }else{                          //earth
            bg[0] = new GreenfootImage ("Debuff/stone.png");
            bg[1] = new GreenfootImage ("Debuff/stone.png");
            bg[2] = new GreenfootImage ("Debuff/stone.png");
            name = "Earth";
        }
        bg[0].setTransparency (100);
        bg[1].setTransparency (200);
        bought = false;
        this.id = id;
        level = 1;
        cost = 50;
    }

    /**
     * changes the button to see if its bought
     */
    public void bought (boolean t){
        bought = t;
    }

    /**
     * returns true if the debuff is already bought
     */
    public boolean isBought(){
        return bought;
    }

    /**
     * returns the id of the debuff
     */
    public int getId (){
        return id;
    }

    /**
     * returns the level of this debuff
     */
    public int getLevel(){
        return level;
    }

    /**
     * returns the name of the debuff
     */
    public String getName(){
        return name;
    }

    /**
     * returns if this button is a dummy button or not
     */
    public boolean isDummy(){
        return dummy;
    }

    /**
     * returns the cost of the button
     */
    public int getCost(){
        return cost;
    }
}
