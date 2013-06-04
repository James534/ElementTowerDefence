import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * The menu that pops up after user hovers over certain buttons
 * 
 * @author James Lu && Terence Lai
 */
public class HoverMenu extends Actor
{
    private GreenfootImage bg;
    private GreenfootImage menuImg;
    private int width;
    private int height;

    private Font titleFont;
    private Font descFont;

    private Color bgColor;
    private Color titleColor;
    private Color descColor;

    private String[] desc;
    private Map map;
    public HoverMenu(){
        width = 300;
        height = 300;
        bg = new GreenfootImage (width, height);
        menuImg = new GreenfootImage ("UI/menu.png");

        titleFont = new Font ("Vrinda", 1, 40);
        descFont  = new Font ("Vrinda", 1, 20);

        bgColor    = new Color (255, 0, 0);
        titleColor = new Color (255, 255, 255);
        descColor  = new Color (255, 255, 255);

        desc = new String[6];
        for (int i = 0; i< desc.length; i++){
            desc[i] = "";
        }

        refresh();
    }

    private void refresh(){
        bg.clear();

        bg.drawImage (menuImg, 0, 0);

        bg.setFont (titleFont);
        bg.setColor (titleColor);
        bg.drawString (desc[0], 20, 50);
        bg.setFont (descFont);
        bg.drawString (desc[1], 20, 110);
        bg.drawString (desc[2], 20, 130);
        bg.drawString (desc[3], 20, 150);
        bg.drawString (desc[4], 20, 170);
        bg.drawString (desc[5], 20, 190);

        this.setImage (bg);
    }

    protected void addedToWorld(World world)
    {
        map = (Map) world;
    }

    public void setData(Actor a){

        for (int i = 0; i < desc.length; i++){
            desc[i] = "";
        }

        if (a instanceof SendCreeps){       //if the actor passed is the button to send mobs
            SendCreeps b = (SendCreeps) a;
            Creep c = b.getCreep();         //make a instance of the creep that the button will send

            desc[0] = c.getName();
            desc[1] = "HP: " + Integer.toString (c.getHp());
            desc[2] = "Bounty: " + Integer.toString (c.getMaxHp() / 20);
            desc[3] = "Income: " + Integer.toString (b.getIncome());
            desc[4] = "Armor: " + Integer.toString (c.getArmor());
            desc[5] = "Speed: " + Float.toString (c.getSpeed());
        }
        else if (a instanceof DebuffButton){
            DebuffButton d = (DebuffButton) a;

            Debuff h = new Debuff (d.getId(), d.getLevel()); 
            desc[0] = h.getName(); 
            desc[1] = "Costs: "            + Integer.toString(d.getCost());
            desc[2] = "Damage Over time: " + Float.toString(h.getDOT()) ;
            desc[3] = "Slow: "            + Float.toString(h.getSlow());
            desc[4] = "Armor reduction " + Float.toString(h.getRedu());

        }
        else if (a instanceof BuildTowers){
            BuildTowers b = (BuildTowers) a; 
            Tower t = b.getTower(); 

            desc[0] =  t.getName(); 
            desc[1] = "Damage: "      + Integer.toString(t.getDmg()); 
            desc[2] = "Attack Rate: " + Integer.toString(t.getAttackSpeed()); 
            desc[3] = "Range: "      + Integer.toString(t.getRange()); 
            desc[4] = "Cost: "        + Integer.toString(t.getCost()) ;

            //add prices into the hoverable

        }
        else if (a instanceof SellButton){
            desc[1] = "Sells current tower";
        }
        else if (a instanceof UpgradeButton) {
            Tower t = map.getSelectedTower(); 
            if (t != null)
            {    desc[1] = "CurrentLevel: " +Integer.toString(t.getCurrentLevel())  ;
                desc[2] = "Cost: " + Integer.toString(t.getUpgradeCost()) ; 
            }
            desc[3] = "Power +5%";
            desc[4]  ="Range + 5%"; 
        }

        refresh();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}