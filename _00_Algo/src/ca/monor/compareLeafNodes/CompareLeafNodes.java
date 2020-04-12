package ca.monor.compareLeafNodes;

public class CompareLeafNodes {
    public static void main(String[] args) {

        System.out.println();
    }

    private static class BinaryTree<E>{
        Node<E> root;

        private static class Node<E>{
            E element;
            Node<E> parent;
            Node<E> left;
            Node<E> right;

            public Node(E element, Node<E> parent) {
                this.element = element;
                this.parent = parent;
            }
        }


    }
}
