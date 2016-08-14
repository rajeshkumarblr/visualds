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
        REVERSE(4),
        STANDARD_LIST(5),
        EXIT(6);

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
            "1. Add data  " +
            "2. Remove data  " +
            "3. Display data  " +
            "4. Reverse List  " +
            "5. Create Standard List (100,200,..1000)  " +
            "6. Exit";

    public Choice getNextChoice() {
        System.out.println(menuStr);
        int val = scanner.nextInt();
        return Choice.getChoice(val);
    }

    int getValue(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    public void createStandardList() {
        for (int i = 100; i <= 1000; i+=100) {
            singlyLinkedList.add(i);
        }
    }

    static SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<Integer>();

    public static void main(String[] args) {
        SinglyLinkedListUI ui = new SinglyLinkedListUI();
        boolean shouldExit = false;

        do {
            Choice ch =ui.getNextChoice();
            if (ch == null) {
                System.out.println("Invalid choice:");
                continue;
            }
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
                case STANDARD_LIST:
                    ui.createStandardList();
                    singlyLinkedList.display();
                    break;
                case REVERSE:
                    singlyLinkedList.reverse();
                    singlyLinkedList.display();
                case EXIT:
                    shouldExit = true;
                    break;
            }

        } while (!shouldExit);
    }
}
