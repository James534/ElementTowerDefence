import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SendCreeps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SendCreeps extends Button implements HoverInfo
{
    private int id;
    private Creep creep;
    private int cost;

    private int counter;
    private int hoverCounter;
    public SendCreeps(int id){
        this.id = id;
        if (id == 1){
            cost = 10;
        }else if (id == 2){
            cost = 50;
        }else if (id == 3){
            cost = 100;
        }
        try{        //temp, remove after i enable all the other buttons
            bg[0] = new GreenfootImage ("Buttons/sendcreeps/fc"+id+"1.png");
            bg[1] = new GreenfootImage ("Buttons/sendcreeps/fc"+id+"2.png");
            bg[2] = new GreenfootImage ("Buttons/sendcreeps/fc"+id+"3.png");
            creep = new FireCreep(id);
            this.setImage (bg[0]);
            counter = 0;
        }catch (Exception e){}
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
        return new FireCreep(id);
    }

    /** interface methods*/
    public void hoverOver(){
        hoverCounter++;
    }

    public void changeImg(boolean s){
        selected = s;
    }

    public void resetCounter(){
        hoverCounter = 0;
    }

}