import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button for the arrows in the tutorial screen <br>
 * The user clicks on this to move the pictures
 * 
 * @author James Lu
 * @version 1.0
 */
public class ArrowButton extends SSButtons
{
    private boolean next;

    public ArrowButton(boolean next){
        if (next){          //if this is the left arrow
            bg[0] = new GreenfootImage ("Buttons/next1.png");
            bg[1] = new GreenfootImage ("Buttons/next2.png");
            bg[2] = new GreenfootImage ("Buttons/next3.png");
        }else{
            bg[0] = new GreenfootImage ("Buttons/prev1.png");
            bg[1] = new GreenfootImage ("Buttons/prev2.png");
            bg[2] = new GreenfootImage ("Buttons/prev3.png");
        }
        this.next = next;
        this.setImage (bg[0]);
    }

    public boolean isNext(){
        return next;
    }
}