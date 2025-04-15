package adt;

import java.util.Iterator;

public interface ListInterface<T extends Comparable<T>> extends Iterable<T> {
    void add(T newEntry);

    boolean update(int index, T newEntry);

    boolean remove(T anEntry);

    void clear();

    boolean contains(T anEntry);

    int getNumberOfEntries();

    boolean isEmpty();

    Iterator<T> iterator();

    ListInterface<T> union(ListInterface<T> otherList); 

    ListInterface<T> intersection(ListInterface<T> otherList); 

    ListInterface<T> difference(ListInterface<T> otherList); 

    int size();
      
    T get(int i);
}