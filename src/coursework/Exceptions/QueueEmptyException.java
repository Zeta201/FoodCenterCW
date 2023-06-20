/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursework.Exceptions;

/**
 *
 * @author Rect
 */
public class QueueEmptyException extends Exception {

    private int queueNo;

    public QueueEmptyException() {

    }

    public QueueEmptyException(int queueNo) {
        this.queueNo = queueNo;
    }

    public String toString() {
        return "This queue " + queueNo + " is currently empty.";
    }
}
