import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * The screen played before the game starts
 * 
 * @author James Lu
 * @version 0.01
 */
public class StartScreen extends Actor
{
    private Map world;
    private GreenfootImage bg;
    private Color bgColor;

    private GreenfootImage startButton;
    private Font startButtonFont;
    public StartScreen(boolean restart){        
        bg              = new GreenfootImage (1024, 768);
        bgColor         = new Color (0,50,120);

        startButton     = new GreenfootImage (250, 50);
        startButtonFont = new Font ("Verdana", 0, 35);

        refresh(restart);        
    }

    private void refresh(boolean restart){
        bg.clear();
        if (!restart){
            bg.setColor (bgColor);
            bg.fill();

            startButton.setColor    (Color.WHITE);
            startButton.fill();
            startButton.setColor    (Color.BLACK);
            startButton.setFont     (startButtonFont);
            startButton.drawString  ("Press-Space", 20, 40);

            bg.drawImage (startButton, 412, 500);
        }
        else{
            bg.setColor (Color.BLACK);
            bg.fill();

            bg.setFont      (new Font ("Times New Roman", 1, 70));
            bg.setColor     (Color.WHITE);
            bg.drawString   ("GAME OVER", 300, 200);

            bg.setFont      (startButtonFont);
            bg.setColor     (Color.WHITE);
            bg.drawString   ("Press A to restart"  , 350, 400);
            bg.drawString   ("or press esc to exit", 340, 500);
        }
        this.setImage (bg);
    }
}
