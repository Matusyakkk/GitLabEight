package SingleLinkedList;

import Interfaces.ICollection;

public class SingleLinkedList<K, V> implements ICollection<K, V> {
    class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            next = null;
        }

        public Node(){
            next = null;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

    private Node<K, V> head;
    private Node<K, V> tail = null;

    @Override
    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        newNode.setNext(head);
        head = newNode;
    }

    @Override
    public V get(K key) {
        V value = null;

        for (Node<K, V> temp = head; temp != null; temp = temp.getNext())
            if (temp.getKey().equals(key))
                return temp.getValue();
        return value;
    }

    @Override
    public void remove(K key) {
        if (head == null) {
            return;
        } else {
            if (head.getNext() != null) {
                Node<K, V> prev = null;
                while (head != null) {
                    if (head.getKey().equals(key))
                        break;
                    prev = head;
                    head = head.next;
                }
                prev.setNext(head.getNext());
            } else {
                head = tail = null;
            }
        }
    }

    @Override
    public void show() {
        Node<K, V> currentNode = head;
        if(currentNode == null){
            System.out.println("Linked list is empty");
        }
        else {
            while(currentNode != null) {
                System.out.print(currentNode.getValue() + " ");
                currentNode = currentNode.getNext();
            }
            System.out.println();
        }
    }
}
