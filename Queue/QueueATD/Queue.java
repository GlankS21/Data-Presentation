package Queue.QueueATD;
import List.DoubleLinkedList.List;
import Resources.Value;
import Queue.IQueue;
public class Queue implements IQueue{
    private List list;
    public Queue(){ list = new List();}
    @Override
    public void MAKENULL() {
        list.makeNull();
    }
    @Override
    public char FRONT() { // возвращает первый элемент (копия) очереди Q.
        return list.retrieve(list.first()).getChar();
    }
    @Override
    public char DEQUEUE() { // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
        Value value = list.retrieve(list.first());
        list.delete(list.first());
        return value.getChar();
    }
    @Override
    public void ENQUEUE(char x) { // вставляет элемент x в конец очереди Q.
        list.insert(new Value(x), list.end());
    }
    @Override
    public boolean EMPTY() {
        return list.first().item == null;
    }
    @Override
    public boolean FULL() {
        return false;
    }
}
