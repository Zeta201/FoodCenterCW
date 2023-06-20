/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursework;

import coursework.Exceptions.QueueEmptyException;
import coursework.Exceptions.QueueFullException;
import coursework.Exceptions.QueueIndexOutOfBoundsException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rect
 */
public class FoodQueue implements Serializable {

    private LinkedList<Customer> queue = new LinkedList<>();
    private int size;
    private int queueId;

    public FoodQueue(int size, int queueId) {
        this.size = size;
        this.queueId = queueId;

    }

    public boolean isFull() {
        return queue.size() == this.size;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return this.size;
    }

    public int getNoOfElements() {
        return this.queue.size();
    }

    public LinkedList getQueue() {
        return this.queue;
    }

    public int getId() {
        return this.queueId;
    }

    public void addCustomer(Customer cus) throws QueueFullException {

        if (isFull()) {
            throw new QueueFullException(queueId);
        }
        queue.add(cus);
    }

    public Customer removeCustomer() throws QueueEmptyException {

        if (isEmpty()) {
            throw new QueueEmptyException(queueId);
        }
        Customer cus = queue.remove();
        System.out.println(cus.getFullName() + " removed.");
        return cus;

    }

    public Customer removeCustomer(int loc) throws QueueIndexOutOfBoundsException, QueueEmptyException {

        if (isEmpty()) {
            throw new QueueEmptyException(queueId);
        }
        if (loc > queue.size() - 1) {
            throw new QueueIndexOutOfBoundsException();
        }
        Customer cus = queue.remove(loc);
        System.out.println(cus.getFullName() + " removed.");
        return cus;
    }

    public void viewQueue() {
        int i = 0;
        for (; i < queue.size(); i++) {
            System.out.println("X");
        }
        for (int j = i; j < this.size; j++) {
            System.out.println("O");
        }
    }

    public float getIncome() {
        float tot = 0f;
        for (Customer c : queue) {
            tot += c.getNoOfBurgers() * FoodCenter.BURGER_PRICE;
        }
        return tot;
    }

}
