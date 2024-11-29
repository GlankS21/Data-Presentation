package QueueATD;
import Resources.*;
public interface IQueue {
    void MAKENULL(); // очищает очередь Q, делая ее пустой.
    Value FRONT(); // возвращает первый элемент (копия) очереди Q.
    Value DEQUEUE(); // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
    void ENQUEUE(Value x); // вставляет элемент x в конец очереди Q.
    boolean EMPTY(); // возвращает значение true, если очередь Q пустая, и значение false в противном случае.
    boolean FULL(); // возвращает значение true, если очередь Q полная, и значение false в противном случае.
}
