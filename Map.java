import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;
import java.lang.NullPointerException;

/**
 * Element Tower Defence <br>
 * class controls the entire game
 * 
 * 
 * @author (Terence Lai & James Lu)
 * @version 1.00
 * 
 * ok now this is a test from the pc to the mac
 *
 */
public class Map extends World{
    private Tile[][] map;                                 //2d array of tiles
    private StartScreen startScreen;                      //the start screen before the main game
    private LoadScreen loadScreen; 

    //starting and ending points of start, checkpoints and end
    private int startX, startY;
    private int endX1, endY1;
    private int endX2, endY2;
    private int endXf, endYf;

    private int topSpace, leftSpace;                     //top and left spaces before the grid starts

    //three pathfinders for the 3 paths
    private Pathfind pf1;
    private Pathfind pf2;
    private Pathfind pf3;

    private ArrayList <ArrayList <Integer>> flyingPath;          //path for the flying units

    //list of mobs
    private ArrayList<Enemy> mobs;
    private LinkedList<Enemy> mobsToSpawn;

    private boolean start;      //start game
    private boolean init;       //initalizing
    private int initCounter;

    //variables for spawning mobs
    private boolean levelStart;
    private boolean flyingLevel;
    private boolean bossLevel;
    private int currentElement;
    private int nextElement;
    private int level;
    private int time;
    private int spawnCount;
    private int maxSpawnCount;
    private int spawnRate;

    private int money;
    private int income;
    private int lives;
    private int currentRunSpeed;
    private int[] runSpeed;
    private int[] runSpeedDelay;

    //ui stuff
    private Ui ui;
    private boolean refreshUi;
    private ChatBox cb;
    private WaveProgress wp;
    private Data data;
    private PointerArrow pa;
    private TowerButton towerButton;
    private CreepButton creepButton;
    private HoverInfo prevButton;

    /** the hover menu that pops up*/
    public final HoverMenu hm = new HoverMenu();

    /**Terrence's variables **/
    private ArrayList<Tower> towers = new ArrayList<Tower>(); //put in constructor 

    private boolean place =  false; //used to place the tower onto the field
    private int lvCounter;
    private int buttonDelay; 

    //must keep a temporary refrence to the tower since it there are subsequent commands
    private Tower selectedTower;
    private Tower placeHolder;
    private Range r; 

    //sound
    /** the sound class*/
    public final Sound s = new Sound();
    private int[] volume;
    private int currentVolume;

    /** 
     * variable initation
     * put in here since you intalize everything after you start the game
     */
    private void intalize (){
        if (initCounter == 1){
            //x and y values
            startX = 0; startY = 0;
            endX1 = 10; endY1 = 12;
            endX2 = 39; endY2 = 12;
            endXf = 49; endYf = 24;
            topSpace = 15; leftSpace = 12;  //spaces from the top and left, hardcoded on enemy

            data = new Data();

            map = new Tile [endYf+1][endXf+1];        //new map

            //new arraylists
            flyingPath = new ArrayList <ArrayList <Integer>>();
            mobs = new ArrayList<Enemy>();
            mobsToSpawn = new LinkedList <Enemy> ();
        }
        else if (initCounter == 2){
            //new pathfinders
            pf1 = new Pathfind (startX, startY, endX1, endY1, this);       //top to the first checkpoint
            pf2 = new Pathfind (endX1, endY1, endX2, endY2, this);         //middle path
            pf3 = new Pathfind (endX2, endY2, endXf, endYf, this);         //rightmost path
        }
        else if (initCounter == 3){
            //tower defence stuff
            money           = 100;
            income          = 0;
            lives           = 20;
            level           = 1;
            time            = 0;
            spawnCount      = 0;
            flyingLevel     = false;
            levelStart      = false;
            bossLevel       = false;
            currentRunSpeed = 1;
            runSpeed = new int [5];
            runSpeed[0] = 40;
            runSpeed[1] = 50;
            runSpeed[2] = 60;
            runSpeed[3] = 70;
            runSpeed[4] = 80;
            runSpeedDelay = new int [5];
            runSpeedDelay[0] = 15;
            runSpeedDelay[1] = 30;
            runSpeedDelay[2] = 100;
            runSpeedDelay[3] = 250;
            runSpeedDelay[4] = 700;     
        }
        else if (initCounter == 4){
            //ui
            ui = new Ui();          
        }
        else if (initCounter == 5){
            refreshUi = false;
            cb = new ChatBox();
            wp = new WaveProgress();
            pa = new PointerArrow();
            towerButton = new TowerButton();
            creepButton = new CreepButton();
            buttonDelay = 0; 
            //generates the map
            generate();      

            //sets the path first
            setPath();

            //generates the path for the flying path, its always gona stay the same, so doing it here
            flyingPath.add (pf1.generatePath());       
            flyingPath.add (pf2.generatePath());
            flyingPath.add (pf3.generatePath());
            //populates the data queue for mobs
            data.populate();

            addObject (ui, 512, 643);
            addObject (cb, 512, 500);
            addObject (wp, 120, 675);
            addObject (towerButton, 262, 538);
            addObject (creepButton, 762, 538);
        }
        else if (initCounter == 6){
            /** Terence's stuff**/
            //Towers
            towers = new ArrayList<Tower>();

            volume = new int[21];
            currentVolume = 16;
            for (int i = 0; i < 21; i++){
                volume[i] = i*5;
            }
            s.setVolume (volume[currentVolume]);

            //variable to run place movemet 
            place =  false;

            //stuff before start
            init = false;
            start = true;
            removeObject (loadScreen);
            s.stopMenu();

            ui.setWaveData (0, 1);
        }
    }

    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        //if it gone past the start menu
        if (start){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            uiAct();        
            s.run();
            //if the level started, increase the time and prepare the spawn
            if (levelStart){
                prepareSpawn();
                time++;
            }
            /**Terence's Stuff**/

            checkInput(mouse); 
            if (mouse != null){
                trackButtons(mouse);
                if (place) // used to place tower when onto the field
                    trackMouse(mouse);
            }
        }
        else if (init){
            initCounter++;
            intalize();
            loadScreen.update();

        }
        else{
            //if the user presses a when they are on the start screen, start the actual game
            //temp fix, add buttons later
            if (startScreen.gameStart()){
                removeObjects (getObjects (SSButtons.class));
                removeObject (startScreen);
                loadScreen = new LoadScreen(6);
                addObject (loadScreen, 512, 384);
                init = true;
            }
        }
    }

    /**
     * Method that checks the mouse over of buttons
     */
    private void trackButtons (MouseInfo m){
        List<Actor> l = getObjectsAt (m.getX(), m.getY(), null);        //list of all the actors the mouse is ontop of
        if (l.size() == 1 && l.get(0) instanceof Ui){ 
            //if its just the ui, not hovering over a button
            if (prevButton != null){
                prevButton = prevButton;
                prevButton.resetCounter();
            }
            prevButton = null;
        }
        else{
            for (Actor a: l){
                if (a instanceof Button){       //if the mouse is ontop of a button, change the pic
                    Button b = (Button) a;
                    b.changeImg (true);
                }
                if (a instanceof HoverInfo){       
                    HoverInfo h = (HoverInfo) a;
                    if (h == prevButton){
                        h.hoverOver();
                    }else if (prevButton != null){
                        prevButton.resetCounter();
                    }

                    prevButton = h;
                    return;
                }
            }
        }
        removeObject (hm);
    }

    /**
     * Method that checks what did the user click on.  or what buttons are pressed.
     * all user interaction is does in this method
     */
    private void checkInput(MouseInfo mouse){
        if(mouse != null && Greenfoot.mouseClicked(null)){
            Actor selected = mouse.getActor();
            if      (selected instanceof Tower && placeHolder == null){
                //if the user selectes a tower, are not placing another tower and have not selected another tower
                selectedTower = (Tower) selected ;               
                refreshUi = true;
                resetDefaultUi();
            }
            else if (selected instanceof Tile || selected instanceof Range){    //cancel selection of the tower
                cancelBuild();                
            } 
            else if (selected instanceof Enemy && selectedTower == null){
                cancelBuild();
                ui.setMobData ((Enemy)selected);
                ui.changeUi (5);
                drawArrow (false);
            }
            else if (selected instanceof DebuffVisu && selectedTower == null){
                cancelBuild();
                DebuffVisu d = (DebuffVisu) selected;
                ui.setMobData (d.getEnemy());
                ui.changeUi (5);
                drawArrow (false);                
            }
            else if (selected instanceof Button){
                Button b = (Button) selected;
                s.playClicked();
                if (selected instanceof TowerButton){
                    cancelBuild();
                    ui.changeUi (1);
                    towerButton.clicked (true);
                }
                else if (selected instanceof CreepButton){
                    cancelBuild();
                    ui.changeUi (2);
                    creepButton.clicked (true);
                }
                else if (selected instanceof SellButton && selectedTower != null){
                    b.clicked(true);
                    map[selectedTower.getGridY()] [selectedTower.getGridX()].setWalkable(true);
                    map[selectedTower.getGridY()] [selectedTower.getGridX()].setPlaceable(true);
                    selectedTower.sell();
                    removeObject (selectedTower);
                    towers.remove(selectedTower); 
                    setPath();
                    cancelBuild();
                }
                else if (selected instanceof UpgradeButton){
                    b.clicked (true);
                    if (money >= selectedTower.getUpgradeCost()){
                        if (selectedTower.upgradeable()){
                            changeMoney (-selectedTower.getUpgradeCost());
                            selectedTower.upgrade(false);  
                            refreshUi = true;
                        }else{
                            cb.setMessage ("Tower is fully upgraded", 1);
                        }
                    }else{
                        cb.setMessage ("You Require More Money", 1);
                    }
                }
                else if (selected instanceof SendCreeps){
                    SendCreeps button = (SendCreeps) selected;
                    button.clicked (true);
                    if (money >= button.getCost()){
                        income += button.getIncome();
                        changeMoney (-button.getCost());
                        resetUi();
                        mobsToSpawn.add (button.getCreep());
                        cb.setMessage ("sending 1 creep", 2);
                    }
                    else{
                        cb.setMessage ("YOU DO NOT HAVE ENOUGH MONEY", 1);
                    }
                }
                else if (selected instanceof BuildTowers && selectedTower == null){
                    BuildTowers button = (BuildTowers) selected;
                    button.clicked (true);
                    if (levelStart == false){
                        if (money >= button.getCost()){
                            placeHolder = button.getTower();

                            place = true;                               //terrence's place variable
                            ui.setTowerData (placeHolder);              //changes the ui to match the tower
                            ui.changeUi (4);
                            r = new Range(placeHolder.getRange()) ;     //shows the range of the tower

                            if (getObjects (PointerArrow.class) != null){       //removes the arrow if there is one
                                removeObject (pa);
                            }
                            addObject (r, 10000, 10000); 
                            addObject (placeHolder, 10000, 10000);
                        }else{
                            cb.setMessage ("YOU REQUIRE MORE MONEY", 1);
                        }
                    }
                    else{
                        placeHolder = null;             
                        cb.setMessage ("You Cant Build During a Wave", 1);
                    }
                }
                else if (selected instanceof DebuffButton){
                    DebuffButton d = (DebuffButton) selected;
                    if (d.isBought() == false){
                        if (money >= d.getCost()){
                            changeMoney (-d.getCost());
                            d.bought (true);
                            selectedTower.addDebuff (d.getId(), d.getLevel());
                            cb.setMessage ("Bought " + d.getName(), 2);
                        }
                        else{
                            cb.setMessage ("You Require More Money", 1);
                        }
                    }
                }
            }
        }
        //short cut keys that do not use the mouse

        //if the user presses s, start the level
        //temp spawning        
        if (Greenfoot.isKeyDown ("space") && levelStart == false){
            cancelBuild(); 
            levelStart = true;
            try {
                setLevelSpawn();
            }
            catch (NullPointerException e){
                winGame(); 
            }
        }else if (Greenfoot.isKeyDown("escape")){
            cancelBuild(); 
        }else if (Greenfoot.isKeyDown("=")){
            if (buttonDelay > runSpeedDelay[currentRunSpeed]){
                currentRunSpeed++;
                buttonDelay = 0;
                if (currentRunSpeed <= 4){
                    Greenfoot.setSpeed (runSpeed[currentRunSpeed]);
                    cb.setMessage ("Speed changed to " + runSpeed[currentRunSpeed], 2);
                }else{
                    currentRunSpeed = 4;
                }
            }
        }else if (Greenfoot.isKeyDown("-")){
            if (buttonDelay > runSpeedDelay[currentRunSpeed]){
                currentRunSpeed--;
                buttonDelay = 0;
                if (currentRunSpeed >= 0){
                    Greenfoot.setSpeed (runSpeed[currentRunSpeed]);
                    cb.setMessage ("Speed changed to " + runSpeed[currentRunSpeed], 2);
                }else{
                    currentRunSpeed = 0;
                }
            }
        }else if (Greenfoot.isKeyDown("up")){
            if (buttonDelay > runSpeedDelay[currentRunSpeed]){
                buttonDelay = 0;
                currentVolume++;
                if (currentVolume <= 20){
                    s.setVolume (volume[currentVolume]);
                    cb.setMessage ("Volume increased to " + volume[currentVolume], 2);
                }else{
                    currentVolume = 20;
                }
            }
        }else if (Greenfoot.isKeyDown("down")){
            if (buttonDelay > runSpeedDelay[currentRunSpeed]){
                buttonDelay = 0;
                currentVolume--;
                if (currentVolume >= 0){
                    s.setVolume (volume[currentVolume]);
                    cb.setMessage ("Volume increased to " + volume[currentVolume], 2);
                }else{
                    currentVolume = 0;
                }
            }
        }else if (Greenfoot.isKeyDown("1")){
            cancelBuild();
            ui.changeUi (1);
            towerButton.clicked (true);
        }else if (Greenfoot.isKeyDown("2")){
            cancelBuild();
            ui.changeUi (2);
            creepButton.clicked (true);
        }
        else if (Greenfoot.isKeyDown("U") && selectedTower != null)//temp placement
        {
            if (buttonDelay >= 38) {
                if (money >= selectedTower.getUpgradeCost() ){
                    if (selectedTower.upgradeable() ){
                        changeMoney (-selectedTower.getUpgradeCost());
                        selectedTower.upgrade(false);  
                        buttonDelay =  0;
                        refreshUi = true;
                    } else{
                        cb.setMessage ("Tower is Fully Upgraded", 1);
                    }
                }else{
                    cb.setMessage ("You Require More Money", 1);
                }
            }
        }

        /**     grid controls for building towers **/
        if (ui.getId() == 1){
            boolean tempPlace = false;
            BuildTowers button = null;
            String tempString = Greenfoot.getKey();
            if (tempString != null && selectedTower == null){
                if (tempString.equals ("q")){                //air 1
                    button = (BuildTowers) ui.getButton (1, 0);
                    tempPlace = true;
                }
                else if (tempString.equals ("a")){            //air 2
                    button = (BuildTowers) ui.getButton (1, 1);
                    tempPlace = true;
                }
                else if (tempString.equals ("z")){            //air 3
                    button = (BuildTowers) ui.getButton (1, 2);
                    tempPlace = true;
                }
                else if (tempString.equals ("w")){            //water 1
                    button = (BuildTowers) ui.getButton (1, 3);
                    tempPlace = true;
                }
                else if (tempString.equals ("s")){            //water 2
                    button = (BuildTowers) ui.getButton (1, 4);
                    tempPlace = true;
                }
                else if (tempString.equals ("x")){            //water 3
                    button = (BuildTowers) ui.getButton (1, 5);
                    tempPlace = true;
                }
                else if (tempString.equals ("e")){            //fire 1
                    button = (BuildTowers) ui.getButton (1, 6);
                    tempPlace = true;
                }
                else if (tempString.equals ("d")){            //fire 2
                    button = (BuildTowers) ui.getButton (1, 7);
                    tempPlace = true;
                }
                else if (tempString.equals ("c")){            //fire 3
                    button = (BuildTowers) ui.getButton (1, 8);
                    tempPlace = true;
                }
                else if (tempString.equals ("r")){            //earth 1
                    button = (BuildTowers) ui.getButton (1, 9);
                    tempPlace = true;
                }
                else if (tempString.equals ("f")){            //earth 2
                    button = (BuildTowers) ui.getButton (1, 10);
                    tempPlace = true;
                }
                else if (tempString.equals ("v")){            //earth 3
                    button = (BuildTowers) ui.getButton (1, 11);
                    tempPlace = true;
                }
            }

            if (tempPlace){
                button.clicked (true);
                s.playClicked();
                if (levelStart == false){
                    if (money >= button.getCost()){
                        placeHolder = button.getTower();

                        place = true;                               //terrence's place variable
                        ui.setTowerData (placeHolder);              //changes the ui to match the tower
                        ui.changeUi (4);
                        r = new Range(placeHolder.getRange()) ;     //shows the range of the tower

                        if (getObjects (PointerArrow.class) != null){       //removes the arrow if there is one
                            removeObject (pa);
                        }
                        addObject (r, 10000, 10000); 
                        addObject (placeHolder, 10000, 10000);
                    }else{
                        cb.setMessage ("YOU REQUIRE MORE MONEY", 1);
                    }
                }
                else{
                    placeHolder = null;             
                    cb.setMessage ("You Cant Build During a Wave", 1);      
                }
            }
        }
        /**         GRID HOTKEYS TO SEND CREEPS     */
        else if (ui.getId() == 2){
            boolean tempPlace = false;
            SendCreeps button = null;
            String tempString = Greenfoot.getKey();

            if (tempString != null){
                if (tempString.equals ("q")){                       //air 1
                    button = (SendCreeps) ui.getButton (0, 0);
                    tempPlace= true;
                }
                else if (tempString.equals ("a")){                  //air 2
                    button = (SendCreeps) ui.getButton (0, 1);
                    tempPlace= true;
                }
                else if (tempString.equals ("z")){                  //air 3
                    button = (SendCreeps) ui.getButton (0, 2);
                    tempPlace= true;
                }
                else if (tempString.equals ("w")){                  //water 1
                    button = (SendCreeps) ui.getButton (0, 3);
                    tempPlace= true;
                }
                else if (tempString.equals ("s")){                  //water 2
                    button = (SendCreeps) ui.getButton (0, 4);
                    tempPlace= true;
                }
                else if (tempString.equals ("x")){                  //water 3
                    button = (SendCreeps) ui.getButton (0, 5);
                    tempPlace= true;
                }
                else if (tempString.equals ("e")){                  //fire 1
                    button = (SendCreeps) ui.getButton (0, 6);
                    tempPlace= true;
                }
                else if (tempString.equals ("d")){                  //fire 2
                    button = (SendCreeps) ui.getButton (0, 7);
                    tempPlace= true;
                }
                else if (tempString.equals ("c")){                  //fire 3
                    button = (SendCreeps) ui.getButton (0, 8);
                    tempPlace= true;
                }
                else if (tempString.equals ("r")){                  //earth 1
                    button = (SendCreeps) ui.getButton (0, 9);
                    tempPlace= true;
                }
                else if (tempString.equals ("f")){                  //earth 2
                    button = (SendCreeps) ui.getButton (0, 10);
                    tempPlace= true;
                }
                else if (tempString.equals ("v")){                  //earth 3
                    button = (SendCreeps) ui.getButton (0, 11);
                    tempPlace= true;
                }
            }
            if (tempPlace){
                button.clicked (true);
                s.playClicked();
                if (money >= button.getCost()){
                    income += button.getIncome();
                    changeMoney (-button.getCost());
                    resetUi();
                    mobsToSpawn.add (button.getCreep());
                    cb.setMessage ("sending 1 creep", 2);
                }
                else{
                    cb.setMessage ("YOU DO NOT HAVE ENOUGH MONEY", 1);
                }
            }
        }
        buttonDelay++;
    }

    /**
     * Method for following the mouse when placing on field and placing the object. 
     * WHEN PLACE == TRUE
     * */
    public void trackMouse(MouseInfo mouse){        
        double x = mouse.getX();
        double y = mouse.getY();

        float snapRealX = Math.round((x-22)/20f) * 20f + 22;
        float snapRealY = Math.round((y-25)/20f) * 20f + 25;

        int snapX = Math.round(( (float) x-22) /20f); //these are the coordinates of the grid
        int snapY = Math.round(( (float) y-25) /20f);

        if (withinField (snapRealX , snapRealY)){
            placeHolder.setLocation ( (int)snapRealX,(int)snapRealY);

            if (r == null)
            {
                r = new Range(placeHolder.getRange());
                addObject (r, Math.round (snapRealX), Math.round (snapRealY));
            }
            else 
            {
                r.setLocation (Math.round (snapRealX), Math.round (snapRealY));
            }

            if (Greenfoot.mouseClicked (null) ) {
                createTower((int)snapRealX,(int)snapRealY, snapX, snapY,Greenfoot.isKeyDown("shift")) ;

                removeObject (r);
                r = null; 
            }  
        }
    }

    /**
     * cancels build mode and deletes the placeholder tower
     */
    private void cancelBuild(){
        //REFUND THE MONEY WHEN YOU CANCEL BUILD 

        if (placeHolder != null){           //cancels the building of a tower
            removeObject(placeHolder);

            placeHolder = null; 
            place = false; 

            removeObject (r);
            r = null;
        }
        else if (selectedTower != null){         //cancels the selection of a tower
            ui.changeUi (0);
            showRange (0, false);
            selectedTower = null;
        }

        if (ui.getId () != 0){
            ui.changeUi (0);
        }
        resetDefaultUi();
    }

    /**
     * used to delete the pointer arrow
     * called after the mob its pointing to dies
     * or 
     * when the user cancels the tower selection
     */
    public void resetDefaultUi(){
        if (getObjects (PointerArrow.class) != null){
            removeObject (pa);
            ui.changeUi (0);
        }
        towerButton.clicked (false);
        creepButton.clicked (false);
    }

    /**
     * Creates a tower and adds it to the world. detects what tower to place based on its place holder
     */
    private void createTower(int realX, int realY, int x, int y,boolean shiftClick){
        Tower t = new FireTower(); //just a preset tower, should be replaced soon, since a generic tower is abstract 
        if (map[y][x].getWalkable() && map[y][x].getPlaceable() && canStillWalk(x, y)){

            if      (placeHolder instanceof  FireTower){
                t = new FireTower();}
            else if (placeHolder instanceof WaterTower){
                t= new WaterTower();}
            else if (placeHolder instanceof EarthTower){
                t= new EarthTower();  }
            else if (placeHolder instanceof   AirTower){
                t= new AirTower(); }

            int tempLevel = placeHolder.getLevel();
            for (int i = 1; i < tempLevel; i++){
                t.upgrade(true);
            }

            int cost = t.getCost();
            if (money >= cost){
                changeMoney (-cost);
                //places the real tower 
                selectedTower = null;  // prevents the placement click from slecting the tower

                map[y][x].setWalkable(false);  
                map[y][x].setPlaceable (false);
                if (!shiftClick){
                    cancelBuild();
                }
                t.setGridX(x);//pass the grid coordinates for further refrence
                t.setGridY(y);
                addObject(t,realX,realY);

                towers.add(t);  //added to arraylist
                setPath();
                t.setActive(false);
            }
            else{
                cb.setMessage ("YOU REQUIRE MORE MINERALS", 1);
            }

        }
        else{
            cb.setMessage ("YOU CANT BUILD THERE", 1);
        }
    }

    /**
     * changes to the money either through buying or selling. positive numbers increase money while negative numbers decrease money.
     */
    public void changeMoney(int t){
        money+=t;
        resetUi();
        if (t < 0){
            s.playMoney(); 
        }
    }

    /**
     * Checks to see if the coordinates supplied are within the playing area of the game
     */
    public boolean withinField(double x, double y){
        if ( x > 15 && x < 1014 && y >= 25 && y < 518) {
            return true;
        }
        return false; 
    }

    /**
     * Constructor for objects of class Map.
     */
    public Map(){    
        super(1024, 768, 1); 

        Greenfoot.setSpeed (50);

        startInitialize (false);

        setPaintOrder (SSButtons.class, StartScreen.class, HoverMenu.class, ChatBox.class, 
            WaveProgress.class, Button.class, DummyImage.class, Ui.class,
            Explosion.class, HealthBar.class, PointerArrow.class, Weapon.class, DebuffVisu.class,
            Tower.class, Range.class, Enemy.class,
            Tile.class);   
    }

    /**
     * draws arrow on top of the selected tower to giver better indication to the user. 
     */
    public void drawArrow(boolean ifTower){
        if (ifTower){
            addObject (pa, selectedTower.getX(), selectedTower.getY() - 20);
            pa.setMob (null);
        }
        else{
            Enemy e = ui.getMob();
            if (e.getHp() > 0){
                pa.setMob (e);
                addObject (pa, e.getX(), e.getY() - 20);
            }
        }
    }

    private void uiAct(){
        if (selectedTower != null && refreshUi){
            refreshUi = false;
            ui.setTowerData (selectedTower);
            ui.changeUi (3);
            drawArrow (true);
            if (r != null)
            {
                removeObject(r);
            }
            showRange(selectedTower.getRange(), true);                    
        }
    }

    private void showRange(int range, boolean show){
        if (show){
            r = new Range(range);
            addObject (r, selectedTower.getX(), selectedTower.getY());
        }
        else{
            removeObject (r);
            r = null;
        }
    }

    /**
     * resets the ui
     * call after change any values
     */
    public void resetUi (){
        ui.setGeneralData (lives, money, income);
    }

    /**
     * initializes the start menu
     * called when reseting the game
     */
    private void startInitialize (boolean restart){        
        startScreen = new StartScreen (restart);        
        addObject (startScreen, 512, 384);
        start = false;
        init = false;
        initCounter = 0;
    }

    /**
     * lose a life
     * called after a mob exits the map
     */
    public void loseLife(){
        if (lives == 1){
            start = false;
            removeObjects (getObjects(null));       //removes all the objects on the screen

            //starts the game agian
            startInitialize (true);
        }
        lives--;
        resetUi();
        cb.setMessage ("Lost a life", 1);
    }

    /**
     * called after a mob dies
     * deletes it from the world, and removes it from the arraylist of mobs
     */
    public void mobDie (Enemy mob, boolean exitWorld){
        if (!exitWorld){
            changeMoney (mob.getMaxHp() / 10);
        }
        else{
            loseLife();
        }
        mobs.remove (mob);
        removeObject (mob);
    }

    /**
     * prepares the spawn for the mobs
     */
    private void prepareSpawn(){
        if (time % spawnRate == 0){
            if (maxSpawnCount > spawnCount){
                spawn(mobsToSpawn);
                spawnCount++;
                wp.setLeft (maxSpawnCount - spawnCount);
            }
        }

        if (maxSpawnCount <= spawnCount && mobs.size() == 0){
            levelStart = false;
            ui.setWaveData (0, nextElement);
            spawnCount = 0;
            time = 0;
            level++;

            List <Tower> tempTower = getObjects (Tower.class);
            for (Tower t: tempTower){
                t.setActive (false);
            }
            wp.reset();
        }
    }

    /**
     * sets what element the current wave is
     * also sets the special type of enemy (boss, flying, etc)
     */
    private void setLevelSpawn(){

        data.nextLevel();
        bossLevel       = data.ifBoss();    //checking if its a boss level, every 10 levels        
        currentElement  = data.getType();   //1 is air, 2 is water, 3 is fire, 0 is earth
        nextElement     = data.getNextType();
        maxSpawnCount   = data.getMaxSpawn();
        spawnRate       = data.getSpawnRate();
        int tempSpawn   = mobsToSpawn.size();       //the number of mobs the user sent

        /** insert here code to tell user the new round started */
        ui.setWaveData (currentElement, nextElement);

        money += income;
        ui.setGeneralData (lives, money, income);
        cb.setMessage ("Level " + level + " Starting", 2);

        for (int i = 0; i < maxSpawnCount; i++){
            Enemy newEnemy = null;
            //add what type of enemy to make
            if      (currentElement == 1){
                newEnemy = new AirMob   (data.getHp(), data.getArmor(), data.getSpeed(), 
                    data.ifBoss(), data.ifFlying() );
            }
            else if (currentElement == 2){
                newEnemy = new WaterMob (data.getHp(), data.getArmor(), data.getSpeed(), 
                    data.ifBoss(), data.ifFlying() );
            }
            else if (currentElement == 3){
                newEnemy = new FireMob  (data.getHp(), data.getArmor(), data.getSpeed(), 
                    data.ifBoss(), data.ifFlying() );
            }
            else if (currentElement == 4){
                newEnemy = new EarthMob (data.getHp(), data.getArmor(), data.getSpeed(), 
                    data.ifBoss(), data.ifFlying() );            
            }
            mobsToSpawn.addFirst (newEnemy);
        }

        //adds the numbers of mobs to spawn to the max spawn count
        maxSpawnCount += tempSpawn;

        List <Tower> tempTower = getObjects (Tower.class);
        for (Tower t: tempTower){
            t.setActive (true);
        }
        //System.out.println (level);
        wp.setMax (maxSpawnCount);
    }

    /**
     * spawns a mob
     */
    private void spawn(LinkedList<Enemy> ll){
        Enemy newEnemy = ll.remove();
        addObject (newEnemy, 10+leftSpace, 10+topSpace);
        mobs.add (newEnemy);
    }

    /**
     * sets the path on the tiles
     */
    private void setPath(){
        ArrayList <ArrayList <Integer>> finalPath = new ArrayList <ArrayList <Integer>>();
        if (flyingLevel){   //flying level path is always the same
            finalPath = flyingPath;
        }
        else{
            //generates the 3 paths for each checkpoint
            finalPath.add (pf1.generatePath());       
            finalPath.add (pf2.generatePath());
            finalPath.add (pf3.generatePath());
        }

        for (int Y = 0; Y < map.length; Y++){
            for (int X = 0; X < map[0].length; X++){
                map [Y][X].reset();
            }
        }

        for (int n = 0; n < 3; n++){
            ArrayList<Integer> tempPath = finalPath.get (n);
            for (int i = tempPath.size()-1; i >=0 ;i--){            
                int tempY = tempPath.get (i);
                i--;
                int tempX = tempPath.get (i);

                //changes the id of the tile so it is walkable
                map [tempY][tempX].setId (1);

                //setting the direction for the tiles if its not the last tile
                if (i >= 2){
                    int tempPathX = tempPath.get (i-2);
                    int tempPathY = tempPath.get (i-1);

                    int movementX = tempPathX - tempX;
                    int movementY = tempPathY - tempY;
                    map[tempY][tempX].setMovement (movementX, movementY, n);
                }
            }
        }
        map[endYf][endXf].setMovement(true);
    }

    private boolean canStillWalk(int x, int y){
        map[y][x].setWalkable (false);
        boolean result = false;
        if (pf1.generatePath() != null){
            if (pf2.generatePath() != null){
                if (pf3.generatePath() != null){
                    result = true;
                }
            }
        }
        map[y][x].setWalkable (true);
        return result;
    }

    /**
     * generates the map at first
     */
    private void generate(){        
        for (int y = 0; y < map.length; y++){
            for (int x = 0; x < map[0].length; x++){
                map [y][x] = new Tile(x, y, this);
                addObject (map[y][x], x*20+10 + leftSpace, y*20+10 + topSpace);
            }
        }
        map[endYf][endXf].setMovement(true);

        //sets the special colors
        map [startX][startY].setId (2, true);
        map [endY1][endX1].setId (3, true);
        map [endY1][endX2].setId (3, true);
        map [endYf][endXf].setId (4, true);
    }

    /**
     * returns of the tile on x, y is walkable
     */
    public boolean getTileWalkable(int x, int y){
        if (map[y][x].getWalkable()){
            return true;
        }
        return false;
    }

    /**
     * returns the X length of the map in tiles
     */
    public int getSizeX(){
        return map[0].length;
    }

    /**
     * returns the Y length of the map in tiles
     */
    public int getSizeY(){
        return map.length;
    }

    /**
     * returns the tile at X, Y
     */
    public Tile getTile(int x, int y){
        if (x >= 0 && x < map[0].length){
            if (y >= 0 && y < map.length){
                return map [y][x];
            }
        }
        return null;
    }    

    /**
     * Checks to see if the enemy is still in game
     */
    public boolean checkEnemy(Enemy n){
        if (mobs.contains(n))
        { return true;}
        return false; 
    }

    /**
     * returns the current income
     */
    public int getIncome(){
        return income;
    }

    /**
     * displays the message on the screen
     */
    public void displayMessage(String s, int id)
    {
        cb.setMessage(s,id);
    }

    /**
     * Method that gets the world to play a sound based on the id that they input. 
     */
    public void playSound(int Id)
    {
        s.play(Id); 
    }

    /**
     * Returns the tower that is currently selected
     */
    public Tower getSelectedTower()
    {
        return selectedTower; 
    }

    private void winGame()
    {
    }

}