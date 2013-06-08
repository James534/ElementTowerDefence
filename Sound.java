import greenfoot.*;

/**
 * DataBase of Sound files.
 * 
 * @author (Terence Lai)
 */
public class Sound
{
    private String[] attack;
    private String moneyName;
    private String clicked;

    private GreenfootSound menu;

    private int volume;
    private int[] delay;
    public Sound()
    {
        volume      = 80;

        delay = new int[5];
        delay[0] = 0;
        delay[1] = 0;
        delay[2] = 0;
        delay[3] = 0;
        delay[4] = 0;

        attack      = new String[4];

        attack[0] = "sounds/airAttack.wav"; 
        attack[1] = "sounds/fireAttack.wav"; 
        attack[2] = "sounds/earthAttack.wav"; 
        attack[3] = "sounds/waterAttack.wav"; 

        moneyName   = "sounds/buy.wav";
        clicked = "sounds/clicked.wav";

        menu    = new GreenfootSound ("sounds/menu.mp3");

        playOnce(); 
        setVolume(volume);
    }

    public void run(){
        delay[0]++;
        delay[1]++;
        delay[2]++;
        delay[3]++;
        delay[4]++;
    }

    /**
     * Play sound based on the ID
     */
    public void play(int Id)
    {
        if (delay[Id] > 2){
            if (Id == 2 && delay[2] < 4){       //if its an earth, the delay is longer
                return;                         //stops the lag from playing too much sound files
            }

            delay[Id] = 0;
            GreenfootSound s = new GreenfootSound (attack[Id-1]);
            s.setVolume (volume);
            s.play();
        }
    } 

    /**
     * Play the sound when the user buys or sells a tower
     */
    public void playMoney(){
        GreenfootSound money = new GreenfootSound (moneyName);
        money.setVolume (volume);
        money.play();
    }

    /**
     * Play the click sound
     */
    public void playClicked(){
        GreenfootSound click = new GreenfootSound (clicked);
        click.setVolume (volume);
        click.play();
    }

    private void playOnce()
    {
        GreenfootSound money = new GreenfootSound(moneyName);

        money.setVolume(0);
        //money.play();
        for (int i = 0; i < attack.length; i++){
            GreenfootSound s = new GreenfootSound (attack[i]);
            s.setVolume (0);
            //s.play();
        }
    }

    /**
     * Change the volume of the game
     */
    public void setVolume(int v){
        volume = v;

        // sets the volume to every sound
        menu    .setVolume (volume - volume/3*2);
    }

    /**
     * Plays the bgm tracks. based on the ID of the song
     */
    public void playbgm(int Id) 
    {

    }

    /**
     * Plays the menu music on loop
     */
    public void playMenu(){
        menu.playLoop();
    }

    /**
     * Stops playing the menu music
     */
    public void stopMenu(){
        if (menu.isPlaying()){
            menu.stop();
        }
    }
}