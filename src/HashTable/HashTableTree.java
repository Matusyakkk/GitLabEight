package HashTable;

import Interfaces.ICollection;
import Tree.AVLTree;

public class HashTableTree<K extends Comparable<K>, V> implements ICollection<K, V> {
    AVLTree<K, V>[] table;
    int size;

    public HashTableTree(int size) {
        this.size = size;
        table = new AVLTree[size];

        for (int i = 0; i < size; i++)
            table[i] = new AVLTree<K, V>();
    }

    public int getHash(K key) {
        return key.hashCode() % size;
    }

    @Override
    public void put(K key, V value) {
        table[getHash(key)].put(key, value);
    }

    @Override
    public V get(K key) {
        return table[getHash(key)].get(key);
    }

    @Override
    public void remove(K key) {
        table[getHash(key)].remove(key);
    }

    @Override
    public void show() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            table[i].show();
            System.out.println();
        }
    }
}