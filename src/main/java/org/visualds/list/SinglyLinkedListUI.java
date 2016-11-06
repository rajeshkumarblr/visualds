package org.visualds.list;

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
        REVERSE_RECUSRION(4),
        REVERSE_LOOP(5),
        CREATE_STANDARD_LIST(6),
        DUPLICATE_LIST(7),
        EXIT(8);

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
            "4. Reverse List By recursion " +
            "5. Reverse List By loop " +
            "6. Create Standard List (100,200,..1000)  " +
            "7. Duplicate List" +
            "8. Exit";

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

        //Create an initial list of 100,200,300...1000 for convenience.
        ui.createStandardList();
        singlyLinkedList.display();
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
                case CREATE_STANDARD_LIST:
                    ui.createStandardList();
                    singlyLinkedList.display();
                    break;
                case REVERSE_RECUSRION:
                    singlyLinkedList.reverse(true);
                    singlyLinkedList.display();
                    break;
                case REVERSE_LOOP:
                    singlyLinkedList.reverse(false);
                    singlyLinkedList.display();
                    break;
                case DUPLICATE_LIST:
                    SinglyLinkedList dupList = SinglyLinkedList.duplicateList(singlyLinkedList);
                    dupList.display();
                    break;
                case EXIT:
                    shouldExit = true;
                    break;
            }

        } while (!shouldExit);
    }
}
