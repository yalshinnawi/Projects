
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import TreePackage.*;
import java.lang.*;

/**
 * A Thread that contains the application we are going to animate
 * 
 * @author Charles Hoot 
 * @version 2.0
 */

public class EncodeActionThread extends ActionThread
{

    /**
     * Constructor for objects of class EncodeActionThread
     */
    public EncodeActionThread()
    {
        super(); 
    }

    public String getApplicationTitle()
    {

        return "Encode a file using Huffman Coding (Skeleton)";
    }

    // **************************************************************************
    // This is application specific code
    // **************************************************************************    

    // These are the variables that are parameters of the application and can be
    // set via the application specific GUI
    // Make sure they are initialized
    private String textFileName = "message2.txt";

    // Displayed items
    private Message myMessage;
    private Code myCode;
    private HuffmanTree<Character> [] myTrees;
    private int numberOfTrees;
    private int[] count;

    // ADD YOUR PRIVATE VARIABLE HERE

    private boolean textFileNotFound = false;
    private boolean treeCreated = false;

    public void init() 
    {
        myMessage = new Message();
        myCode = new Code();
        myTrees = null;
        numberOfTrees = 0;
        treeCreated = false;
        textFileNotFound = false;
    }

    public void executeApplication()
    {

        loadMessage(textFileName, myMessage);

        // ADD CODE HERE FOR A SINGLE RUN OF THE APPLICATION
        count = getCounts(myMessage);
        myTrees = getInitialTrees(count);
        numberOfTrees = myTrees.length;
        animationPause();
        while (myTrees[1] != null) {
            combineTrees();
        }
        treeCreated = true;
        for (int i = 0; i < count.length - 1; i++) {
            if (count[i] != 0) {
                System.out.print("Encoding " + intToChar(i) + " : ");
                encodeCharacter(intToChar(i), myCode, myTrees[0]);
                animationPause();
            }
        }
        System.out.println("Encoding Finished");
        myTrees[0].reset();
    }

    /**
     * count the number of times each character appears in the message. 
     *
     * @param aMessage an object that holds all the characters of the message
     * to be encoded.
     * @return an array with the count for the number of times each character occurs.
     */

    public int[] getCounts(Message aMessage)
    {
        int [] result = new int[128];

        // ADD CODE HERE TO COMPUTE THE FREQUENCY OF EACH CHARACTER
        while (aMessage.hasNext()) {
            int letter = charToInt(aMessage.next());
            result[letter] = result[letter] + 1;
        }
        return result;
    }

    /**
     * create the initial forrest of trees. 
     *
     * @param counts the frequency of each character
     * to be encoded.
     * @return the forest of trees for each single letter
     */
    public HuffmanTree<Character>[] getInitialTrees(int[] count)
    {
        HuffmanTree<Character> []  result = new HuffmanTree[50];
        // ADD CODE HERE TO CREATE THE FOREST OF INITIAL TREES
        int count2 = 0;
        for (int i = 0; i < count.length - 1; i++ ) {
            if (count[i] > 0) {
                List list = new ArrayList();
                list.add(intToChar(i));
                long g = count[i];
                SymbolFrequencyPacket data = new SymbolFrequencyPacket(list, g);
                result[count2] = new HuffmanTree(data);
                count2++;
            }
        }
        return result;
    }    

    /**
     * reduce the number of trees in the forest by one. This will operate by side effect
     * on the forest of trees contained in the variable myTrees.
     *
     */

    public void combineTrees()
    {
        List Clist = new ArrayList();
        List List1 = new ArrayList();
        List List2 = new ArrayList();
        SymbolFrequencyPacket leftData;
        SymbolFrequencyPacket rightData;
        SymbolFrequencyPacket mainData;
        HuffmanTree<Character> left;
        HuffmanTree<Character> right;
        HuffmanTree CData;
        long totalFreq;
        // // ADD CODE HERE THAT COMBINES THE TWO TREES WITH LEAST FREQUENCY
        for (int i = 0; i < myTrees.length - 1; i++) {
            if (myTrees[i+1] == null) {
                if (myTrees[i].getRootData().getSymbols().size() > 1) {
                    int minIndex = minValue();
                    long minFreq1 = myTrees[minIndex].getRootData().getFrequency();
                    right = myTrees[i];
                    totalFreq = minFreq1 + myTrees[i].getRootData().getFrequency();
                    List1.add(myTrees[minIndex].getRootData().getSymbols());
                    Clist.add(myTrees[minIndex].getRootData().getSymbols());
                    Clist.add(myTrees[i].getRootData().getSymbols());
                    remove(minIndex);
                    leftData = new SymbolFrequencyPacket(List1,minFreq1);
                    mainData = new SymbolFrequencyPacket(Clist,totalFreq);
                    left = new HuffmanTree(leftData);
                    CData = new HuffmanTree(mainData, left, right);
                    remove(i-1);
                    add(CData);
                    animationPause();
                    return;
                }
                break;
            }
        }
        int minIndex = minValue();
        long minFreq1 = myTrees[minIndex].getRootData().getFrequency();
        Clist.add(myTrees[minIndex].getRootData().getSymbols());
        List1.add(myTrees[minIndex].getRootData().getSymbols());
        remove(minIndex);
        minIndex = minValue();
        long minFreq2 = myTrees[minIndex].getRootData().getFrequency();
        Clist.add(myTrees[minIndex].getRootData().getSymbols());
        List2.add(myTrees[minIndex].getRootData().getSymbols());
        remove(minIndex);
        totalFreq = minFreq1 + minFreq2;
        mainData = new SymbolFrequencyPacket(Clist, totalFreq);
        if (minFreq1 < minFreq2) {
            leftData = new SymbolFrequencyPacket(List1, minFreq1);
            rightData = new SymbolFrequencyPacket(List2, minFreq2);
        } else {
            rightData = new SymbolFrequencyPacket(List1, minFreq1);
            leftData = new SymbolFrequencyPacket(List2, minFreq2);
        }
        left = new HuffmanTree(leftData);
        right = new HuffmanTree(rightData);
        CData = new HuffmanTree(mainData, left, right);
        add(CData);
        animationPause();
    }    

    public int minValue() {
        int i = 0;
        long minValue= myTrees[0].getRootData().getFrequency();
        int minIndex = 0;
        for (i = 0; i < myTrees.length - 1; i++) {
            if (myTrees[i] == null) {
                break;
            }
            if (myTrees[i].getRootData().getFrequency() < minValue) {
                minValue = myTrees[i].getRootData().getFrequency();
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void remove(int index) {
        int i, j;
        for (i = 0; i < myTrees.length - 1; i++) {
            if (i == index) {
                for (j = i; j < myTrees.length - 2; j++) {
                    myTrees[j] = myTrees[j+1];
                }
                break;
            }
        }
    }

    public void add(HuffmanTree data) {
        for (int i = 0; i < myTrees.length - 1; i++) {
            if (myTrees[i] == null) {
                myTrees[i] = data;
                break;
            }
        }
    }

    /**
     * encode a single symbol using a Huffman tree 
     *
     * @param c the symbol to be encoded.
     * @param codeBuffer the code buffer where the code characters will be placed
     * @param coder a Huffman tree used to encode the character
     */

    public void encodeCharacter(Character c, Code codeBuffer, HuffmanTree<Character> coder)
    {
        // ADD CODE HERE THAT ENCODE A SINGLE CHARACTER     
        int count1 = 0;
        int count0 = 0;
        while (!coder.checkRight(null)) {
            if (coder.checkLeft(c)) {
                codeBuffer.addCharacter('0');
                System.out.print('0');
                count0++;
                System.out.println(" " + count1 + " ones " + " and " + count0 + " zeros");
                coder.advanceLeft();
                animationPause();
                coder.reset();
                return;
            } else if (coder.checkRight(c)) {
                codeBuffer.addCharacter('1');
                System.out.print('1');
                count1++;
                coder.advanceRight();
                animationPause();
            } else {
                codeBuffer.addCharacter('1');
                System.out.print('1');
                count1++;
                coder.advanceRight();
                animationPause();
            }
        }
        System.out.println(" " + count1 + " ones " + " and " + count0 + " zeros");
        coder.reset();
        animationPause();
    }        

    /**
     * convert an integer value into an ascii character
     *
     * @param x the integer value
     *
     * @return the character or null if unable to convert
     */

    public Character intToChar(int x)
    {
        Character result = null;

        if (x <= 127 && x >= 0)
        {
            try
            {
                byte [] b = new byte[1];
                b[0] = (byte) x;
                String st = new String(b,"US-ASCII");
                result =  new Character(st.charAt(0));

            }
            catch (UnsupportedEncodingException e)
            {
                System.out.println("US-ASCII encoding is not supported - Whoops");
            }
        }
        return result;
    }

    /**
     * convert a character into an ascii integer
     *
     * @param c the character value to convert
     *
     * @return the integer or null if unable to convert
     */

    public Integer charToInt(char c)
    {
        Integer result = null;
        char[] in = new char[1];
        in[0] = c;
        String toConvert = new String(in);
        byte[] out;
        try
        {
            out = toConvert.getBytes("US-ASCII");
            if(out.length == 1)
                result = new Integer( (int) out[0]);
        } 
        catch (UnsupportedEncodingException e)
        {
            System.out.println("US-ASCII encoding is not supported - Whoops");
        }

        return result;
    }

    /**
     * load the words into the Message
     *
     * @param theFileName the name of the file holding the message
     *
     * @param theMesage the object to load.
     */
    public void loadMessage(String theFileName, Message theMessage)
    {
        Scanner input;        
        try
        {
            String inString;

            input = new Scanner( new File(theFileName ) );

            while(input.hasNextLine())  // read until  end of file
            {
                theMessage.addLine(input.nextLine());
            }          
            System.out.println(theMessage);
        }
        catch(Exception e)
        {
            System.out.println("There was an error in reading or opening the file: " +theFileName);
            System.out.println(e.getMessage());
            textFileNotFound = true;
            forceLastPause();
            throw new ResetApplicationException("Could not read message File");
        }

    }

    private static int DISPLAY_HEIGHT = 600;
    private static int DISPLAY_WIDTH = 1200;

    public JPanel createAnimationPanel()
    {
        return new AnimationPanel();
    }

    private static int MESSAGE_HEIGHT = 60;
    private static int CODE_HEIGHT = 60;
    private static int CONTROLS_HEIGHT = 50;
    private static int LINE_HEIGHT = 15;

    // This privately defined class does the drawing the application needs
    public class AnimationPanel extends JPanel
    {
        public AnimationPanel()
        {
            super();
            setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        }

        public void paintComponent(Graphics g)
        {
            String toDraw;
            super.paintComponent(g);
            toDraw = "Tree created: " + treeCreated;
            g.drawString(toDraw, 20, DISPLAY_HEIGHT - CONTROLS_HEIGHT - LINE_HEIGHT);

            if(textFileNotFound)
            {
                toDraw = "Text file not found; aborting";
                g.setColor(Color.red);
                g.drawString(toDraw, 20, DISPLAY_HEIGHT - CONTROLS_HEIGHT);
                g.setColor(Color.black);
            }             

            // Now draw the Huffman trees if they are available
            if(myTrees != null)
            {
                int base = 0;
                int delta = DISPLAY_WIDTH*5/numberOfTrees;
                for(int i=0; i<numberOfTrees; i++)
                {            
                    if(myTrees[i] != null)
                        myTrees[i].drawOn(g, base, (base+delta)*2, 20 + MESSAGE_HEIGHT + CODE_HEIGHT);
                    base += delta;
                }
            }

            // Now draw the message if it is available
            if(myMessage != null)
                myMessage.drawOn(g, 0, 20);

            // Now draw the code if it is available
            if(myCode != null)
                myCode.drawOn(g, 0, 20 + MESSAGE_HEIGHT);
        }
    }

    // **************************************************************************
    // This is the application specific GUI code
    // **************************************************************************    

    private JTextField textNameTextField;
    private JLabel setupStatusLabel;
    private JPanel setupPanel;

    public void setUpApplicationSpecificControls()
    {
        getAnimationPanel().setLayout(new BorderLayout());

        textNameTextField = new JTextField("message1.txt");
        textNameTextField.addActionListener(
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent event) 
                {
                    textNameTextFieldHandler();
                    getAnimationPanel().repaint();
                }
            }
        );

        setupStatusLabel = new JLabel("");
        setupPanel = new JPanel();
        setupPanel.setLayout(new GridLayout(2,2));

        setupPanel.add(new JLabel("Text file name:"));
        setupPanel.add(textNameTextField);
        setupPanel.add(setupStatusLabel);

        getAnimationPanel().add(setupPanel,BorderLayout.SOUTH);

    }

    private void textNameTextFieldHandler()
    {
        try
        {
            if(applicationControlsAreActive())   // Only change if we are in the setup phase
            {
                String input = textNameTextField.getText().trim();
                File test = new File(input);
                if(test.canRead())
                {
                    textFileName = input;
                    setupStatusLabel.setText("text file is now " + input);
                }
                else
                {
                    setupStatusLabel.setText("Could not read " + input);
                    textNameTextField.setText("");
                }

            }
        }
        catch(Exception e)
        {
            // don't change the name if we had an exception
            setupStatusLabel.setText("bad text file name");

        }

    }

} // end class ActionThread
