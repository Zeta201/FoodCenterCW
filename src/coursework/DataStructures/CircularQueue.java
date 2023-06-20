/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursework.DataStructures;

import coursework.Exceptions.QueueEmptyException;
import java.io.Serializable;

/**
 *
 * @author Rect
 */
public class CircularQueue<T> implements Serializable {

    private class Node {

        T data;
        Node link;
    }
    Node front, rear;

    public void enQueue(T value) {
        Node temp = new Node();
        temp.data = value;
        if (this.front == null) {
            this.front = temp;
        } else {
            this.rear.link = temp;
        }

        this.rear = temp;
        this.rear.link = this.front;
    }

    public T deQueue() throws QueueEmptyException {
        if (this.front == null) {
            throw new QueueEmptyException();

        }

        T value;
        if (this.front == this.rear) {
            value = this.front.data;
            this.front = null;
            this.rear = null;
        } else {
            Node temp = this.front;
            value = temp.data;
            this.front = this.front.link;
            this.rear.link = this.front;
        }

        return value;
    }

    public boolean isEmpty() {
        return this.front == null;
    }

    public void displayQueue() {

        if (this.front == null) {
            System.out.println("Waiting Queue is currently empty");
            return;
        }
        Node temp = this.front;

        while (temp.link != this.front) {
            System.out.println(temp.data);
            temp = temp.link;
        }
        System.out.println(temp.data);
    }
}
