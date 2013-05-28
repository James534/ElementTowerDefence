import greenfoot.*;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * DataBase of Sound files
 */
public class Sound
{
    private LinkedList<GreenfootSound> track;
    private GreenfootSound[] attack;

    private String moneyName;
    private int volume;
    public Sound()
    {
        track = new LinkedList<GreenfootSound>();
        moneyName   = "Sounds/buy.wav";
        volume      = 50;
        attack      = new GreenfootSound[4];

        attack[0] = new GreenfootSound("Sounds/airAttack.wav"); 
        attack[1] = new GreenfootSound("Sounds/fireAttack.wav"); 
        attack[2] = new GreenfootSound("Sounds/earthAttack.wav"); 
        attack[3] = new GreenfootSound("Sounds/waterAttack.wav"); 

        playOnce(); 
    }

    public void run(){
        ArrayList <GreenfootSound> al = new ArrayList<GreenfootSound>();
        for (GreenfootSound s : track){
            if (s.isPlaying() == false){
                al.add (s);
            }
        }
        for (int i = 0; i < al.size(); i++){
            GreenfootSound s = al.get(i);
            track.remove (s);
        }
    }

    public void play(int Id)
    {
        attack[Id].play();
    } 

    public void playMoney(){
        GreenfootSound money = new GreenfootSound (moneyName);
        money.setVolume (volume);
        money.play();
        track.add (money);
    }

    private void playOnce()
    {
        GreenfootSound money = new GreenfootSound(moneyName);

        money.setVolume(0);
        money.play();
        money.setVolume(volume);
        for (int i = 0; i < attack.length; i++){
            GreenfootSound s = attack[i];
            s.setVolume (0);
            s.play();
            s.setVolume (volume);
        }
    }

    public void setVolume(int v){
        volume = v;
        for (GreenfootSound s : track){
            s.setVolume (volume);
        }
    }
}