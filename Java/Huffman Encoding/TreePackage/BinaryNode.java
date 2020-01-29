package TreePackage;

/** An implementation of the ADT Binary Node.
*
* This code is from Chapter 26 of
* Data Structures and Abstractions with Java 2/e
*      by Carrano
*/
import java.util.*;
    
class BinaryNode<T> implements BinaryNodeInterface<T>, java.io.Serializable
{
	private T data;
	private BinaryNode<T> left;
	private BinaryNode<T> right;

	public BinaryNode()
	{
		this(null); // call next constructor
	} // end default constructor
	
	public BinaryNode(T dataPortion)
	{
		this(dataPortion, null, null); // call next constructor
	} // end constructor

	public BinaryNode(T dataPortion, BinaryNode<T> leftChild,
				   BinaryNode<T> rightChild)
	{
		data = dataPortion;
		left = leftChild;
		right = rightChild;
	} // end constructor

	public T getData()
	{
		return data;
	} // end getData

	public void setData(T newData)
	{
		data = newData;
	} // end setData

	public BinaryNodeInterface<T> getLeftChild()
	{
		return left;
	} // end getLeftChild

	public void setLeftChild(BinaryNodeInterface<T> leftChild)
	{
		left = (BinaryNode<T>)leftChild;
	} // end setLeftChild

	public boolean hasLeftChild()
	{
		return left != null;
	} // end hasLeftChild

	public BinaryNodeInterface<T> getRightChild()
	{
		return right;
	} // end getRightChild

	public void setRightChild(BinaryNodeInterface<T> rightChild)
	{
		right = (BinaryNode<T>)rightChild;
	} // end setRightChild

	public boolean hasRightChild()
	{
		return right != null;
	} // end hasRightChild

	public boolean isLeaf()
	{
		return (left == null) && (right == null);
	} // end isLeaf

	public int getHeight()
	{
		return getHeight(this); // call private getHeight
	} // end getHeight

	private int getHeight(BinaryNode<T> node)
	{
		int height = 0;
		if (node != null)
			height = 1 + Math.max(getHeight(node.left),
						 getHeight(node.right));
		return height;
	} // end getHeight

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


    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<T>(data);
        if (left != null)
            newRoot.left = (BinaryNode<T>)left.copy();
        if (right != null)
            newRoot.right = (BinaryNode<T>)right.copy();
        return newRoot;
    } // end copy



} // end BinaryNode
