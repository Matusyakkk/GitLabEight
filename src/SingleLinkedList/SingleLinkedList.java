package SingleLinkedList;

import Interfaces.LinkedList;

public class SingleLinkedList<E> implements LinkedList<E> {
    class Node<E> {
        private E data;
        private Node<E> nextNode;

        public Node(E data){
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }

    private Node<E> head;
    private Node<E> tail = null;

    @Override
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.setNextNode(head);
        head = newNode;
    }

    @Override
    public void addAt(int index, E data) {
        Node<E> new_Node = new Node<>(data);
        Node<E> node = head;

        for (int i = 0; i < index - 1; i++) node = node.getNextNode();
        new_Node.setNextNode(node.getNextNode());
        node.setNextNode(new_Node);
    }

    @Override
    public void add(E data) {
        Node<E> newNode = new Node<>(data);

        if (this.head == null) head = newNode;
        else {
            Node<E> currentNode = head;
            while (currentNode.getNextNode() != null) currentNode = currentNode.getNextNode();
            currentNode.setNextNode(newNode);
        }
    }

    @Override
    public void deleteFirst() {
        if (this.head != null) {
            this.head = this.head.getNextNode();
        }
    }

    @Override
    public void deleteAt(int index) {
        Node<E> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNextNode();
        }
        node.setNextNode(node.getNextNode().getNextNode());
    }

    @Override
    public void deleteLast() {
        if (head == null) {
            return;
        } else {
            if (head != tail) {
                int count = 0;
                Node<E> current = head;
                while (current.getNextNode() != tail) {
                    current = current.getNextNode();
                    count++;
                }
                deleteAt(count);
            } else {
                head = tail = null;
            }
        }
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.getNextNode()) {
                if (x.getData() == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.getNextNode()) {
                if (o.equals(x.getData()))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public void show() {
        Node<E> currentNode = head;
        if(currentNode == null){
            System.out.println("Linked list is empty");
        }
        else {
            while(currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                currentNode = currentNode.getNextNode();
            }
            System.out.println();
        }
    }
}
