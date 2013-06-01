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

    public void setDebuff (int id){
        if (id == 0){           //stun
            cache = Data.stun;
            counter = 0;
            frame = 1;
            maxFrame = 6;
        }else if (id == 1){     //lightning

        }else if (id == 2){     //freeze
            cache = Data.freeze;
            counter = 0;
            frame = 1;
            maxFrame = 2;
        }
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

    public void changeLocation (int x, int y){
        this.setLocation (x, y-10);
    }

    public Enemy getEnemy(){
        return e;
    }
}
