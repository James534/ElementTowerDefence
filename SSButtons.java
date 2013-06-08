import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The parent class for the start screen buttons <br>
 * These are the buttons that only appear during the start screen
 * 
 * @author James Lu
 * @version 1.0
 */
public class SSButtons extends Button
{
    private int counter;

    public SSButtons (){
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
                this.setImage (bg[2]);
            }
        }
        else if (selected){
            selected = false;
            this.setImage (bg[1]);
        }
        else{
            this.setImage (bg[0]);
        }
    }
}