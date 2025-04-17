/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Keerthu_2809
 */
public class Node<T> {
    T data; // Data stored in the node
    Node<T> next; // Pointer to the next node

    public Node(T data) {
        this.data = data;
        this.next = null; // Initially, the next node is null
    }
}
