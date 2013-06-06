import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Visulizer for the maximum attack range of the tower. <br>
 * called when user places a tower, or selectes a tower already in game
 * 
 * @author (Terence Lai ) 
 */
public class Range extends Actor
{
    private Color backgroundColor = new Color( 255,20,20);
    private GreenfootImage attackRange ;
    private GreenfootImage bg;

    private int rad;
    /**
     * draws the maximum range of the tower
     */
    public Range(int radius){
        attackRange  = new GreenfootImage(radius*2,radius*2);
        bg           = new GreenfootImage (radius, radius);

        rad = radius;
        refresh();
    }

    private void refresh(){
        bg.clear();
        attackRange.setTransparency(50) ;
        attackRange.setColor(backgroundColor);  
        attackRange.fillOval(0, 0, rad, rad);

        bg.drawImage (attackRange, 0, 0);
        this.setImage(bg);   
    }
}