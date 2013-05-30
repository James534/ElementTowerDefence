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
        String element = "";
        int ID = (id-1) / 3;
        if (ID == 0){                   //air
            if (id == 1){
                cost = 10;
            }else if (id == 2){
                cost = 50;
            }else if (id == 3){
                cost = 100;
            }
            element = "a";
            creep = new AirCreep(id);
        }else if (ID == 1){             //water
            if (id == 4){          
                cost = 10;
            }else if (id == 5){          
                cost = 50;
            }else if (id == 6){          
                cost = 100;
            }
            id = (id-1)%3 + 1;
            element = "w";
            creep = new WaterCreep(id);
        }else if (ID == 2){             //Fire
            if (id == 7){          
                cost = 10;
            }else if (id == 8){          
                cost = 50;
            }else if (id == 9){          
                cost = 100;
            }
            id = (id-1)%3 + 1;
            element = "f";
            creep = new FireCreep(id);
        }else if (ID == 3){             //Earth
            if (id == 10){          
                cost = 10;
            }else if (id == 11){          
                cost = 50;
            }else if (id == 12){          
                cost = 100;
            }
            id = (id-1)%3 + 1;
            element = "e";
            creep = new EarthCreep(id);
        }
        counter = 0;
        bg[0] = new GreenfootImage ("Buttons/SendCreeps/"+element + "" +id+"1.png");
        bg[1] = new GreenfootImage ("Buttons/SendCreeps/"+element + "" +id+"2.png");
        bg[2] = new GreenfootImage ("Buttons/SendCreeps/"+element + "" +id+"3.png");
        this.setImage (bg[0]);
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
        int ID = (id-1) / 3;
        int creepId = (id-1) % 3 + 1;
        if (ID == 0){           //air
            return new AirCreep (creepId);
        }else if (ID == 1){     //water
            return new WaterCreep (creepId);
        }else if (ID == 2){     //fire
            return new FireCreep (creepId);
        }else{                  //earth
            return new EarthCreep (creepId);
        }
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