package TreePackage;

/** An implementation of the ADT Binary Tree.
*
* This code is from Chapter 26 of
* Data Structures and Abstractions with Java 2.e
*      by Carrano
*/

import java.util.*;
    
public class BinaryTree<T> implements BinaryTreeInterface<T>,
            java.io.Serializable
{
    private BinaryNodeInterface<T> root;
    public BinaryTree()
    {
        root = null;
    } // end default constructor

    public BinaryTree(T rootData)
    {
        root = new BinaryNode<T>(rootData);
    } // end constructor

    public BinaryTree(T rootData, BinaryTree<T> leftTree,
                   BinaryTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor

    public void setTree(T rootData)
    {
        root = new BinaryNode<T>(rootData);
    } // end setTree
    
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                     BinaryTreeInterface<T> rightTree)
    {
        privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
    } // end setTree


    

    private void privateSetTree(T rootData,
                             BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        root = new BinaryNode<T>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);

        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        } // end if

        if ((leftTree != null) && (this != leftTree))
            leftTree.clear();

        if ((rightTree != null) && (this != rightTree))
                rightTree.clear();
    } // end privateSetTree


    public T getRootData()
    {
        T rootData = null;
        if (root != null)
            rootData = root.getData();
        return rootData;
    } // end getRootData

    public boolean isEmpty()
    {
        return root == null;
    } // end isEmpty

    public void clear()
    {
        root = null;
    } // end clear

    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    } // end setRootData

    protected void setRootNode(BinaryNodeInterface<T> rootNode)
    {
        root = rootNode;
    } // end setRootNode

    protected BinaryNodeInterface<T> getRootNode()
    {
        return root;
    } // end getRootNode

    public int getHeight()
    {
        // Modified from Carano to return 0 if the tree is empty
        if(root== null)
            return 0;
        else
            return root.getHeight();    
    } // end getHeight

    public int getNumberOfNodes()
    {
        // Modified from Carano to return 0 if the tree is empty
        if(root== null)
            return 0;
        else
            return root.getNumberOfNodes();
    } // end getNumberOfNodes

    public void inorderTraverse()
    {
        inorderTraverse(root);
    } // end inorderTraverse

    private void inorderTraverse(BinaryNodeInterface<T> node)
    {
        if (node != null)
        {
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getData());
            inorderTraverse(node.getRightChild());
        } // end if
    } // end inorderTraverse
    
    
    private class InorderIterator implements Iterator<T>
    {
        private Stack<BinaryNodeInterface<T>> nodeStack;
        private BinaryNodeInterface<T> currentNode;

        public InorderIterator()
        {
            nodeStack = new Stack<BinaryNodeInterface<T>>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            BinaryNodeInterface<T> nextNode = null;
            
            // find leftmost node with no left child
            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty())
            {
                nextNode =  nodeStack.pop();
                currentNode = nextNode.getRightChild();
            }
            else
                throw new NoSuchElementException();

            return nextNode.getData();
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end InorderIterator
    
    /* create an inorder iterator
    * return the iterator
    */
    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    }
    
    // Only the one iterator will be implemented by this code
    public Iterator<T> getPreorderIterator()
    {
        throw new RuntimeException("Post order iterators not yet supported by this class");   
    }
    public Iterator<T> getPostorderIterator()
    {
        throw new RuntimeException("Post order iterators not yet supported by this class");
    }
    public Iterator<T> getLevelOrderIterator()
    {
        throw new RuntimeException("Post order iterators not yet supported by this class");    
    }
    
    
    
    
    
    
}

	