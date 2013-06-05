/**
 * The interface for everything that shows a popup menu when the user hover overs it
 * 
 * @author James Lu
 * @version 1.0
 */
public interface HoverInfo  
{
    /**
     * Increases the hover counter when the mouse hovers over this object
     */
    void hoverOver();

    /**
     * Changes the image to be selected or not Pass true if the button is selected
     */
    void changeImg(boolean b);

    /**
     * Resets the hover counter when the mouse is no longer hovering over it
     */
    void resetCounter();
}