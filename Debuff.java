import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button to buy debuffs on towers
 * 
 * also shows the debuffs on the mobs
 * 
 * @author James Lu
 * @version 0.1
 */
public class Debuff extends UIButton
{
    private boolean bought;
    private int id;
    private String name;
    public Debuff (int id){
        if (id == 0){                   //stun
            bg[0] = new GreenfootImage ("ui/stunned.png");
            bg[1] = new GreenfootImage ("ui/stunned.png");
            bg[2] = new GreenfootImage ("ui/stunned.png");
            name = "Stun";
        }else if (id == 1){             //lightning
            bg[0] = new GreenfootImage ("ui/lightning.png");
            bg[1] = new GreenfootImage ("ui/lightning.png");
            bg[2] = new GreenfootImage ("ui/lightning.png");
            name = "Lightning";
        }else if (id == 2){             //water
            bg[0] = new GreenfootImage ("ui/iceDebuff.png");
            bg[1] = new GreenfootImage ("ui/iceDebuff.png");
            bg[2] = new GreenfootImage ("ui/iceDebuff.png");
            name = "Freeze";
        }else if (id == 3){             //fire
            bg[0] = new GreenfootImage ("ui/burning.png");
            bg[1] = new GreenfootImage ("ui/burning.png");
            bg[2] = new GreenfootImage ("ui/burning.png");      
            name = "Burn";      
        }else{                          //earth
            bg[0] = new GreenfootImage ("ui/stone.png");
            bg[1] = new GreenfootImage ("ui/stone.png");
            bg[2] = new GreenfootImage ("ui/stone.png");
            name = "Earth";
        }
        bg[0].setTransparency (100);
        bg[1].setTransparency (200);
        bought = false;
        this.id = id;
    }

    public void act(){
        if (bought){
            hoverCounter = 0;
            this.setImage (bg[2]);
        }
        else if (selected){
            selected = false;
            this.setImage (bg[1]);
            if (hoverCounter >= 50){
                map.hm.setData (this);
                int[] co = setCo();
                map.addObject (map.hm, co[0], co[1]);
            }
        }
        else{
            this.setImage (bg[0]);
        }
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
     * returns the name of the debuff
     */
    public String getName(){
        return name;
    }
}
