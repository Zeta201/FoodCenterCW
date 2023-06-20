/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursework.Exceptions;

/**
 *
 * @author Rect
 */
public class QueueFullException extends Exception {

    private int queueNo;

    public QueueFullException() {

    }

    public QueueFullException(int queueNo) {
        this.queueNo = queueNo;
    }

    public String toString() {
        return "The queue " + queueNo + " is currently fully occupied. Customer added to the waiting queue";
    }

}
