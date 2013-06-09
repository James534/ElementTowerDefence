import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The button that starts the wave
 * 
 * @author James Lu
 * @version 1.0
 */
public class StartWave extends Button
{
    private int counter;
    public StartWave(){
        bg[0] = new GreenfootImage ("Buttons/sw1.png");
        bg[1] = new GreenfootImage ("Buttons/sw2.png");
        bg[2] = new GreenfootImage ("Buttons/sw3.png");
        this.setImage (bg[0]);
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
