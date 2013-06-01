import greenfoot.*;
public class Wall extends Tower
{
    public Wall()
    {
        fileName = "Towers/wall";
        setImage("Towers/wall1.png");
        attackRate = 20; // rate at which it attak
        range = 1; // 
        speed = 0;// speed of the projectile
        power = 0; 
        active = false; //will not let it fire
        element = 3;
        elementString = "wall"; //called when teh tower fires a bullet

        desc.add ("Wall used to block enemy");
        desc.add ("Does not affect enemy");
    }
}