import greenfoot.*;

/**
 * DataBase of Sound files.
 * 
 * @author (Terence Lai)
 */
public class Sound
{
    private String[] attack;
    private String loseMoneyName;
    private String gainMoneyName;
    private String clicked;

    private GreenfootSound menu;
    private GreenfootSound ambient;
    private GreenfootSound boss;

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

        loseMoneyName   = "sounds/buy.wav";
        gainMoneyName   = "sounds/coins.wav";
        clicked         = "sounds/clicked.wav";

        menu    = new GreenfootSound ("sounds/menu.mp3");
        ambient = new GreenfootSound ("sounds/ambient.mp3");
        boss    = new GreenfootSound ("sounds/boss.mp3");

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
     * Play the sound when the user loses gold
     */
    public void playLoseMoney(){
        GreenfootSound money = new GreenfootSound (loseMoneyName);
        money.setVolume (volume);
        money.play();
    }

    /**
     * Plays the money sound when the user gains gold
     */
    public void playGainMoney(){
        GreenfootSound money = new GreenfootSound (gainMoneyName);
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
        GreenfootSound money = new GreenfootSound(loseMoneyName);
        money = new GreenfootSound(gainMoneyName);
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
        ambient .setVolume (volume - volume/3*2);
        boss    .setVolume (volume - volume/3*2);
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

    /**
     * Plays the ambient music
     */
    public void playAmbient(){
        ambient.playLoop();
    }

    /**
     * Stops playing the ambient music
     */
    public void stopAmbient(){
        if (ambient.isPlaying()){
            ambient.stop();
        }
    }

    /**
     * Plays the boss music
     */
    public void playBoss(){
        boss.play();
    }

    /**
     * Stops playing the boss music
     */
    public void stopBoss(){
        if (boss.isPlaying()){
            boss.stop();
        }
    }

    /**
     * Stops all current music
     */
    public void stop(){
        menu.stop();
        ambient.stop();
        boss.stop();
    }
}