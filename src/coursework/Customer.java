/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursework;

import java.io.Serializable;

/**
 *
 * @author Rect
 */
public class Customer implements Serializable {

    private String firstName;
    private String secondName;
    private int noOfBurgers;

    public Customer(String firstName, String secondName, int noOfBurgers) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.noOfBurgers = noOfBurgers;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public String getFullName() {
        return this.firstName + " " + this.secondName;
    }

    public int getNoOfBurgers() {
        return this.noOfBurgers;
    }
}
