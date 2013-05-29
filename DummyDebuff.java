import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Dummy debuffs, the debuffs that show up on enimies when the user clicks on them
 * 
 * @author James Lu
 * @version 1.0
 */
public class DummyDebuff extends DummyImage
{
    private int id;
    public DummyDebuff(int id){
        this.id = id;
        if (id == 0){           //stun
            bg = new GreenfootImage ("debuff/stunned.png");
            this.setImage (bg);
        }
    }
}
