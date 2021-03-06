import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The buttons that you click to build towers <br>
 * Each button builds a new Tower
 * 
 * @author (James Lu) 
 * @version (1.0)
 */
public class BuildTowers extends Button implements HoverInfo
{
    private int id;
    private Tower tower;
    private int cost;

    private int counter;
    private int hoverCounter;
    public BuildTowers (int id){
        this.id = id;
        String element = "";

        int ID = (id-1) / 3;
        if (ID == 0){                   //air
            tower = new AirTower();
            if (id == 1){
            }else if (id == 2){
                tower.upgrade(true);
            }else if (id == 3){
                tower.upgrade(true);
                tower.upgrade(true);
            }
            element = "a";
        }else if (ID == 1){             //water
            tower = new WaterTower();
            if (id == 4){          
            }else if (id == 5){          
                tower.upgrade(true);
            }else if (id == 6){         
                tower.upgrade(true);
                tower.upgrade(true);
            }
            id = (id-1)%3 + 1;
            element = "w";
        }else if (ID == 2){             //Fire
            tower = new FireTower();
            if (id == 7){          
            }else if (id == 8){          
                tower.upgrade(true);
            }else if (id == 9){          
                tower.upgrade(true);
                tower.upgrade(true);
            }
            id = (id-1)%3 + 1;
            element = "f";
        }else if (ID == 3){             //Earth
            tower = new EarthTower();
            if (id == 10){          
            }else if (id == 11){         
                tower.upgrade(true);
            }else if (id == 12){       
                tower.upgrade(true);
                tower.upgrade(true);
            }
            id = (id-1)%3 + 1;
            element = "e";
        }
        counter = 0;
        hoverCounter = 0;
        bg[0] = new GreenfootImage ("Buttons/BuildTowers/"+element + "" +id+"1.png");
        bg[1] = new GreenfootImage ("Buttons/BuildTowers/"+element + "" +id+"2.png");
        bg[2] = new GreenfootImage ("Buttons/BuildTowers/"+element + "" +id+"3.png");
        cost = tower.getCost();
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

    /**
     * Returns the tower this button builds
     */
    public Tower getTower(){
        int ID = (id-1) / 3;
        int level = (id-1) % 3 + 1;
        Tower t;
        if (ID == 0){           //air
            t = new AirTower();
        }else if (ID == 1){     //water
            t = new WaterTower();
        }else if (ID == 2){     //fire
            t = new FireTower();
        }else{                  //earth
            t = new EarthTower();
        }        
        for (int i = 1; i < level; i++){
            t.upgrade (true);
        }
        return t;
    }

    /**
     * Returns the cost of building the tower
     */
    public int getCost(){
        return cost;
    }

    /** interface methods*/
    /**
     * Increases the hover counter when the mouse hovers over this object
     */
    public void hoverOver(){
        hoverCounter++;
    }

    /**
     * Changes the image to be selected or not Pass true if the button is selected
     */
    public void changeImg(boolean s){
        selected = s;
    }

    /**
     * Resets the hover counter when the mouse is no longer hovering over it
     */
    public void resetCounter(){
        hoverCounter = 0;
    }
}