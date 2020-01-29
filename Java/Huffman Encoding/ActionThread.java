import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The abstract framework for a thread that animates an application
 * 
 * @author Charles Hoot 
 * @version 2.0
 */

abstract class ActionThread extends Thread
{

    // These methods must be defined in the subclass

    /**
     * Create the specialized animation panel that the application will use
     * @return a new animation panel.
     */
    abstract JPanel createAnimationPanel();

    /**
     * Create and place the GUI components for the application in the specialized
     * animation panel
     */
    abstract void setUpApplicationSpecificControls();

    /**
     * Initialize the variables that the application will use.
     */
    abstract void init();

    /**
     * Execute the application a single time
     */
    abstract void executeApplication();

    /**
     * What is the title to use with the application
     * @return a string for the title
     */
    abstract String getApplicationTitle();

    /**
     * Constructor for objects of class ActionThread
     */
    public ActionThread()
    {

        myPanel =  createAnimationPanel();
        resetApplicationInThread = false;
        killTheThread = false;
        changesAllowed = true;

        setUpApplicationSpecificControls();       
    }

    // **************************************************************************
    // This code handles the mechanics of running the application in the thread
    // It should not depend on the actual application being run
    // **************************************************************************

    private boolean resetApplicationInThread;
    private boolean killTheThread;
    private boolean changesAllowed;
    private Stepper myStepper;
    private JPanel myPanel;

    /**
     * Time to wait for the user.  Make it well behaved too.
     */
    public void animationPause()
    {
        myStepper.animationStep();
        makeThreadWellBehaved();
    }

    /**
     * Time to wait for the user.  Force to the last step.
     */
    public void forceLastPause()
    {
        myStepper.finalStep();
        makeThreadWellBehaved();
    }

    /**
     * Check to see if the thread should kill itself or reset the execution of
     * the application
     */
    public void makeThreadWellBehaved()
    {
        if(killTheThread)
            throw new KillThreadException("Thread is being killed");
        if(resetApplicationInThread)
            throw new ResetApplicationException("Application is being reset");
    }

    /**
     * Return the specialized panel so the animation frame can include it
     * @return the animation panel
     */
    public JPanel getAnimationPanel()
    {
        return myPanel;
    }

    /**
     * determine if the application specific controls should be active
     * @return true if the controls should be active, false otherwise
     */
    public boolean applicationControlsAreActive()
    {
        return changesAllowed;
    }

    /**
     * Use the given stepper to control the animation
     */
    public void setStepper(Stepper s)
    {
        myStepper = s;
    }

    /**
     * Signals that the thread should reset the execution of the application
     */
    public void resetExecution()
    {
        resetApplicationInThread = true;
    }

    /**
     * Signals that the thread should kill itself at the first opportunity
     */
    public void killThread()
    {
        killTheThread = true;
    }

    /**
     * Run the application multiple times.  This satisfies Thread responsibilities.
     */
    public void run()
    {
        boolean keepGoing = true;
        //This will allow many executions of the application

        while(keepGoing)
        {
            // basic initialization for application
            init();
            changesAllowed = true;

            //This is the setup animation step.  The user can make changes beyond the
            // basic initialization before starting the using private controls.
            myStepper.setupStep();

            // No changes allowed now
            changesAllowed = false;

            try
            {            
                // This will be the initial state display
                init();
                myStepper.initialStateStep();
                makeThreadWellBehaved();

                // Start up the application
                executeApplication();

                //This is the finish animation step.  
                //Nothing should happen until reset is pressed
                myStepper.finalStep();

            }
            catch(KillThreadException e)
            {
                keepGoing = false;
            }
            catch(ResetApplicationException e)
            {
                //We got reset; go around again!
            }                        
            resetApplicationInThread = false;
        }
    }

} // end class ActionThread
