package QueueATD;
import DoubleLinkedList.List;
import Resources.Value;

public class Queue implements IQueue{
    private List list;
    public Queue(){ list = new List();}
    @Override
    public void MAKENULL() {
        list.makeNull();
    }
    @Override
    public Value FRONT() { // возвращает первый элемент (копия) очереди Q.
        return new Value(list.retrieve(list.first()));
    }

    @Override
    public Value DEQUEUE() { // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
        Value value = list.retrieve(list.first());
        list.delete(list.first());
        return value;
    }
    @Override
    public void ENQUEUE(Value x) { // вставляет элемент x в конец очереди Q.
        list.insert(x, list.end());
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
