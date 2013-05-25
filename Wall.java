import greenfoot.*;
public class Wall extends Tower
{
    public Wall()
    {

        setImage("Towers/fireTower.png");
        attackRate = 20; // rate at which it attak
        range = 1; // 
        speed = 0;// speed of the projectile
        power = 0; 
        active = false; //will not let it fire
        element = 3;
        elementString = "wall"; //called when teh tower fires a bullet
        
        desc.add ("Insert Description Here");
        desc.add ("Wall used to block enemy. Does not affect enemy in any way.");
    }

}