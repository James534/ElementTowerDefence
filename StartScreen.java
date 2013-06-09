import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.List;
/**
 * The screen played before the game starts
 * 
 * @author James Lu
 * @version 0.01
 */
public class StartScreen extends Actor
{
    private Map map;
    private Color bgColor;
    private Font startButtonFont;

    private GreenfootImage bg;
    private GreenfootImage[] tutPics;

    private StartButton startButton;
    private TutButton   tutButton;
    private ArrowButton nextButton;
    private ArrowButton prevButton;
    private BackButton  backButton;

    private boolean restart;        //whether this screen displays the normal or restart screen
    private boolean gameStart;      //if the user starts the game
    private boolean win;            //if the user won the game
    private int stage;              //which stage the screen is on
    private int tutPicNum;          //which tuorial pic the user is on
    public StartScreen(boolean restart){        
        bgColor         = new Color (0,50,120);
        startButtonFont = new Font ("Verdana", 0, 35);

        bg              = new GreenfootImage (1024, 768);
        tutPics         = new GreenfootImage[20];
        for (int i = 0; i < tutPics.length; i++){
            tutPics[i] = new GreenfootImage ("Tutorial/" + (i+1) + ".png");
        }

        startButton     = new StartButton();
        tutButton       = new TutButton();
        nextButton      = new ArrowButton(true);
        prevButton      = new ArrowButton(false);
        backButton      = new BackButton();

        gameStart    = false;   
        this.restart = restart;
        win = false;

        stage       = 0;
        tutPicNum   = 0;
    }

    protected void addedToWorld(World world){
        map = (Map) world;
        refresh(restart);
    }

    public void act(){
        if (!restart){                              //if its not restarting
            checkInput (stage);
            map.s.playMenu();
        }else{
            if (Greenfoot.isKeyDown ("r")){
                restart = false;
                if (win){
                    win = false;
                }
            }
        }
        refresh(restart);     
    }

    private void checkInput (int s){
        /** KEYBOARD INPUTS */
        String tempString = Greenfoot.getKey();
        if (tempString != null){            //if a key is pressed
            if (s == 0){                    //the main start screen
                if       (tempString.equals ("space")){
                    gameStart = true;
                    map.s.playClicked();
                }else if (tempString.equals ("escape")){
                    Greenfoot.stop();
                }else if (tempString.equals ("t")){
                    stage = 1;
                    startButton.clicked (true);
                    map.s.playClicked();
                    map.removeObjects (map.getObjects (SSButtons.class));
                }
            }
            else if (s == 1){               //the tutorial screen
                if       (tempString.equals ("left")){
                    prevButton.clicked (true);
                    map.s.playClicked();
                    tutPicNum--;
                    if (tutPicNum < 0){
                        tutPicNum = 0;}
                    Greenfoot.delay (5);
                }else if (tempString.equals ("right")){
                    nextButton.clicked (true);
                    map.s.playClicked();
                    tutPicNum++;
                    if (tutPicNum >= tutPics.length){
                        tutPicNum = tutPics.length-1;}
                    Greenfoot.delay(5);
                }else if (tempString.equals ("escape") || tempString.equals ("b")){
                    stage = 0;
                    map.s.playClicked();
                    map.removeObjects (map.getObjects (SSButtons.class));
                }
            }
        }

        /** MOUSE INPUTS */
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null){
            List<SSButtons> l = map.getObjectsAt (mouse.getX(), mouse.getY(), SSButtons.class);
            for (SSButtons b: l){           //hovering over buttons
                b.changeImg (true);         //changes image when mouse hover over this button
            }

            if (Greenfoot.mouseClicked (null)){
                Actor a = mouse.getActor();
                if (a instanceof SSButtons){        //if the user clicks a button
                    SSButtons b = (SSButtons) a;
                    b.clicked (true);               //change the button to clicked
                    map.s.playClicked();
                    if (b instanceof StartButton){  //if the button clicked is startbutton
                        gameStart = true;
                    }else if (b instanceof TutButton){
                        stage = 1;
                        map.removeObjects (map.getObjects (SSButtons.class));
                    }else if (b instanceof ArrowButton){
                        ArrowButton ab = (ArrowButton) b;
                        if (ab.isNext()){                   //if its a next button
                            tutPicNum++;
                            if (tutPicNum >= tutPics.length){
                                tutPicNum = tutPics.length-1;}
                        }
                        else{                               //if its a previous button
                            tutPicNum--;
                            if (tutPicNum < 0){
                                tutPicNum = 0;}
                        }
                    }else if (b instanceof BackButton){
                        stage = 0;
                        map.removeObjects (map.getObjects (SSButtons.class));
                    }
                }
            }
        }
    }

    private void refresh(boolean restart){
        bg.clear();
        if (win){            
            bg.setColor (Color.BLACK);
            bg.fill();

            bg.setFont      (new Font ("Times New Roman", 1, 70));
            bg.setColor     (Color.WHITE);
            bg.drawString   ("Congratulations", 300, 200);
            bg.drawString   ("You have beat the game!"  , 180, 300);

            bg.setFont      (startButtonFont);
            bg.setColor     (Color.WHITE);
            bg.drawString   ("Press r to restart"  , 350, 500);
            bg.drawString   ("or press esc to exit", 340, 570);
        }
        else if (!restart){
            if (stage == 0){
                bg.setColor (bgColor);
                bg.fill();
                if (map != null){
                    map.addObject (startButton, 512, 500);
                    map.addObject (tutButton, 512, 600);
                }
            }else if (stage ==  1){
                bg.drawImage (tutPics[tutPicNum], 0, 0);

                map.addObject (nextButton, 940, 300);
                map.addObject (prevButton, 84, 300);
                map.addObject (backButton, 940, 50);
            }
        }
        else{
            bg.setColor (Color.BLACK);
            bg.fill();

            bg.setFont      (new Font ("Times New Roman", 1, 70));
            bg.setColor     (Color.WHITE);
            bg.drawString   ("GAME OVER", 300, 200);

            bg.setFont      (startButtonFont);
            bg.setColor     (Color.WHITE);
            bg.drawString   ("Press r to restart"  , 350, 400);
            bg.drawString   ("or press esc to exit", 340, 500);
        }
        this.setImage (bg);
    }

    /**
     * Returns true if the game has started
     */
    public boolean gameStart(){
        return gameStart;
    }

    /**
     * Sets true if the user won the game
     */
    public void setWin (boolean w){
        win = w;
        if (w){
            restart = true;
        }
    }
}
