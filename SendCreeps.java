import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SendCreeps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SendCreeps extends Button
{
    private int id;
    private int counter;
    private Creep creep;
    private int cost;
    public SendCreeps(int id){
        this.id = id;
        if (id == 1){
            bg[0] = Data.creepButtonImg[0];
            bg[1] = Data.creepButtonImg[1];
            bg[2] = Data.creepButtonImg[2];
            cost  = 10;
            creep = new FireCreep1();
        }
        this.setImage (bg[0]);
        counter = 0;
    }

    public void act(){
        if (clicked){
            if (counter >= 10){
                counter = 0;
                clicked = false;
            }
            else{
                counter++;
                hoverCounter = 0;
                this.setImage (bg[2]);
            }
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

    public int getCost(){
        return cost;
    }

    public int getIncome(){
        return creep.getMaxHp()/10;
    }

    public Creep getCreep(){
        return new FireCreep1();
    }
}