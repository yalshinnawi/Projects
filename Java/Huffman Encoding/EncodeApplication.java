import java.awt.*;
import javax.swing.*;

/**
 * An application to detect possibly misspelled words
 *  
 * @author Charles Hoot 
 * @version 2.0
 */
    
public class EncodeApplication 
{

    public static void main (String args[]) 
    {    
        JPanel myPanel;
        Stepper myStepper;
        ActionThread myThread;
        Object dispatcher;
        
        myThread = new EncodeActionThread();                 // Change this line for different
                                                             // applications
        myPanel =  myThread.getAnimationPanel();
 
        dispatcher = new Object();
        AnimatedApplicationFrame myFrame = 
            new AnimatedApplicationFrame(myThread.getApplicationTitle(),
                                        dispatcher, myPanel, 
                                        myThread);
        myStepper = myFrame.getStepper();
        
        // must set the stepper before we start the thread
        myThread.setStepper(myStepper);
        myThread.start();
    }
    
 
}
