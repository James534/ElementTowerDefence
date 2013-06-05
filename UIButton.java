import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class to simplify the removal of specific buttons <br>
 * Easier to call remove (UIButton.class) instead of <br>
 * Remove (___.class), remove (___.class), remove (___.class), etc
 * 
 * @author (James Lu) 
 * @version (1.0)
 */
public class UIButton extends Button implements HoverInfo
{
    private int counter;
    private int hoverCounter;

    public UIButton (){
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

    /** interface methods */
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