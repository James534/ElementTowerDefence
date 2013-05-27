import greenfoot.*;
/**
 * The visual indicator for debuffs on the mobs
 * 
 * @author James Lu
 * @version 1.0
 */
public class DebuffVisu extends Actor  
{
    private GreenfootImage bg;
    private GreenfootImage[] cache;

    private int counter;            //increases every act
    private int frame;              //current frame of the picture
    private int maxFrame;           //max frame of the pictures

    private boolean show;           //whether to show this or not
    public DebuffVisu(){
        counter = 0;
        bg = new GreenfootImage (20, 20);
        this.setImage (bg);
    }

    public void setDebuff (int id){
        if (id == 0){           //stun
            cache = Data.stun;
            counter = 0;
            frame = 1;
            maxFrame = 6;
        }
        show = true;
    }

    public void ifShow(boolean b){
        show = b;
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
        }
    }

    public void changeLocation (int x, int y){
        this.setLocation (x, y-20);
    }
}
