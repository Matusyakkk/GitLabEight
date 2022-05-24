package Interfaces;

public interface
LinkedList<E>{
    void add(E data);
    void addFirst(E data);
    void addAt(int index, E data);
    void deleteFirst();
    void deleteAt(int index);
    void deleteLast();
    int indexOf(Object o);
    void show();
}