package QueueArray;

public class Queue implements IQueue{
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
    public char FRONT() {
        return queue[QueuePosition(front)];
    }
    @Override
    public char DEQUEUE() {  // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
        return queue[QueuePosition(front++)];
    }
    @Override
    public void ENQUEUE(char x) { // вставляет элемент x в конец очереди Q.
        queue[QueuePosition(++rear)] = x;
    }
    @Override
    public boolean EMPTY() {
        return front == QueuePosition(rear);
    }
    @Override
    public boolean FULL() {
        return front == QueuePosition(QueuePosition(rear));
    }
    private int QueuePosition(int position){
        position++;
        return position % (queue.length - 1);
    }
}
