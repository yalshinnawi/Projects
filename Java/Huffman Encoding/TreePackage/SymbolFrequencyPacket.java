package TreePackage;

/**
 * used to store a list of symbols and their combined frequency for use
 * in a Huffman code tree.
 * 
 * @author Charles Hoot
 * @version 2.0
 */
import java.util.*;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;

public class SymbolFrequencyPacket<T>
{
    List<T> symbolList;
    long totalFrequency;
    boolean isHighLighted;

    /**
     * Constructor for objects of class SymbolFreqencyPacket
     */
    public SymbolFrequencyPacket(List<T> symbols, long frequency)
    {
        symbolList = symbols;
        totalFrequency = frequency;
        isHighLighted = false;
    }

    /**
     * Get the combined frequency of all the symbols
     * 
     * @return     the total frequency 
     */
    synchronized public long getFrequency()
    {
        return totalFrequency;
    }

    /**
     * Get the List of all the symbols
     * 
     * @return     the total frequency 
     */
    synchronized public List<T> getSymbols()
    {
        return symbolList;
    }

    /**
     * Determine if a symbol is in the list
     *
     * @param  symbol   the symbol to look for
     * @return     true if the symbol is in the list
     */
    synchronized public boolean inList(T symbol)
    {
        List pat = new ArrayList();
        pat.add(symbol);
        List rat = new ArrayList();
        rat.add(pat);
        //System.out.println(symbolList.get(0).equals(rat.get(0)));
        if (symbolList.get(0).equals(rat.get(0))) {
            return true;
        }
        return false;
    }

    /**
     * Set the highlighting
     * 
     * @param highlight if true, highlight the node 
     */
    synchronized public void setHighLighting(boolean highlight)
    {
        isHighLighted = highlight;
    }

    public static final int TEXTHEIGHT = 15;

    /**
     * draw a representation of the symbol frequency packet centered at
     * the given location
     * 
     * @param   g  the graphics context to draw on   
     * @param   centerX  x position of text to draw
     * @param   centerY  y position of text to draw
     * 
     */
    synchronized public void drawOn(Graphics g, int centerX, int centerY)
    {
        String toDraw = new String("");;

        FontMetrics fm = g.getFontMetrics();
        if (symbolList.size() == 1)
        {
            // only one symbol draw it
            toDraw = symbolList.get(0).toString();
        }
        else
        {
            // draw the frequency
            toDraw = (new Long(totalFrequency)).toString();
        }

        int width = fm.stringWidth(toDraw);

        int startX = centerX - width/2;
        int startY = centerY - TEXTHEIGHT/2;

        // Blank the back ground
        g.setColor(Color.white);
        g.fillRect(startX, startY, width, TEXTHEIGHT);

        if(isHighLighted)
            g.setColor(Color.red);
        else
            g.setColor(Color.black);

        int baseLineY = centerY + TEXTHEIGHT/2;
        // draw the string
        g.drawString(toDraw, startX, baseLineY);

    } // end drawOn

}
