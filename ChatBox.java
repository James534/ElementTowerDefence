import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * the chat box, or more commonly, the warning box that shows up
 *  when you do something illegal in the game
 * 
 * @author James Lu
 * @version 0.01
 */
public class ChatBox extends Actor
{
    private Map map;
    private GreenfootImage bg;

    private Font font;

    private Color color;
    private Color warningColor;
    private Color startColor;

    private String message;
    private int trans;
    private boolean show;
    public ChatBox(){
        map = (Map) getWorld();
        bg = new GreenfootImage (1000, 75);

        font         = new Font ("Verdana", 1, 35);

        warningColor = new Color (255, 50, 0);
        startColor   = new Color (0  , 0 , 0);
        color        = warningColor;

        message = "";
        trans = 500;
        show = false;
        refresh();
    }

    protected void addedToWorld (World world){
        if (map == null){
            map = (Map) world;
            map.removeObject (this);
        }
    }

    public void act(){
        if (map.getObjects (ChatBox.class) != null){
            if (show){
                if (bg.getTransparency() > 0){
                    trans -= 5;
                }
                else{
                    show = false;
                    trans = 0;
                    map.removeObject (this);
                }
                if (trans <= 255){
                    bg.setTransparency (trans);
                    refresh();}
            }
            //bg.fill();
            //this.setImage (bg);
        }    
    }

    /**
     * sets the message to whatever passes to it
     * id is which type of message is passed through
     *      1 is warning, red
     *      2 is wave starting, in black
     */
    public void setMessage(String m, int id){
        map.addObject (this, 512, 500);
        if       (id == 1){
            color = warningColor;
        } else if(id == 2){
            color = startColor;
        }
        message = m;
        trans = 500;
        show = true;
        bg.setTransparency (255);
        refresh();
    }

    private void refresh(){     
        bg.clear();
        bg.setFont (font);
        bg.setColor (color);

        int tempX = 500 - message.length() * 10;
        bg.drawString (message, tempX, 50);

        this.setImage (bg);
    }
}
