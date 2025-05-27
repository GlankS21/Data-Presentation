package PriorityQueue;

public interface IPriorityQueue {
    void INSERT(Priority p); // Очеред вставить значение с приоритетом р
    int DELETEMIN(); // Возвращает значение с минимальным приоритетом, удаляя его из очереди
    void MAKENULL(); // Делает очередь пустой
    void PRINT(); // отображает очередь на экране
}
