package org.dsl.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BstPrinter<T extends Comparable<T>> {

    public static <T extends Comparable<?>> void printNode(Bst.Node root) {
        int maxLevel = BstPrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Bst.Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BstPrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BstPrinter.printWhitespaces(firstSpaces);

        List<Bst.Node> newNodes = new ArrayList<Bst.Node>();
        for (Bst.Node node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BstPrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BstPrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BstPrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BstPrinter.printWhitespaces(1);

                BstPrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BstPrinter.printWhitespaces(1);

                BstPrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Bst.Node node) {
        if (node == null)
            return 0;

        return Math.max(BstPrinter.maxLevel(node.left), BstPrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}