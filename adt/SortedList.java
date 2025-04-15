package adt;

import java.util.Iterator;

public class SortedList<T extends Comparable<T>> implements ListInterface<T> {

    private Node head;
    private int size;

    public SortedList() {
        this.head = null;
        this.size = 0;
    }

    public SortedList(ListInterface<T> otherList) {
        this();
        if (otherList != null) {
            Iterator<T> iterator = otherList.iterator();
            while (iterator.hasNext()) {
                this.add(iterator.next());
            }
        }
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (head == null || head.data.compareTo(newEntry) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data.compareTo(newEntry) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public boolean remove(T anEntry) {
        if (head == null) {
            return false; // Empty list
        }

        if (head.data.equals(anEntry)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(anEntry)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // Element not found
    }

    // Method to update an entry at a specific index
    public boolean update(int index, T newEntry) {
        if (index < 0 || index >= size) {
            return false; // Index is out of bounds
        }

        Node current = head; // Use the private Node class
        for (int i = 0; i < index; i++) {
            current = current.next; // Traverse to the desired index
        }

        // Update the data at the current node
        current.data = newEntry;
        return true; // Update was successful
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(T anEntry) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(anEntry)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int getNumberOfEntries() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public ListInterface<T> union(ListInterface<T> otherList) {
        SortedList<T> result = new SortedList<>();

        Node current = this.head;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }

        Node otherCurrent = ((SortedList<T>) otherList).head;
        while (otherCurrent != null) {
            if (!result.contains(otherCurrent.data)) {
                result.add(otherCurrent.data);
            }
            otherCurrent = otherCurrent.next;
        }

        return result;
    }

    @Override
    public ListInterface<T> intersection(ListInterface<T> otherList) {
        SortedList<T> result = new SortedList<>();

        Node current = this.head;
        while (current != null) {
            if (otherList.contains(current.data)) {
                result.add(current.data);
            }
            current = current.next;
        }

        return result;
    }

    @Override
    public ListInterface<T> difference(ListInterface<T> otherList) {
        SortedList<T> result = new SortedList<>();

        Node current = this.head;
        while (current != null) {
            if (!otherList.contains(current.data)) {
                result.add(current.data);
            }
            current = current.next;
        }

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;
            private boolean endReached = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    endReached = true;
                    return null; // Indicates end of the list
                }
                T data = current.data;
                current = current.next;
                return data;
            }

            // Helper method to convert to array for iteration
            public Object[] toArray() {
                Object[] array = new Object[size];
                int i = 0;
                Node current = head;
                while (current != null) {
                    array[i++] = current.data;
                    current = current.next;
                }
                return array;
            }

            public boolean isEndReached() {
                return endReached;
            }
        };
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        Node current = head;
        for (int index = 0; index < i; index++) {
            current = current.next;
        }
        return current.data; // Return the element at the specified index
    }

    @Override
    public int size() {
        return size;
    }

    //forEach()
    public void forEach(java.util.function.Consumer<? super T> action) {
        Node current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    // === Node Class without Generics ===
    private class Node {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
