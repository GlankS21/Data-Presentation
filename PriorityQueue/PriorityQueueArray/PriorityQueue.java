package PriorityQueue.PriorityQueueArray;
import PriorityQueue.IPriorityQueue;
import PriorityQueue.PriorityQueueException;
import PriorityQueue.Priority;
public class PriorityQueue implements IPriorityQueue {
    Priority[] queue;
    int add; // Позиция, в которой добавим новое значение
    public PriorityQueue(int size){
        queue = new Priority[size];
        add = 0;
    }
    @Override
    public void INSERT(Priority p) {
        if(add >= queue.length) return;
        queue[add] = p; // Добавим новый элемент в позиции add
        shiftUp(add++); // Обеспечиваем правильный порядок, поднимая элемент вверх
    }
    @Override
    public int DELETEMIN() {
        if(add == 0) throw new PriorityQueueException("Очередь пустая");
        int result = queue[0].priority;
        swap(0, --add); // Перемещаем последний элемент на место удаляемого и уменьшаем размер очереди
        shiftDown(0); // Обеспечиваем правильный порядок, опуская элемент вниз
        return result;
    }
    @Override
    public void MAKENULL() {
        add = 0;
    }
    @Override
    public void PRINT() {
        if (add == 0) {
            System.out.println("Empty Queue");
            return;
        }
        System.out.println("Priority Queue:");
        int elements = 1;
        for (int i = 0; i < add; ) {
            for (int j = 0; j < elements && i < add; j++, i++) {
                System.out.print(queue[i].priority + " ");
            }
            System.out.println();
            elements *= 2;
        }
        System.out.println("----------------------------------------");
    }
    // Приватный метод для обмена двух элементов
    private void swap(int index1,int index2){
        Priority temp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }

    // перемещение элемента вверх по дереву, чтобы сохранить структуру кучи
    private void shiftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (queue[parent].priority > queue[index].priority) { // Если родительский элемент больше текущего, меняем их местами
                swap(parent, index);
                index = parent;
            } else break;
        }
    }
    // перемещение элемента вниз по дереву
    private void shiftDown(int index) {
        while (2 * index + 1 < add) {
            int left = 2 * index + 1; // Левый дочерний
            int right = 2 * index + 2; // Правый дочерний
            int j = left;
            if (right < add && queue[right].priority < queue[left].priority) { // Если правый элемент меньше левого
                j = right;
            }
            if (queue[index].priority <= queue[j].priority) break; // Если все в порядке => break;
            swap(index, j); // Меняем текущий элемент с минимальным из дочерних
            index = j;
        }
    }
}