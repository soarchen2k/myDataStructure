package ca.monor.binaryTree;

public interface BinaryTreeInfo {
    /**
     * who is the root node
     */
    Object root();

    /**
     * how to get the left child of a node
     */
    Object left(Object node);

    /**
     * how to get the right child of a node
     */
    Object right(Object node);

    /**
     * how to print the node
     */
    Object String(Object node);
}
