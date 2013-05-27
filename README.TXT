
 * Element Tower Defence
 * 
 * ------------------------------------------------
 * TO-DO:
 * 
 * 
 * SOUND!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * finish UI
 * 
 * Terene's To DO:
 * 
 * change the debuff system do you can buy debuffs
 * balance**
 * impliment sound***
 *
 * 
 * pop up description for weapons
 * 
 * ------------------------------------------------
 * Changelog:
 * NOW OFFICIALLY IN BETA

0.18 (I don't know what version, its after debuffs)
added try/catch to end the game (code end game code in)
fix healthbar layering

 * 
 * 0.16 (May 24,2013)
 * programmed functionality in upgrade button
 * all upgraded weapons working
 * shift clicking
 * improved clicking functionality (selecting multiple towers after one another)
 * fixed various bugs involving target hp
 * added different runtimes
 * added wall image
 * added upgrade costs
 * 
 * 0.15 (May 22, 2013)
 * made towers more efficicent
 * fixed bug where towers keep attacking the mob even though it's target hp is less than 0
 * added a upgrade button
 * made the buttons more efficient by adding a ui buttons class
 * added elemental damage
 * 
 * 0.14 (May 8, 2013)
 * implemeted gheto targetting priorities
 * fixed a big where towers target enimies that pass the goal
 * improved effiency of towers when there are no mobs
 * changed the way how certain buttons were handled
 * fixed a bug where send creeps button was not removed
 * added income
 * enabled the user to send creeps
 * organized image files
 * completely changed upgrade system
 * 
 * 
 * added wall
 * added artillery backend
 * 
 * 0.13 (Apr 26, 2013)
 * CHANGED MAP, RANGE, UI, WATERTOWER, TOWER
 * check changed dates for changed classes
 * made ui functional, changes according to which selection the user uses
 * added targetting hp onto the mobs
 *      -to help reduce too many towers shoot at the same mob, overkilling it
 * improved the 2 main buttons, made them change when hovering over them, and made them functional
 * added mechanics to change weapons
 * laser weapons do no track the enemy but pierce through the enemy
 * seperated the button class from the ui class
 * made the chat box remove itself when its not doing anything
 * recreated the sell button, made it so it changes when hovered over
 * 
 * 
 * Fixed bug where range wont go out of the screen
 * changed the ui when the user presses on a tower
 * added ui change when user presses on a mob
 * added an arrow to indicate what is selected
 * 
 * 0.12
 * refractor James' code
 * added a new class, Data, to store all the data
 *      -mainly to store the data for the mobs in a queue, so dont have to generate on the fly
 * added the aplha UI, working, but need to work on athestics
 * enabled money
 * changed pathfinding to be a method call instead of boolean change
 *      -make the act method run faster, dont need to check a boolean every act
 * changed the data storage into thte data class
 * added current and next waves onto the ui
 * added a "chatbox" to show messages to the user instead of system.out.println
 * added looping when the user runs out of lives
 * changed damage calculation formula, currently DOTA formula
 * 
 * added different elemental towers. 
 * added shortcuts keys to create towers
 * cancel the build command by pressing escape
 * 
 * 0.11
 * added different types of enemies
 * changed the way pictures are handled in tile
 * 
 * 
 * ------------------------------------------------
 * @James Lu & Terence Lai
 * @0.16