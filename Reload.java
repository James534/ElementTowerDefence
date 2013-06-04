import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class for the reloading picture of towers
 * 
 * @author James Lu
 * @version 1.0
 */
public class Reload extends DummyImage
{
    private GreenfootImage[] weapons;
    private GreenfootImage cd;

    private int id;
    private int fireRate;
    private int cooldown;
    public Reload(){
        bg = new GreenfootImage ("UI/cd.png");
        weapons = new GreenfootImage[3];
        weapons[0] = new GreenfootImage ("UI/bullet.png");
        weapons[1] = new GreenfootImage ("UI/laser.png");
        weapons[2] = new GreenfootImage ("UI/shell.png");

        cd = new GreenfootImage ("UI/cd.png");
        id = 0;

        refresh();
    }

    private void refresh(){
        bg.clear();
        bg.drawImage (weapons[id], 0,0);

        cd.setTransparency (255 - Math.round ( (float)cooldown / fireRate * 255));
        bg.drawImage (cd, 0, 0);
    }

    public void act(){
        if (hoverCounter >= 50){
            map.hm.setData (this);
            int[] co = setCo();
            map.addObject (map.hm, co[0], co[1]);
        }
        refresh();
        this.setImage (bg);        
    }

    public void setId(int i){
        id = i;
    }

    public void setRate(int rate){
        fireRate = rate;
    }

    public void setCD(int c){
        cooldown = c;
    }
}