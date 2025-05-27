package Poset;

import javax.swing.*;

public class PoSet {
    private static class Obj extends Child {
        public int value; // Значение вершины
        public int quantity; // Количество входящих рёбер (in-degree)
        public Obj(int value, Obj next) {
            super(next, null);
            this.value = value;
            this.quantity = 0;
        }

    }
    private static class Child{
        Obj next;   // Ссылка на следующую вершину
        Child child;  // Список потомков данной вершины
        public Child(Obj next, Child child) {
            this.next = next;
            this.child = child;
        }
    }
    private Obj head;

    public PoSet() {head = null;}

    /**
     *  1. fromObj = null, toObj = null, current = head
     *  2. Поиск вершин с заданными значениями
     *     Если найдена вершина с значением => сохраним вершину
     *  3. Если вершина from/to отсутствует => создаём и добавляем в начало (head = new Obj(from, head))
     *  4. Добавление вершины-потомка у from
     *  5. Увеличиваем количество входящих рёбер у to
     */
    public void Init(int[][] values) {
        for (int[] value : values) {
            int from = value[0];
            int to = value[1];
            Obj fromObj = find(from);
            Obj toObj = find(to);
            if (fromObj == null) fromObj = addToHead(from);
            if (toObj == null) toObj = addToHead(to);
            fromObj.child = new Child(toObj, fromObj.child);
            toObj.quantity++;
        }
    }

    /**
     *  newHead = null, current = null
     * 1. Пока остаются вершины в исходном списке (head != null)
     * 2. Если голова имеет 0 входящих рёбер => удаляем её (head.quantity == 0)
     * 3. Иначе ищем следующую вершину для удаления
     *      3.1. Если не найдена => return false
     *      3.2. Иначе удаляем найденную вершину
     * 4. Добавляем удалённую вершину в конец нового списка
     * 5. head = newHead
     */
    public boolean TopoSort() {
        Obj newHead = null;
        Obj current = null;
        while (head != null) {
            Obj delete;
            if(head.quantity == 0) {
                delete = head;
                head = head.next;
                deleteObj(delete);
            }
            else {
                Obj prevRemove = prevRemove();
                if(prevRemove == null) return false;
                delete = prevRemove.next;
                prevRemove.next = delete.next;
                deleteObj(delete);
            }
            if(newHead == null) {
                newHead = delete;
                current = newHead;
            }
            else {
                current.next = delete;
                current = current.next;
            }
        }
        head = newHead;
        return true;
    }
    // метод для добавления нового элемента в начале
    private Obj addToHead(int value){
        head = new Obj(value, head);
        return head;
    }
    // Метод для поиска вершины перед той, у которой quantity == 0
    private Obj prevRemove() {
        Obj prev = null;
        Obj current = head;
        while (current != null) {
            if (current.quantity == 0) return prev;
            prev = current;
            current = prev.next;
        }
        return null;
    }
    // метод для поиска вершины с указанным знаменем
    private Obj find(int value){
        Obj result = null;
        Obj current = head;
        while (current != null) {
            if (current.value == value) result = current;
            current = current.next;
        }
        return result;
    }
    //  Метод для удаления вершины
    private void deleteObj(Obj obj) {
        Child currentChild = obj.child;
        while(currentChild != null) {
            currentChild.next.quantity--;
            currentChild = currentChild.child;
        }
        obj.child = null;
        obj.next = null;
    }

    public void print() {
        Obj current = head;
        System.out.println("Список частично упорядоченных элементов:");
        while (current != null) {
            System.out.print("Вершина " + current.value + " → ");
            Child child = current.child;
            if (child == null) {
                System.out.println("нет");
            } else {
                while (child != null) {
                    System.out.print(child.next.value);
                    child = child.child;
                    if (child != null) System.out.print(", ");
                }
                System.out.println();
            }
            System.out.println("Quantity: " + current.quantity);
            current = current.next;
        }
    }
}