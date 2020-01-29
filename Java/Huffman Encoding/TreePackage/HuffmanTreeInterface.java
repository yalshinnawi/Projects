package TreePackage;


/**
 * Operations are provided to interact with a Huffman encoding tree.  
 * This is similar to the DecisionTreeInterface 
 * from Chapter 25 of
 * Data Structures and Abstractions with Java 2/e
 *      by Carrano
 * 
 * @author Charles Hoot 
 * @version 2.0
 */
    
public interface HuffmanTreeInterface<T> extends BinaryTreeInterface<SymbolFrequencyPacket<T>>
{
    /** 
     * Get the symbol/frequency for the current node in the Huffman tree.
     * @return The object of type SymbolFrequencyPacket being held at the current node.
     * */  
    public SymbolFrequencyPacket<T> getCurrentData();
    
    /** 
     * Determine whether current node contains a single code letter.
     * @return true if the current node is a leaf 
     * */
    public boolean isSingleSymbol();
    
    /**
     * Moves the current node to the left child of
     * the current node. 
     * */
    public void advanceLeft();

    /**
     * Moves the current node to the right child of
     * the current node. 
     * */
    public void advanceRight();

    /** 
     * Check the node to the left of the current node to see if a symbol is there.
     * @param symbol the symbol to look for 
     * @return true if the symbol is on the left
     * */
     public boolean checkLeft(T symbol);

    /** 
     * Check the node to the right of the current node to see if a symbol is there.
     * @param symbol the symbol to look for 
     * @return true if the symbol is on the right
     * */    
     public boolean checkRight(T symbol);
    
    /** 
     * Sets the current node to the root of the tree.
     * */
    public void reset();
} // end HuffmanTreeInterface