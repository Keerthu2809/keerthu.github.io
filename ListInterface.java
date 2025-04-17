/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author ROG G14
 */
public interface ListInterface<T> {

    /** Task: Adds a new entry to the end of the list. */
    boolean add(T newEntry);

    /** Task: Adds a new entry at a specified position within the list. */
    boolean add(int newPosition, T newEntry);

    /** Task: Removes the entry at a given position from the list. */
    T remove(int givenPosition);

    /** Task: Removes all entries from the list. */
    void clear();

    /** Task: Replaces the entry at a given position in the list. */
    boolean replace(int givenPosition, T newEntry);

    /** Task: Retrieves the entry at a given position in the list. */
    T getEntry(int givenPosition);

    /** Task: Sees whether the list contains a given entry. */
    boolean contains(T anEntry);

    /** Task: Gets the number of entries in the list. */
    int getNumberOfEntries();

    /** Task: Sees whether the list is empty. */
    boolean isEmpty();

    /** Task: Sees whether the list is full. */
    boolean isFull();
}