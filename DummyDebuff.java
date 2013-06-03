import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Dummy debuffs, the debuffs that show up on enimies when the user clicks on them
 * 
 * @author James Lu
 * @version 1.0
 */
public class DummyDebuff extends DummyImage
{
    private int id;
    private int level;
    private int duration;
    private int maxDuration;
    public DummyDebuff(int id){
        this.id = id;
        if (id == 0){           //stun
            bg = new GreenfootImage ("Debuff/stunned.png");
        }
        else if (id == 1){
            bg = new GreenfootImage ("Debuff/lightning.png");
        }
        else if (id == 2){            
            bg = new GreenfootImage ("Debuff/freeze.png");
        }
        else if (id == 3){
            bg = new GreenfootImage ("Debuff/burning.png");
        }
        else if (id == 4){
            bg = new GreenfootImage ("Debuff/stone.png");
        }
        this.setImage (bg);
        maxDuration = Data.debuffDuration [0][id];
        duration = 0;
    }

    public void act(){
        if (selected){
            selected = false;
            if (hoverCounter >= 50){
                map.hm.setData (this);
                int[] co = setCo();
                map.addObject (map.hm, co[0], co[1]);
            }
        }
        updateImg();
        this.setImage (bg);        
    }

    private void updateImg(){
        bg.setTransparency (Math.round ( (float)duration / maxDuration * 255));
    }

    public void setDuration (int d){
        duration = d;
    }

    public void setLevel (int lv){
        level = lv;
        maxDuration = Data.debuffDuration [lv][id];
    }

    public int getDuration(){
        return duration;
    }

    public int getId(){
        return id;
    }
}
