/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package coursework;

import coursework.Exceptions.QueueOutOfBoundsException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Rect
 */
public class CourseWork {

    public static void main(String[] args) {
        FoodCenter fc = new FoodCenter();
//        fc.addCustomer(1, "Dinitha");
//        fc.addCustomer(1, "Shamy");
////        fc.addCustomer(1, "Pawan");
////        fc.addCustomer(1,"Dinitha");
//        fc.addCustomer(2, "Amila");
//        fc.addCustomer(2, "Anananda");
//        fc.addCustomer(2, "Zebra");
//
////        fc.viewQueues();
//        fc.displaySortedQueue();
        Scanner sc = new Scanner(System.in);
        String option = "";

        do {

            try {
                System.out.println("\t======Foodies Fave Food center======");
                System.out.println("100 or VFQ: View all Queues");
                System.out.println("101 or VEQ: View all Empty Queues");
                System.out.println("102 or ACQ: Add customer to a Queue");
                System.out.println("103 or RCQ: Remove a customer from a queue");
                System.out.println("104 or PFQ: Remove a served customer");
                System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
                System.out.println("106 or SPD: Store Program Data into file.");
                System.out.println("107 or LPD: Load Program Data from file");
                System.out.println("108 or STK: View remaining burgers stock");
                System.out.println("109 or AFS: Add burgers to Stock");
                System.out.println("110 or IFQ: Calculate Income");

                System.out.println("999 or EXT: Exit the Program");
                System.out.println("\t------------------------------------");
                System.out.println("Enter the operation: ");
                option = sc.nextLine();

                switch (option) {
                    case "100":
                    case "VFQ":
                        fc.viewQueues();
                        break;
                    case "101":
                    case "VEQ":
                        fc.viewEmptyQueues();
                        break;
                    case "102":
                    case "ACQ":

                        System.out.println("Enter customer full name: ");

                        String name = sc.nextLine();
                        String nameArr[] = name.split(" ");

                        System.out.println("Enter customer order size: ");
                        int orderSize = sc.nextInt();
                        fc.addCustomer(new Customer(nameArr[0], nameArr[1], orderSize));
                        sc.nextLine();
                        break;
                    case "103":
                    case "RCQ":
                        System.out.println("Enter queue number: ");
                        int queueNo = sc.nextInt();
                        if (queueNo != 1 && queueNo != 2 && queueNo != 3) {
                            throw new QueueOutOfBoundsException();
                        }
                        sc.nextLine();
                        System.out.println("Enter location: ");
                        int loc = sc.nextInt();

                        fc.removeCustomer(queueNo, loc);
                        sc.nextLine();
                        break;
                    case "104":
                    case "PCQ":
                        System.out.println("Enter queue number: ");
                        queueNo = sc.nextInt();
                        if (queueNo != 1 && queueNo != 2 && queueNo != 3) {
                            throw new QueueOutOfBoundsException();
                        }

                        fc.removeCustomer(queueNo);
                        sc.nextLine();
                        break;
                    case "105":
                    case "VCS":
                        fc.displaySortedQueue();
                        break;
                    case "106":
                    case "SPD":
                        try {
                        FileOutputStream fos = new FileOutputStream("state.txt");
                        ObjectOutputStream out = new ObjectOutputStream(fos);
                        out.writeObject(fc);
                        out.flush();
                        out.close();
                        System.out.println("Data saved successfully");

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    break;
                    case "107":
                    case "LPD":
                       try {

                        ObjectInputStream in = new ObjectInputStream(new FileInputStream("state.txt"));
                        fc = (FoodCenter) in.readObject();
                        in.close();
                        System.out.println("Data loaded successfully");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    break;

                    case "108":
                    case "STK":
                        System.out.println("\tRemaining burgers in stock");
                        System.out.println(fc.getCurrBurgers() + " burgers");
                        break;
                    case "109":
                    case "AFS":
                        System.out.println("Enter quantity: ");
                        int qty = sc.nextInt();
                        fc.addBurgers(qty);
                        sc.nextLine();
                        System.out.println("\tRemaining burgers in stock");
                        System.out.println(fc.getCurrBurgers() + " burgers");
                        break;
                    case "110":
                    case "IFQ":
                        System.out.println("Enter queue number: ");
                        queueNo = sc.nextInt();
                        fc.displayIncome(queueNo);
                        sc.nextLine();
                        break;
                    case "999":
                    case "EXT":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid operation code!");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!");
                sc.nextLine();
            } catch (QueueOutOfBoundsException ex) {
                System.out.println(ex);
                sc.nextLine();
            } catch (Exception ex) {
                System.out.println(ex);

            }

        } while (!option.equals(
                "999") && !option.equals("EXT"));

    }
}
