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

    SinglyLinkedList() {
        head = tail = null;
    }

    public void add(T data) {
        node newnode = new node(data);;
        if (head == null) {
            head = tail = newnode;
        } else {
            tail.next = newnode;
        }
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
        if ((head != null) && (head.data == data)) {
            head = head.next;
            isRemoved = true;
        } else {
            node prevNode = findPrevNode(data);
            if (prevNode != null) {
                node currNode = prevNode.next;
                if (currNode != null) {
                    prevNode.next = currNode.next;
                } else {
                    prevNode = null;
                }
                isRemoved = true;
            }
        }
        return isRemoved;
    }

}