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
    public Debuff (int id){
        if (id == 0){                   //stun
            bg[0] = new GreenfootImage ("ui/stunned.png");
            bg[1] = new GreenfootImage ("ui/stunned.png");
            bg[2] = new GreenfootImage ("ui/stunned.png");
        }else if (id == 1){             //lightning
            bg[0] = new GreenfootImage ("ui/lightning.png");
            bg[1] = new GreenfootImage ("ui/lightning.png");
            bg[2] = new GreenfootImage ("ui/lightning.png");
        }
        else{
            bg[0] = new GreenfootImage ("ui/lightning.png");
            bg[1] = new GreenfootImage ("ui/lightning.png");
            bg[2] = new GreenfootImage ("ui/lightning.png");
        }
        bg[0].setTransparency (100);
        bg[1].setTransparency (200);
    }

    public void act(){
        if (clicked){
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
}
