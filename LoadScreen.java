import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * The loading screen
 * 
 * @author James Lu
 * @version 1.0
 */
public class LoadScreen extends Actor
{
    private GreenfootImage bg;

    private Color loading;

    private int counter;
    private int max;
    public LoadScreen(int max){
        bg = new GreenfootImage (1024, 768);

        loading = new Color (50, 255, 0);

        counter = 0;
        this.max = max;

        refresh();
    }

    public void update(){
        counter++;
        refresh();
    }

    private void refresh(){
        bg.clear();
        bg.setColor (Color.BLACK);
        bg.fill();

        bg.setColor (loading);

        int length = Math.round ( (float)counter / max * 1000);
        bg.fillRect (100, 400, length, 30);
        this.setImage (bg);
    }
}
