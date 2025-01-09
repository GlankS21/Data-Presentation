package Queue.QueueArray;
import Queue.IQueue;
public class Queue implements IQueue {
    private char[] queue;
    private int front;
    private int rear;
    public Queue(){
        queue = new char[50];
        front = 0;
        rear = queue.length - 1;
    }
    public void MAKENULL(){
        front = 0;
        rear = queue.length - 1;
    }
    @Override
    public char FRONT() { return queue[front];}
    @Override
    public char DEQUEUE() {  // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
        char result = queue[front];
        front = QueueNextPosition(front);
        return result;
    }
    @Override
    public void ENQUEUE(char x) { // вставляет элемент x в конец очереди Q.
        rear = QueueNextPosition(rear);
        queue[rear] = x;
    }
    @Override
    public boolean EMPTY() {
        return front == QueueNextPosition(rear);
    }
    @Override
    public boolean FULL() {
        return front == QueueNextPosition(QueueNextPosition(rear));
    }
    private int QueueNextPosition(int position){
        return (position + 1) % queue.length;
    }
}
