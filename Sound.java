import greenfoot.*;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * DataBase of Sound files
 */
public class Sound
{
    private LinkedList<GreenfootSound> track;
    private String moneyName;
    private GreenfootSound[] attack;
    public Sound()
    {
        track = new LinkedList<GreenfootSound>();
        moneyName = "Sounds/buy.wav";
        attack = new GreenfootSound[4];

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
        money.play();
        track.add (money);
    }

    private void playOnce()
    {
        GreenfootSound money = new GreenfootSound(moneyName);

        money.setVolume(0);
        money.play();
        money.setVolume(50);
        for (int i = 0; i < attack.length; i++){
            GreenfootSound s = attack[i];
            s.setVolume (0);
            s.play();
            s.setVolume (50);
        }
    }
}