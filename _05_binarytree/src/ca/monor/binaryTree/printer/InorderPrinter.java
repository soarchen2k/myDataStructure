package ca.monor.binaryTree.printer;
import ca.monor.binaryTree.BinaryTreeInfo;

/**

              ┌──800
          ┌──760
          │   └──600
      ┌──540
      │   └──476
      │       └──445
 ┌──410
 │   └──394
 381
 │     ┌──190
 │     │   └──146
 │  ┌──40
 │  │  └──35
 └──12
    └──9

 * @author MJ Lee
 *
 */

public class InorderPrinter extends Printer {
    private static String rightAppend;
    private static String leftAppend;
    private static String blankAppend;
    private static String lineAppend;

    static{
        int length = 2;
        rightAppend = "┌" + Strings.repeat("─", length);
        leftAppend = "└" + Strings.repeat("─", length);
        blankAppend = Strings.blank(length + 1);
        lineAppend = "│" + Strings.blank(length);
    }

    public InorderPrinter(BinaryTreeInfo tree) {
        super(tree);
    }

    /**
     * 生成打印的字符串
     */
    @Override
    public String printString() {
        StringBuilder stringBuilder = new
                StringBuilder(printString(tree.root(),
                "",
                "",
                ""));
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * 生成node节点的字符串
     *
     * @param nodePrefix  node那一行的前缀字符串
     * @param leftPrefix  node整棵左子树的前缀字符串
     * @param rightPrefix node整棵右子树的前缀字符串
     * @return
     */
    private String printString(
            Object node,
            String nodePrefix,
            String leftPrefix,
            String rightPrefix) {
        Object left = tree.left(node);
        Object right = tree.right(node);
        String string = tree.string(node).toString();

        int length = string.length();
        if (length % 2 == 0) {
            length--;
        }
        length >>= 1;  // length = length>>1

        String nodeString = "";
        if (right != null) {
            rightPrefix += Strings.blank(length);
            nodeString += printString(
                    right,
                    rightPrefix + rightAppend,
                    rightPrefix + lineAppend,
                    rightPrefix + blankAppend
            );
        }
        nodeString += nodePrefix + string + "\n";
        if (left != null) {
            leftPrefix += Strings.blank(length);
            nodeString += printString(
                    left,
                    leftPrefix + leftAppend,
                    leftPrefix + lineAppend,
                    leftPrefix + blankAppend
            );
        }
        return nodeString;
    }
}
