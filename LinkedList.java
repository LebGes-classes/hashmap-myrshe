package javaHashMap;

public class LinkedList {
    public HashMap.Node head;
    public HashMap.Node tail;
    public int hash;

    LinkedList () {
        ;
    }
    public void add(HashMap.Node node) {
        if (head == null) {
            head = node;
        } else {
            head.next = node;
            head = node;
        }
    }
}
