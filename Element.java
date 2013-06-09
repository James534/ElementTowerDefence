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
        element[0] = new GreenfootImage (1,1);
        element[1] = new GreenfootImage ("UI/a.png");
        element[2] = new GreenfootImage ("UI/w.png");
        element[3] = new GreenfootImage ("UI/f.png");
        element[4] = new GreenfootImage ("UI/e.png");

        id = 0;
        bg.fill();
    }

    /**
     * Sets the element <br>
     * 0 is nothing <br>
     * 1 is air <br>
     * 2 is water <br>
     * 3 is fire <br>
     * 4 is earth
     */
    public void setId (int i){
        id = i;
        bg = element[id];
    }
    
    /**
     * Returns the id of this element image <br>
     * Returns the numerical value of the current displayed element
     */
    public int getId(){
        return id;
    }
}