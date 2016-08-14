package org.dsl.list;

import java.util.Scanner;

/**
 * Created by rajesh on 13/8/16.
 */
public class SinglyLinkedListUI {

    Scanner scanner;

    enum Choice {
        ADD(1),
        REMOVE(2),
        DISPLAY(3),
        EXIT(4);

        int choice;
        Choice(int choice) {
            this.choice =choice;
        }

        public static Choice getChoice(int val) {
            Choice ch = null;
            for (Choice l: Choice.values()) {
                if (l.choice == val) {
                    ch = l;
                    break;
                }
            }
            return ch;
        }

    }

    SinglyLinkedListUI() {
        scanner = new Scanner(System.in);
    }

    private static final String menuStr = "Enter the singly linked list operation choice:\n" +
            "1. Add data\n" +
            "2. Remove data\n" +
            "3. Display data\n" +
            "4. Exit";

    public Choice getNextChoice() {
        System.out.println(menuStr);
        int val = scanner.nextInt();
        return Choice.getChoice(val);
    }

    int getValue(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    public static void main(String[] args) {

        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<Integer>();
        SinglyLinkedListUI ui = new SinglyLinkedListUI();
        boolean shouldExit = false;

        do {
            Choice ch =ui.getNextChoice();
            int val = 0;
            switch (ch) {
                case ADD:
                    val = ui.getValue("Enter the value to be added to the list:");
                    singlyLinkedList.add(val);
                    singlyLinkedList.display();
                    break;
                case REMOVE:
                    val = ui.getValue("Enter the value to be removed to the list:");
                    singlyLinkedList.remove(val);
                    singlyLinkedList.display();
                    break;
                case DISPLAY:
                    singlyLinkedList.display();
                    break;
                case EXIT:
                    shouldExit = true;
                    break;
            }

        } while (!shouldExit);
    }
}
