import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * The class for the explosions, when the artilary fires
 * 
 * @author James Lu
 * @version 1.0
 */
public class Explosion extends Effects
{
    private int trans;          //transparancy
    public Explosion(int aoe){
        bg = new GreenfootImage (aoe, aoe);

        bg.setColor (Color.RED);
        bg.fillOval(0, 0, aoe, aoe);

        trans = 255;
        show = true;
        this.setImage (blank);
    }

    public void act(){
        if (show){
            if (counter >= 2){
                counter = 0;
                trans -= 30;
                if (trans >= 0){
                    bg.setTransparency (trans);
                }
                else{
                    m.removeObject (this);
                }
            }
            else{
                counter++;
            }
            this.setImage (bg);
        }else{
            this.setImage (blank);
        }
    }
}