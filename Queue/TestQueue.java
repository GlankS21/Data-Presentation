package Queue;
import Queue.QueueRing.*;
public class TestQueue {
    public static void main(String[] args){
        String s = "abcdefghijklm";
        char[] symbols = s.toCharArray();
        Queue queue = new Queue();
        System.out.print("Заносим символы в стек: ");
        for (char c : symbols) {
            if (queue.FULL()) break;
            System.out.print(c);
            queue.ENQUEUE(c);
        }
        System.out.println();
        System.out.print("Вывесем на экран все символы: ");
        while (!queue.EMPTY()) {
            System.out.print(queue.DEQUEUE());
        }
    }
}
