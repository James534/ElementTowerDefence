import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * The health bar for the enemy
 * 
 * @author James Lu
 * @version 0.01
 */
public class HealthBar extends Actor
{
    private GreenfootImage bg;
    private int maxHp;
    private int currentHp;
    private int length, width;
    public HealthBar(int maxhp, int currenthp){
        this.maxHp      = maxhp;
        this.currentHp  = currenthp;
        
        length          = 20;
        width           = 6;
        
        bg = new GreenfootImage(length+2, width+2);
        refresh();
    }

    public HealthBar(int maxhp, int currenthp, int length, int width){
        this (maxhp, currenthp);
        this.length = length;
    }

    /**
     * refreshes the picture after every change
     */
    private void refresh(){
        int scale = Math.round ( (float)currentHp/ (float)maxHp * length);
        bg.clear();

        if (currentHp != maxHp){
            bg.setColor (Color.RED);
            bg.fill();
        }

        bg.setColor (Color.GREEN);
        bg.fillRect (1,1, scale, width);
        //bg.fill();

        bg.setColor (Color.BLACK);
        bg.drawRect (0,0,length+1, width+1);
        this.setImage (bg);
    }

    /**
     * changes the hp of the health bar
     * called after the mob it belongs to takes damage
     */
    public void changeHp (int hp){
        currentHp = hp;
        refresh();
    }

    /**
     * changes the coordinates of the health bar
     * called after the mob it belongs to moves
     */
    public void changeLocation (int x, int y){
        setLocation (x, y-15);
    }

    /**
     * Changes the maxHp of the bar
     */
    public void changeMaxHp (int maxHp, int currentHp){
        this.maxHp     = maxHp;
        this.currentHp = currentHp;
        refresh();
    }
}
