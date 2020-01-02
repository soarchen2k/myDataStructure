package ca.monor.binaryTree.printer;

import ca.monor.binaryTree.BinaryTreeInfo;

public final class BinaryTrees {
    private BinaryTrees() {}

    public static void print(BinaryTreeInfo tree) {
        print(tree, null);
    }

    private static void print(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) {
            return;
        }
        printer(tree, style).print();
    }


    public static void println(BinaryTreeInfo tree) {
        println(tree, null);
    }

    public static void println(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) {
            return;
        }
        printer(tree, style).println();
    }

    public static String printString(BinaryTreeInfo tree) {
        return printString(tree, null);
    }

    public static String printString(BinaryTreeInfo tree, PrintStyle style) {
        if (tree == null || tree.root() == null) {
            return null;
        }
        return printer(tree, style).printString();
    }

    public static Printer printer(BinaryTreeInfo tree, PrintStyle style) {
        if (style == PrintStyle.IN_ORDER) {
            return new InorderPrinter(tree);
        }
        return new LevelOrderPrinter(tree);
    }
    public enum PrintStyle {
        LEVEL_ORDER, IN_ORDER
    }
}
