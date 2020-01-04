package ca.monor.tree;

import ca.monor.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void preOrder() {
        if (root == null) {
            return;
        }

        System.out.print("Pre Order: ");
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            System.out.print(node.element + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    public void preOrderRecursion() {
        System.out.print("Pre Order: ");
        preOrderRecursion(root);
        System.out.println();
    }

    private void preOrderRecursion(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + " ");
        preOrderRecursion(node.left);
        preOrderRecursion(node.right);
    }

    public void inOrder() {
        if (root == null) {
            return;
        }
        Stack<Node<E>> nodes = new Stack<>();
        Node<E> node = root;
        System.out.print("In Order: ");

        while (node != null || !nodes.isEmpty()) {
            if (node != null) {
                nodes.push(node);
                node = node.left;
            } else {
                node = nodes.pop();
                System.out.print(node.element + " ");
                node = node.right;
            }
        }
        System.out.println();
    }

    public void inOrderRecursion() {
        if (root == null) {
            return;
        }
        System.out.print("In Order: ");
        inOrderRecursion(root);
        System.out.println();
    }

    private void inOrderRecursion(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderRecursion(node.left);
        System.out.print(node.element + " ");
        inOrderRecursion(node.right);
    }

    public void postOrder() {
        if (root == null) {
            return;
        }
        Stack<Node<E>> preOrderStack = new Stack<>();
        Stack<Node<E>> postOrderStack = new Stack<>();
        preOrderStack.push(root);
        System.out.print("Post Order: ");

        while (!preOrderStack.isEmpty()) {
            Node<E> node = preOrderStack.pop();
            postOrderStack.push(node);
            if (node.left != null) {
                preOrderStack.push(node.left);
            }
            if (node.right != null) {
                preOrderStack.push(node.right);
            }

            while (!postOrderStack.isEmpty()) {
                System.out.print(postOrderStack.pop().element + " ");
            }
            System.out.println();
        }
    }

    public void postOrderRecursion() {
        if (root == null) {
            return;
        }
        System.out.print("Post Order: ");
        postOrderRecursion(root);
        System.out.println();
    }

    private void postOrderRecursion(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderRecursion(node.left);
        postOrderRecursion(node.right);
        System.out.print(node.element + " ");
    }

    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = queue.size();
        int height = 0;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            levelSize--;

            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    public int heightRecursion() {
        return heightRecursion(root);
    }

    private int heightRecursion(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursion(node.left), heightRecursion(node.right));
    }

    public boolean isComplete() {
        if (root == null) {
            return false;
        }

    }

    protected Node<E> creatNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            Node<E> predecessor = node.left;
            while (predecessor.right != null) {
                predecessor = predecessor.right;
            }
            return predecessor;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }


    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node<E> successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    private class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public abstract class Visitor<E>{
            boolean stop;

            /**
             * 如果返回 true，就表示停止遍历
             * @param element
             * @return
             */
            abstract boolean visit(E element);
        }

        /**
         * 返回节点的兄弟节点
         * @return
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return this.parent.right;
            }
            if (isRightChild()) {
                return this.parent.left;
            }
            return null;
        }
    }
}
