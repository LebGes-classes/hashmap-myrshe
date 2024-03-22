package javaHashMap;
import java.util.ArrayList;

public class HashMap<K, V> implements Map<K, V> {
    public class Node {
        int hash;
        K key;
        V value;
        Node next;
        Node previous;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = this.hashCode();
        }
    }

    public ArrayList<LinkedList> array;

    HashMap () {;}

    public int hashCode(V value) {
        String str = value.toString();
        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            int ascii = (int) str.charAt(i);
            hash += ascii;
        }
        return hash;
    }
    @Override
    public void put(K key, V value) {
        Node node = new Node(key, value);
        if (!containsKey(key)) {
            int hashcode = hashCode(value);
            boolean flag = false;

            for (int i = 0; i < this.array.size(); i ++) {
                if (this.array.get(i).hash == hashcode ) {
                    this.array.get(i).addNode(node);
                    flag = true;
                }
            }
            if (!flag) {
                LinkedList linked_node = new LinkedList();
                linked_node.hash = hashcode;
                int arraySize = this.array.size();
                this.array.get(arraySize).addNode(node);
            }
        } else {
            System.out.println("Значение с таким ключом уже присутствует в HashMap");
        }
    }

    @Override
    public V get(K key) {
        Node current = this.array.get(0).tail; //начинаем исктать с первого элемента в нклевом листе
        int counterOfLists = 0;
        while (current.key != key) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tail;
            }
            current = current.next;
        }
        return current.value;
    }

    @Override
    public boolean containsKey(K key) {

        Node current = this.array.get(0).tail;
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < this.size(); i++) {
            if (current == this.array.get(count).head) {
                count++;
                current = this.array.get(count).tail;
            }
            if (current.key == key) {
                flag = true;
                break;
            }
            current = current.next;
        }
        return flag;
    }

    @Override
    public boolean containsValue(V value) {
        Node current = this.array.get(0).tail; //начинаем исктать с первого элемента в нулевом листе
        int counterOfLists = 0;
        boolean flag = false;

        for (int i = 0; i < this.size(); i++) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tail;
            }
            if (current.value == value) { //если нашли необходимое значение, выхлдим из цикла
                flag = true;
                break;
            }
            current = current.next;
        }
        return flag;
    }

    @Override
    public void remove(K key) {
        Node current = this.array.get(0).tail; //начинаем исктать с первого элемента в нклевом листе
        int counterOfLists = 0;
        while (current.key != key) {
            if (current == this.array.get(counterOfLists).head) { //если мы дошли до головы листа в первом элементе, переопределяем хвост
                counterOfLists++;
                current = this.array.get(counterOfLists).tail;
            }
            current = current.next;
        }

        (current.previous).next = current.next; //переопределяем указатели последующего и предыдущего элементов
        (current.next).previous = current.previous;

        current.previous = null;
        current.next = null;
    }

    @Override
    public int size() {
        int counterOfNodes = 0; //счетчик эдементов коллекции
        Node current;

        for (int i = 0; i < this.array.size(); i++) { //проходимся по каждому листу
            current = this.array.get(i).tail; //перед каждым прохождением по листу, переопределяем текущий элемент
            while (current != this.array.get(i).head) { //пока не дойдем до головы списка
                counterOfNodes ++;
                current = current.next;
            }
        }
        return counterOfNodes;
    }
}
