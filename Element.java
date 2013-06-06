import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The element picture displaying air, water, fire, earth
 * 
 * @author James Lu
 * @version 1.0
 */
public class Element extends DummyImage
{
    private GreenfootImage[] element;

    private int id;

    public Element(){
        bg = new GreenfootImage ("UI/a.png");

        element = new GreenfootImage[5];
        element[0] = new GreenfootImage (10,10);
        element[1] = new GreenfootImage ("UI/a.png");
        element[2] = new GreenfootImage ("UI/w.png");
        element[3] = new GreenfootImage ("UI/f.png");
        element[4] = new GreenfootImage ("UI/e.png");

        id = 0;
        bg.fill();
    }

    /**
     * Sets the id of the element <br>
     * 0 is the current wave <br>
     * 1 is the next wave <br>
     * 2 is the monster/tower's element
     */
    public void setId (int i){
        id = i;
        bg = element[id];
    }
}