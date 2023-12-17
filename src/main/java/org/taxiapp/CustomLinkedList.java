package org.taxiapp;
import java.util.ArrayList;
public class CustomLinkedList <T> {


    private class Node {
        public T data; // what info is stored at each node
        public double key; // acts as a index to compare node postions
        public Node next; // node behind current


        public Node(double distance, T taxi) {
            // initialises a node that is detached from the list for inserting and removing
            data = taxi;
            key = distance;
            next = null;
        }
        public Node (T x){
            data = x;
            next = null;
        }

    }

    Node head;
    int size;
    int maxValue;

    public CustomLinkedList() {
        head = null;
        size = 0;
        maxValue = 5;
    }

    public boolean isEmpty() {
        return head == null;
    }
    public boolean isFull(){
        return size > maxValue;
    }


    public void simpleInsert(T x){
        Node insertNode = new Node (x);
        if (!isEmpty()){
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = insertNode;
        } else {
            head = insertNode;
        }
    }
    public boolean contains(T x){
        if (isEmpty()){
            return false;
        } else{
            Node current = head;
            while (current != null){
                if (current.data.equals(x)){
                    return true;
                } else {
                    current = current.next;
                }
            }
        } return false;
    }
    public void sortInsert(double distance, T taxi) {
        // instance of the node to be inserted
        Node insertNode = new Node(distance, taxi);
        // checks to see if the node should be inserted at the end or not
        boolean isInserted = false;

        // if the list is empty, make it the first
        if (isEmpty()) {
            head = insertNode;
            isInserted = true;
            size++;
        }
        else if (!isFull()){
        // if new node's distance is less than the head, make it the ditst
        if (insertNode.key < head.key) {
            insertNode.next = head; // make the next of the new node the head
            head = insertNode; // make the new node the head
            isInserted = true;
            size++;
        } else {
            // start from the start of the list
            Node current = head;

            // while yout not at the end, and the new node is not smaller than the current
            while (current.next != null && insertNode.key > current.next.key) {
                // move to the next
                current = current.next;
            }
            // if it is less than the current, insert it
            insertNode.next = current.next; // make the new nodes next value be th next value of current
            current.next = insertNode; // makes the current next the insert
            isInserted = true;
            size++;
        }

        // If the node hasn't been inserted yet, it means it should go at the end
        if (!isInserted) {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = insertNode;
            size++;
        }
    }}
    public void printList(){
        Node current = head;
        if (isEmpty()){
            System.out.println("The list is empty");
        } else {
            int i = 1;
            while (current!= null){
                System.out.println("[" + i + "]");
                Taxi taxi = (Taxi) current.data;
                System.out.println(taxi.displayInformation());
                current = current.next;
                i++;
            }

        }
    }


    public T getChosenTaxi(int userChoice){
        Node current = head;
        if (!isEmpty()){
            int i = 0;
            while (current.next != null && i < userChoice){
                current = current.next;
            }
        }
        return current.data;

    }

    public ArrayList<Taxi> arrayOfTaxis() {
        // purely for testing purposes
        ArrayList<Taxi> arrayofTaxis = new ArrayList<>();
        Node current = head;
        while (current != null) {
            Taxi taxi = (Taxi) current.data;
            arrayofTaxis.add(taxi);
            current = current.next;

        }
        return  arrayofTaxis;
    }
}
