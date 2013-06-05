import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The progress meter showing how many enimies are left in the wave
 * 
 * @author James Lu
 * @version 1.0
 */
public class WaveProgress extends DummyImage implements HoverInfo
{
    private GreenfootImage progress;
    private GreenfootImage progressLeft;
    private GreenfootImage progressSide;

    private int enemyMax;
    private int enemyLeft;
    public WaveProgress(){
        bg           = new GreenfootImage ("UI/progress.png");
        progress     = new GreenfootImage ("UI/progress.png");
        progressLeft = new GreenfootImage ("UI/waveProgress.png");
        progressSide = new GreenfootImage ("UI/progressSides.png");

        enemyMax = 10;
        enemyLeft = 10;

        bg.drawImage (progressSide, 0, 0);
    }

    /**
     * Sets the max enemies of the wave
     */
    public void setMax(int max){
        enemyMax = max;
    }

    /**
     * Sets how many enemies are left
     */
    public void setLeft(int left){
        enemyLeft = left;
        int x = Math.round ( (float) enemyLeft / enemyMax * 200);

        bg.clear();
        bg.drawImage (progress, 0,0);
        bg.drawImage (progressLeft, x, 0);
        bg.drawImage (progressSide, 0, 0);
    }

    /**
     * Resets the meter
     */
    public void reset(){
        bg.clear();
        bg.drawImage (progress, 0,0);
        bg.drawImage (progressSide, 0, 0);
    }
}