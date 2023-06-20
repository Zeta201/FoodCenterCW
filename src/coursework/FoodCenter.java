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

    private FoodQueue cusQueues[] = new FoodQueue[3];
    private final int queueSizes[] = {2, 3, 5};
    public static final int NO_QUEUES = 3;
    public final int INIT_BURGERS = 50;
    public static final float BURGER_PRICE = 650f;
    private int currNoOfBurgers;

    public FoodCenter() {
        cusQueues[0] = new FoodQueue(2, 1);
        cusQueues[1] = new FoodQueue(3, 2);
        cusQueues[2] = new FoodQueue(5, 3);
        this.currNoOfBurgers = INIT_BURGERS;
    }

    public void addCustomer(Customer cus) {

        try {

            FoodQueue min = cusQueues[0];
            float maxEmptySizeRatio = (float) (cusQueues[0].getSize() - cusQueues[0].getNoOfElements()) / cusQueues[0].getSize();
            for (int i = 1; i < NO_QUEUES; i++) {

                float emptySizeRatio = (float) (cusQueues[i].getSize() - cusQueues[i].getNoOfElements()) / cusQueues[i].getSize();
                if (emptySizeRatio > maxEmptySizeRatio) {
                    maxEmptySizeRatio = emptySizeRatio;
                    min = cusQueues[i];
                }
            }

            if (min.isFull()) {
                throw new QueueFullException(min.getId());
            }
            if (currNoOfBurgers < cus.getNoOfBurgers()) {
                throw new InsufficientBurgersException();
            }

            min.addCustomer(cus);
            currNoOfBurgers -= cus.getNoOfBurgers();
            System.out.println(cus.getFullName() + " successfully added.");
            if (currNoOfBurgers <= 10) {
                throw new LowBurgerException();
            }
        } catch (QueueFullException | InsufficientBurgersException | LowBurgerException ex) {
            System.out.println(ex);
        }
    }

    public Customer removeCustomer(int queueNo) {

        try {

            Customer name = cusQueues[queueNo - 1].removeCustomer();

            return name;
        } catch (QueueEmptyException ex) {
            System.out.println(ex);
            return null;
        }

    }

    public Customer removeCustomer(int queueNo, int loc) {

        try {

            Customer cus = cusQueues[queueNo - 1].removeCustomer(loc);

            return cus;
        } catch (QueueEmptyException | QueueIndexOutOfBoundsException ex) {
            System.out.println(ex);
            return null;
        }

    }

    public void viewQueues() {

        System.out.println("**************************");
        System.out.println("*\tCashiers\t*");
        System.out.println("**************************");
        System.out.println("");

        for (int k = 0; k < NO_QUEUES; k++) {
            System.out.println("Queue " + (k + 1));
            cusQueues[k].viewQueue();
        }
    }

    public void displaySortedQueue() {

        Customer temp;
        ArrayList<Customer> tempArr = new ArrayList<>();
        for (int k = 0; k < NO_QUEUES; k++) {
            tempArr.addAll(cusQueues[k].getQueue());
        }
        System.out.println("\tAll customers in dictionary order");
        System.out.println("");
        for (int i = 0; i < tempArr.size(); i++) {
            for (int j = 1; j < (tempArr.size() - i); j++) {
                if (tempArr.get(j - 1).getFullName().compareTo(tempArr.get(j).getFullName()) > 0) {
                    //swap elements

                    temp = tempArr.get(j - 1);
                    tempArr.set(j - 1, tempArr.get(j));
                    tempArr.set(j, temp);
                }

            }
        }
        for (Customer cus : tempArr) {
            System.out.println(cus.getFullName());
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

    public void displayIncome(int queueNo) {
        System.out.printf("Income of %d: %.2f\n", queueNo, cusQueues[queueNo - 1].getIncome());
    }

}
