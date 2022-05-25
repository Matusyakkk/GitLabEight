package HashTable;

import Interfaces.ICollection;
import SingleLinkedList.SingleLinkedList;
import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;


public class HashTableList<K, V> implements ICollection<K, V> {
    SingleLinkedList<K, V>[] table;
    int size;

    public HashTableList(int size) {
        this.size = size;
        table = new SingleLinkedList[size];

        for (int i = 0; i < size; i++)
            table[i] = new SingleLinkedList<K, V>();
    }

    public int getHash(@NotNull K key) {
        return key.hashCode()%size;
    }

    @Override
    public void put(K key, V value) {
        int hash = getHash(key);
        SingleLinkedList<K, V> obj = table[hash];
        obj.put(key, value);
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        SingleLinkedList<K, V> obj = table[hash];
        return obj.get(key);
    }

    @Override
    public void remove(K key) {
        int hash = getHash(key);
        SingleLinkedList<K, V> obj = table[hash];
        obj.remove(key);
    }

    @Override
    public void show() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            SingleLinkedList<K, V> list = table[i];
            list.show();
        }
    }
}