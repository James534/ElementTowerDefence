import greenfoot.*;
/**
 * The visual indicator for debuffs on the mobs
 * 
 * @author James Lu
 * @version 1.0
 */
public class DebuffVisu extends Effects  
{
    private int frame;              //current frame of the picture
    private int maxFrame;           //max frame of the pictures

    private Enemy e;
    public DebuffVisu(Enemy e){
        counter = 0;
        this.e = e;
        bg = new GreenfootImage (20, 20);
        this.setImage (bg);
    }

    /**
     * Sets a debuff on this visualizer <br>
     * 0 is stun <br>
     * 1 is lightning <br>
     * 2 is freeze <br>
     * 3 is burn <br>
     * 4 is stone
     */
    public void setDebuff (int id){
        if (id == 0){           //stun
            cache = Data.stun;
            maxFrame = 6;
        }else if (id == 1){     //lightning
            cache = Data.air;
            maxFrame = 2;
        }else if (id == 2){     //freeze
            cache = Data.freeze;
            maxFrame = 2;
        }else if (id == 3){     //burn
            cache = Data.burn;
            maxFrame = 2;
        }else if (id == 4){
            cache = Data.stone;
            maxFrame = 2;
        }
        counter = 0;
        frame = 1;
        show = true;
    }

    public void act(){
        if (show){
            if (counter >= 2){
                counter = 0;
                frame++;
                if (frame > maxFrame){
                    frame = 1;
                }
                this.setImage (cache[frame-1]);
            }
            else{
                counter++;
            }
        }else{
            this.setImage (bg);
        }
    }

    /**
     * Sets the location of the debuff <br>
     * The paramaters passed are the x and y values of the enemy
     */
    public void changeLocation (int x, int y){
        this.setLocation (x, y-10);
    }

    /**
     * Returns the enemy that this debuff belongs to
     */
    public Enemy getEnemy(){
        return e;
    }
}
