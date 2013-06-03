import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UIButton here.
 * 
 * @author (James Luu) 
 * @version (a version number or a date)
 */
public class UIButton extends Button
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
        }
        else{
            this.setImage (bg[0]);
        }
    }
}