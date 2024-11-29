package QueueCursor;
public class Queue implements IQueue {
    private class Item{
        char value;
        Item next;
        public Item(char value, Item next){
            this.value = value;
            this.next = next;
        }
        public void setNext(char value){
            this.value = value;
        }
    }
    private Item head;
    public Queue(){
        head = null;
    }
    @Override
    public void MAKENULL() {
        head = null;
    }
    @Override
    public char FRONT() {
        return head.value;
    }
    @Override
    public char DEQUEUE() {  // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
        char result = head.value;
        head = head.next;
        return result;
    }
    @Override
    public void ENQUEUE(char x) { // вставляет элемент x в конец очереди Q.
        Item current = head;
        while(current.next != null){
            current = current.next;
        }
        current.setNext(x);
    }
    @Override
    public boolean EMPTY() {
        return head == null;
    }
    @Override
    public boolean FULL() {
        return false;
    }
}
