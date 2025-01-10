package PriorityQueue;
import PriorityQueue.PriorityQueueArray.*;
public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10);
        queue.INSERT(new Priority(10));
        queue.INSERT(new Priority(9));
        queue.INSERT(new Priority(8));
        queue.INSERT(new Priority(7));
        queue.INSERT(new Priority(6));
        queue.INSERT(new Priority(5));
        queue.INSERT(new Priority(4));
        queue.INSERT(new Priority(3));
        queue.INSERT(new Priority(2));
        queue.INSERT(new Priority(1));
        queue.PRINT();

        System.out.println("Значение с минимальными приоритетами: " + queue.DELETEMIN());
        queue.PRINT();
        System.out.println("Значение с минимальными приоритетами: " + queue.DELETEMIN());
        queue.PRINT();
        System.out.println("Значение с минимальными приоритетами: " + queue.DELETEMIN());
        queue.PRINT();
    }
}