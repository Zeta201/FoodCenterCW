/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursework;

import coursework.Exceptions.InsufficientBurgersException;
import coursework.Exceptions.LowBurgerException;
import coursework.Exceptions.QueueEmptyException;
import coursework.Exceptions.QueueFullException;
import coursework.Exceptions.QueueIndexOutOfBoundsException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Rect
 */
public class FoodCenter implements Serializable {

    private ArrayList<String> cusQueues[] = new ArrayList[3];
    public static final int NO_QUEUES = 3;
    private final int queueSizes[] = {2, 3, 5};
    public final int INIT_BURGERS = 50;
    public final int ORDER_SIZE = 5;
    private int currNoOfBurgers;

    public FoodCenter() {
        cusQueues[0] = new ArrayList<>(2);
        cusQueues[1] = new ArrayList<>(3);
        cusQueues[2] = new ArrayList<>(5);
        this.currNoOfBurgers = INIT_BURGERS;
    }

    public void addCustomer(int queueNo, String name) {

        try {

            if (isFull(queueNo - 1)) {
                throw new QueueFullException(queueNo);
            }
            if (currNoOfBurgers < 5) {
                throw new InsufficientBurgersException();
            }
            cusQueues[queueNo - 1].add(name);
            currNoOfBurgers -= ORDER_SIZE;
            System.out.println(name + " successfully added.");
            if (currNoOfBurgers <= 10) {
                throw new LowBurgerException();
            }
        } catch (QueueFullException | InsufficientBurgersException | LowBurgerException ex) {
            System.out.println(ex);
        }
    }

    public String removeCustomer(int queueNo) {

        try {
            if (isEmpty(queueNo - 1)) {
                throw new QueueEmptyException(queueNo);
            }
            String name = cusQueues[queueNo - 1].remove(0);
            System.out.println(name + " removed.");

            return name;
        } catch (QueueEmptyException ex) {
            System.out.println(ex);
            return "";
        }

    }

    public String removeCustomer(int queueNo, int loc) {

        try {
            if (isEmpty(queueNo - 1)) {
                throw new QueueEmptyException(queueNo);
            }

            if (loc > cusQueues[queueNo - 1].size() - 1) {
                throw new QueueIndexOutOfBoundsException();
            }
            String name = cusQueues[queueNo - 1].remove(loc);
            System.out.println(name + " removed!");
            return name;
        } catch (QueueEmptyException | QueueIndexOutOfBoundsException ex) {
            System.out.println(ex);
            return "";
        }

    }
//Expects zero based queue No

    public boolean isEmpty(int queueNo) {
        return cusQueues[queueNo].isEmpty();
    }
//Expects zero based queue No

    public boolean isFull(int queueNo) {
        return cusQueues[queueNo].size() == queueSizes[queueNo];
    }

    public void viewQueues() {

        System.out.println("**************************");
        System.out.println("*\tCashiers\t*");
        System.out.println("**************************");
        System.out.println("");

        for (int k = 0; k < NO_QUEUES; k++) {
            System.out.println("Queue " + (k + 1));
            int i = 0;

            for (; i < cusQueues[k].size(); i++) {
                System.out.println("X");
            }
            for (int j = i; j < this.queueSizes[k]; j++) {
                System.out.println("O");
            }
        }

    }

    public void displaySortedQueue() {

        String temp;
        ArrayList<String> tempArr = new ArrayList<>(cusQueues[0]);
        tempArr.addAll(cusQueues[1]);
        tempArr.addAll(cusQueues[2]);
        System.out.println("\tAll customers in dictionary order");
        System.out.println("");
        for (int i = 0; i < tempArr.size(); i++) {
            for (int j = 1; j < (tempArr.size() - i); j++) {
                if (tempArr.get(j - 1).compareTo(tempArr.get(j)) > 0) {
                    //swap elements

                    temp = tempArr.get(j - 1);
                    tempArr.set(j - 1, tempArr.get(j));
                    tempArr.set(j, temp);
                }

            }
        }
        for (String str : tempArr) {
            System.out.println(str);
        }
    }

    public void viewEmptyQueues() {
        System.out.println("Currently Empty Queues");
        for (int i = 0; i < NO_QUEUES; i++) {
            if (cusQueues[i].isEmpty()) {
                System.out.println("Queue " + (i + 1));
            }
        }
    }

    public int getCurrBurgers() {
        return this.currNoOfBurgers;
    }

    public void addBurgers(int qty) {
        currNoOfBurgers += qty;
        System.out.println("Stock updated successfully!");
    }

}
