package Queue.QueueRing;
import Queue.IQueue;
public class Queue implements IQueue {
    private class Item{
        char value;
        Item next;
        public Item(char value, Item next){
            this.value = value;
            this.next = next;
        }
    }
    private Item rear;
    public Queue(){
        rear = null;
    }
    @Override
    public void MAKENULL() {
        rear = null;
    }
    @Override
    public char FRONT() {
        return rear.next.value;
    }
    @Override
    public char DEQUEUE() {  // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
        char result = rear.next.value;
        if(rear == rear.next){ // Если только 1 элемент
            rear = null;
            return result;
        }
        rear.next = rear.next.next;
        return result;
    }
    @Override
    public void ENQUEUE(char x) { // вставляет элемент x в конец очереди Q.
        if (rear == null) { // Если массив пустой
            rear = new Item(x, null);
            rear.next = rear;
            return;
        }
        // Остальные случаи
        rear.next = new Item(x, rear.next);
        rear = rear.next;
    }
    @Override
    public boolean EMPTY() {
        return rear == null;
    }
    @Override
    public boolean FULL() {
        return false;
    }
}
