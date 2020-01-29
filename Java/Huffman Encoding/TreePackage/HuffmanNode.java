package TreePackage;

/** An implementation of the ADT Binary Node.
*
* This code is based on BinaryNode from Chapter 26 of
* Data Structures and Abstractions with Java 2/e
*      by Carrano
*/
import java.util.*;
import java.awt.Graphics;

    
class HuffmanNode<T> implements BinaryNodeInterface<SymbolFrequencyPacket<T>>, java.io.Serializable
{
	private SymbolFrequencyPacket<T> data;
	private HuffmanNode<T> left;
	private HuffmanNode<T> right;

	public HuffmanNode()
	{
		this(null); // call next constructor
	} // end default constructor
	
	public HuffmanNode(SymbolFrequencyPacket<T> dataPortion)
	{
		this(dataPortion, null, null); // call next constructor
	} // end constructor

	public HuffmanNode(SymbolFrequencyPacket<T> dataPortion, HuffmanNode<T> leftChild,
				   HuffmanNode<T> rightChild)
	{
		data = dataPortion;
		left = leftChild;
		right = rightChild;
	} // end constructor

	synchronized
	public SymbolFrequencyPacket<T> getData()
	{
		return data;
	} // end getData

	synchronized
	public void setData(SymbolFrequencyPacket<T> newData)
	{
		data = newData;
	} // end setData

	synchronized
	public BinaryNodeInterface<SymbolFrequencyPacket<T>> getLeftChild()
	{
		return left;
	} // end getLeftChild

	synchronized
	public void setLeftChild(BinaryNodeInterface<SymbolFrequencyPacket<T>> leftChild)
	{
		left = (HuffmanNode<T>) leftChild;
	} // end setLeftChild

	synchronized
	public boolean hasLeftChild()
	{
		return left != null;
	} // end hasLeftChild

	synchronized
	public BinaryNodeInterface<SymbolFrequencyPacket<T>> getRightChild()
	{
		return right;
	} // end getRightChild

	synchronized
	public void setRightChild(BinaryNodeInterface<SymbolFrequencyPacket<T>> rightChild)
	{
		right = (HuffmanNode<T>) rightChild;
	} // end setRightChild

	synchronized
	public boolean hasRightChild()
	{
		return right != null;
	} // end hasRightChild

	synchronized
	public boolean isLeaf()
	{
		return (left == null) && (right == null);
	} // end isLeaf

	synchronized
	public int getHeight()
	{
		return getHeight(this); // call private getHeight
	} // end getHeight

	synchronized
	private int getHeight(HuffmanNode<T> node)
	{
		int height = 0;
		if (node != null)
			height = 1 + Math.max(getHeight(node.left),
						 getHeight(node.right));
		return height;
	} // end getHeight

	synchronized
	public int getNumberOfNodes()
	{
		int leftNumber = 0;
		int rightNumber = 0;

		if (left != null)
			leftNumber = left.getNumberOfNodes();

		if (right != null)
			rightNumber = right.getNumberOfNodes();

		return 1 + leftNumber + rightNumber;
	} // end getNumberOfNodes


    synchronized
    public HuffmanNode<T> copy()
    {
        HuffmanNode<T> newRoot = new HuffmanNode<T>(data);
        if (left != null)
            newRoot.left = (HuffmanNode<T>)left.copy();
        if (right != null)
            newRoot.right = (HuffmanNode<T>)right.copy();
        return newRoot;
    } // end copy


     /**
     * draw a representation of the node centered at
     * the given location  (pass it onto the data)
     * 
     * @param   g  the graphics context to draw on   
     * @param   centerX  x position of text to draw
     * @param   centerY  y position of text to draw
     * 
     */
 
    synchronized
    public void drawOn(Graphics g, int centerX, int centerY)
    {
        data.drawOn(g, centerX, centerY);        
    } // end drawOn

} // end HuffmanNode
