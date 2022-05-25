package Tree;

import Interfaces.ICollection;

public class AVLTree<K extends Comparable<K>, V>
        implements ICollection<K, V> {

    class Node<K, V> {

        private K key;
        private V value;
        int height;
        Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }

        public Node() {
            left = null;
            right = null;
            height = 0;
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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }
    }

    Node<K, V> root;

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null)
            return new Node<>(key, value);

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(put(node.getLeft(), key, value));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(put(node.getRight(), key, value));
        else
            return node;

        return rebalance(node, key);
    }

    @Override
    public V get(K key) {
        Node<K, V> temp = root;

        while (temp != null) {
            if (temp.getKey().equals(key))
                return temp.getValue();
            temp = temp.getKey().compareTo(key) < 0 ? temp.getRight() : temp.getLeft();
        }
        return null;
    }

    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null)
            return node;

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(remove(node.getLeft(), key));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(remove(node.getRight(), key));
        else {
            if ((node.getLeft() == null) || (node.getRight() == null)) {
                Node<K, V> temp = null;

                if (temp == node.getLeft())
                    temp = node.getRight();
                else
                    temp = node.getLeft();

                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                Node<K, V> temp = minKeyNode(node.getRight());
                node.setKey(temp.getKey());
                node.setValue(temp.getValue());
                node.setRight(remove(node.getRight(), temp.getKey()));
            }
        }
        return rebalance(node, key);
    }

    @Override
    public void show() {
        show(root);
    }

    private void show(Node<K, V> node) {
        if (node != null) {
            System.out.print(node.getKey() + " == " + node.getValue() + "; ");
            show(node.getLeft());
            show(node.getRight());
        }
    }

    //Utils
    int height(Node<K, V> N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }

    int max(int l, int r) {
        return Math.max(l, r);
    }

    Node<K, V> rightRotate(Node<K, V> y) {
        Node<K, V> x = y.getLeft();
        Node<K, V> T2 = x.getRight();

        // perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // update heights
        y.setHeight(max(y.getLeft().getHeight(), y.getRight().getHeight()) + 1);
        x.setHeight(max(x.getLeft().getHeight(), x.getRight().getHeight()) + 1);

        return x;
    }

    Node<K, V> leftRotate(Node<K, V> x) {
        Node<K, V> y = x.getRight();
        Node<K, V> T2 = y.getLeft();

        // perform rotation
        y.setLeft(x);
        x.setRight(T2);

        // update heights
        x.setHeight(max(x.getLeft().getHeight(), x.getRight().getHeight()) + 1);
        y.setHeight(max(y.getLeft().getHeight(), y.getRight().getHeight()) + 1);

        return y;
    }

    int getBalance(Node<K, V> N) {
        if (N == null)
            return 0;
        else if (N.getLeft() == null)
            return N.getRight().getHeight();
        else if (N.getRight() == null)
            return N.getLeft().getHeight();
        else
            return N.getLeft().getHeight() - N.getRight().getHeight();
    }

    void updateHeight(Node<K, V> node) {
        if (node.getLeft() == null)
            node.setHeight(1 + node.getRight().getHeight());
         else if (node.getRight() == null)
            node.setHeight(1 + node.getLeft().getHeight());
         else
            node.setHeight(1 + max(node.getLeft().getHeight(), node.getRight().getHeight()));
    }

    Node<K, V> rebalance(Node<K, V> node, K key) {
        updateHeight(node);

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareTo(node.getLeft().getKey()) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.getLeft().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    Node<K, V> minKeyNode(Node<K, V> node) {
        Node<K, V> temp = node;

        while (temp.getLeft() != null)
            temp = temp.getLeft();

        return temp;
    }
}
