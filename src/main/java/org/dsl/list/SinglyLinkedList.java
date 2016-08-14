package org.dsl.list;

public class SinglyLinkedList<T> {

    class node <T>{
        private T data;
        private node next;
        node(T data) {
            this.data = data;
            next = null;
        }
    }

    node head;
    node tail;
    int count;

    SinglyLinkedList() {
        head = tail = null;
        count = 0;
    }

    public static SinglyLinkedList duplicateList(SinglyLinkedList list) {
        SinglyLinkedList dupList = new SinglyLinkedList();
        dupList.head  = null;
        if (list.head != null) {
            SinglyLinkedList.node tmp = list.head;
            dupList.head = dupList.new node(list.head.data);
            SinglyLinkedList.node newListNode = dupList.head;
            while (tmp != null) {
                SinglyLinkedList.node newnode = dupList.new node(tmp.data);
                newListNode.next = newnode;
                newListNode = newnode;
                tmp = tmp.next;
            }
            dupList.tail = newListNode;
        }
        return  dupList;
    }

    public void add(T data) {
        node newnode = new node(data);;
        if (head == null) {
            head = tail = newnode;
        } else {
            tail.next = newnode;
            tail = newnode;
        }
        count++;
    }

    private node findPrevNode(T data) {
        node tmp  = head;
        node prev = null;
        boolean found = false;
        while (tmp != null) {
            if (tmp.data == data) {
                found = true;
                break;
            }
            prev = tmp;
            tmp = tmp.next;

        }
        return  (found ? prev : null);
    }

    public  boolean isFound(T data) {
        node tmp  = head;
        boolean found = false;
        while (tmp != null) {
            if (tmp.data == data) {
                found = true;
                break;
            }
            tmp = tmp.next;
        }
        return found;
    }

    public boolean remove(T data) {
        boolean isRemoved = false;
        // Check if the data to be removed is at the head node.
        if ((head != null) && (head.data == data)) {
            // If this is the only node in tree update tail
            if (head == tail) {
                tail = tail.next;
            }
            head = head.next;
            isRemoved = true;
        } else {
            // Check if the data to be removed is at the tail node.
            node prevNode = findPrevNode(data);
            if (prevNode == tail) {
                tail = prevNode;
            } // Check if the data to be removed is in the middle nodes.
            else if (prevNode != null) {
                node currNode = prevNode.next;
                if (currNode != null) {
                    prevNode.next = currNode.next;
                } else {
                    prevNode = null;
                }
                isRemoved = true;
            }
        }
        if (isRemoved) {
            count--;
        }
        return isRemoved;
    }

    public void display() {
        node tmp = head;
        System.out.print("LinkedList:");
        while (tmp != null) {
            System.out.print(tmp.data);
            if (tmp.next != null) {
                System.out.print("->");
            }
            tmp = tmp.next;
        }
        System.out.println("");
    }

    private void reverseByLooping() {
        node current = head;
        node prev = null;
        tail = head;
        while (current != null) {
            node tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        head = prev;
    }

    private node reverseByRecursion(node current , node prev) {
        if (current != null) {
            reverseByRecursion(current.next, current).next = prev;
            return  prev;
        } else {
            head = prev;
            return prev;
        }
    }

    public void reverse(boolean isRecursive) {
        if (isRecursive) {
            tail = reverseByRecursion(head, null);
        } else {
            reverseByLooping();
        }
    }

}