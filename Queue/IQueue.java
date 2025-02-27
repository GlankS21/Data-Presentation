package Queue;
import Resources.Value;
public interface IQueue {
    void MAKENULL(); // очищает очередь Q, делая ее пустой.
    char FRONT(); // возвращает первый элемент (копия) очереди Q.
    char DEQUEUE(); // удаляет первый элемент очереди Q, и возвращает его в качестве результатата.
    void ENQUEUE(char x); // вставляет элемент x в конец очереди Q.
    boolean EMPTY(); // возвращает значение true, если очередь Q пустая, и значение false в противном случае.
    boolean FULL(); // возвращает значение true, если очередь Q полная, и значение false в противном случае.
}
