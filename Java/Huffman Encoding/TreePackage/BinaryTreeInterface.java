package TreePackage;

/** An interface for the ADT Binary Tree.
*
* This code is from Chapter 25 of
* Data Structures and Abstractions with Java 2/e
*      by Carrano
*/

public interface BinaryTreeInterface<T> extends TreeInterface<T>, TreeIteratorInterface<T>
{
    /** Task: Sets an existing binary tree to a new one-node binary tree.
    * @param rootData an object that is the data in the new tree�s root
    */
    public void setTree(T rootData);
    
    /** Task: Sets an existing binary tree to a new binary tree.
    * @param rootData an object that is the data in the new tree�s root
    * @param leftTree the left subtree of the new tree
    * @param rightTree the right subtree of the new tree */
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
    BinaryTreeInterface<T> rightTree);
} // end BinaryTreeInterface