import greenfoot.*;

/**
 * DataBase of Sound files
 */
public class Sound
{
    private GreenfootSound money;
    private GreenfootSound[] attack;
    public Sound()
    {
        money = new GreenfootSound("Sounds/buy.wav");
        attack = new GreenfootSound[4];

        attack[0] = new GreenfootSound("Sounds/"); 
        attack[1] = new GreenfootSound("Sounds/"); 
        attack[2] = new GreenfootSound("Sounds/"); 
        attack[3] = new GreenfootSound("Sounds/"); 
    }

    public void play(int Id)
    {
        if ( Id < 5)
        {
            attack[Id].play();
        }
        else
        {
            money.play(); 
        }

    } 
}