package Set;

public class Set implements ISet {
    private static class Item {
        int number;
        Item next;
        public Item(int number, Item next) {
            this.number = number;
            this.next = next;
        }
    }
    private Item rear; // хвост списка

    public Set() {
        rear = null;
    }

    public Set(Set set) {
        rear = (set.rear == null) ? null : copy(set.rear.next, set.rear);
    }

    // А ∩ В - пересечение
   @Override
    public Set INTERSECTION(Set B) {
        if(this == B) return new Set(this);
        if(this.rear == null || B.rear == null) return new Set();
        if(this.rear.next.number > B.rear.number || this.rear.number < B.rear.next.number) return new Set();

        Set Intersection = new Set();
        Item CurrentA = this.rear;
        Item CurrentB = B.rear.next;

        // Добавим первый элемент
        while (CurrentB != B.rear){
            Item prev = Prev(CurrentA, CurrentB.number);
            if (prev.next.number == CurrentB.number) { // Если найдем одинаковый элемент => Добавим в множестве
                Intersection.addFirstItem(CurrentB);
                CurrentA = prev;
                CurrentB = CurrentB.next;
                break;
            }
            if(prev.next == rear) return Intersection; // Если ничего не найден
            CurrentB = CurrentB.next;
        }
        if(Intersection.rear == null){ // Проверим хвост B
            Item prev = Prev(CurrentA, B.rear.number);
            if (prev.next.number == B.rear.number) // => Добавим
                Intersection.addFirstItem(B.rear);
            return Intersection;
        }

        // Добавим остальные
       while (CurrentB != B.rear) {
           Item prev = Prev(CurrentA, CurrentB.number);
           // Если найден элемент => Добавим в конце
           if (prev.next.number == CurrentB.number) Intersection.addItem(CurrentB, Intersection.rear);
           else if (prev.next == rear) return Intersection;
           CurrentA = prev;
           CurrentB = CurrentB.next;
       }
       // Обработка хвоста
       Item prev = Prev(CurrentA, B.rear.number);
       // Добавим в конце
       if (prev.next.number == B.rear.number) Intersection.addItem(B.rear, Intersection.rear);
       return Intersection;
    }
    // А U В - объединение
    @Override
    public Set UNION(Set B) {
        if(this == B) return new Set(this);
        if (rear == null && B.rear == null) return new Set();
        if (rear == null) return new Set(B);
        if (B.rear == null) return new Set(this);

        Set union = new Set(this);
        Item unionCurrent = union.rear;
        Item CurrentB = B.rear.next;

        // Добавим элементы из B
        while(CurrentB != B.rear){
            Item prev = union.Prev(unionCurrent, CurrentB.number);
            if(prev.next.number == CurrentB.number){ // Если 2 элементы одинаковые => Пропустим
                unionCurrent = prev;
            }else if (prev.next == union.rear && prev.next.number < CurrentB.number){ // Если проходили все элементы
                unionCurrent = prev;
                break;
            }else { // Если 2 элементы разные => Добавим
                Item next = prev.next;
                prev.next = new Item(CurrentB.number, next);
                unionCurrent = prev.next;
            }
            CurrentB = CurrentB.next;
        }
        // Если проходили все элементы в A => Добавим остальные элементы в B
        while (CurrentB != B.rear) {
            union.addItem(CurrentB, union.rear);
            CurrentB = CurrentB.next;
        }

        // Отдельно обработаем по такому же алгоритму хвост
        Item prev = union.Prev(unionCurrent, B.rear.number);
        // Вариант добавим в конце
        if (prev.next.number < CurrentB.number) union.addItem(B.rear, union.rear);
        // Вариант добавим перед концом
        if (prev.next.number > CurrentB.number) prev.next = new Item(CurrentB.number,  prev.next);

        return union;
    }
    // А \ В
    @Override
    public Set DIFFERENCE(Set B) {
        if (B.rear == null) return new Set(this);
        if (this.rear == null || this == B) return new Set();
        if(B.rear.next.number > this.rear.number || this.rear.next.number > B.rear.number) return new Set(this);

        Set difference = new Set();
        Item CurrentA = this.rear.next;
        Item CurrentB = B.rear;

        // Добавим первый элемент
        while (CurrentA != this.rear) {
            Item prev = B.Prev(CurrentB, CurrentA.number);
            if (prev.next.number != CurrentA.number) { // Если в В нет такого элемента => Добавим
                difference.addFirstItem(CurrentA);
                CurrentB = prev;
                CurrentA = CurrentA.next;
                break;
            }
            if (prev.next == B.rear) { // Если ничего не найден => Добавим остальные в A
                difference.rear = copy(CurrentA.next, this.rear);
                return difference;
            }
            CurrentA = CurrentA.next;
        }
        // Проверим хвост
        if (difference.rear == null) {
            Item prev = B.Prev(CurrentB, this.rear.number);
            // Добавим в пустом множестве
            if (prev.next.number != this.rear.number) difference.addFirstItem(this.rear);
            return difference;
        }
        // Добавим другие элементы
        while (CurrentA != this.rear) {
            Item prev = B.Prev(CurrentB, CurrentA.number);
            // Если 2 элементы не одинаковые => Добавим в конце
            if (prev.next.number != CurrentA.number) difference.addItem(CurrentA, difference.rear);
            // Если проходили все элементы в B => Добавим остальные в А
            else if (prev.next == B.rear){
                while (CurrentA.next != this.rear.next){
                    CurrentA = CurrentA.next;
                    difference.addItem(CurrentA, difference.rear);
                }
                return difference;
            }
            CurrentB = prev;
            CurrentA = CurrentA.next;
        }

        // Обработка хвоста
        Item prev = B.Prev(CurrentB, this.rear.number);
        // Добавим в конце
        if (prev.next.number != this.rear.number) difference.addItem(this.rear, difference.rear);
        return difference;
    }
    // А U В, но результат будет не определен, если А ∩ В ≠ 0
    @Override
    public Set MERGE(Set B) {
        if (this == B) return new Set(this);
        if (B.rear == null && rear == null) return new Set();
        if (this.rear == null) return new Set(B);
        if (B.rear == null) return new Set(this);

        Set merge = new Set(this);
        Item Current = merge.rear;
        Item CurrentB = B.rear.next;

        while (CurrentB != B.rear) {
            Item prev = merge.Prev(Current, CurrentB.number);
            if (prev.next == merge.rear && prev.next.number < CurrentB.number) { // Если prev = merge.rear => Добавим в конце
                Current = prev;
                break;
            }
            // Если нет, поставим в правильном месте
            Item next = prev.next;
            prev.next = new Item(CurrentB.number, next);
            Current = prev.next;
            CurrentB = CurrentB.next;
        }

        // Если все элементы в А меньшее элементов в B => Добавим остальные элементы в конце
        while (CurrentB != B.rear) {
            merge.addItem(CurrentB, merge.rear);
            CurrentB = CurrentB.next;
        }

        // Обработка хвоста
        Item prev = merge.Prev(Current, B.rear.number);
        // Вариант добавим в конце
        if (prev.next.number < CurrentB.number) merge.addItem(B.rear, merge.rear);
        // Вариант добавим перед концом
        if (prev.next.number > CurrentB.number) prev.next = new Item(CurrentB.number, prev.next);
        return merge;
    }
    // Добавляем новый элемент после позиции
    private void addFirstItem(Item item){
        this.rear = new Item(item.number, null);
        this.rear.next = this.rear;
    }
    private void addItem(Item item, Item prev){
        Item temp = prev;
        prev = new Item(item.number, prev.next);
        temp.next = prev;
        if (temp == this.rear) {
            this.rear = prev;
        }
    }
    @Override
    public boolean MEMBER(int x) {
        if (this.rear == null) return false;
        return isMember(x);
    }
    @Override
    public void MAKENULL() {
        rear = null;
    }
    @Override
    public void INSERT(int x) {
        if (rear == null) {
            rear = new Item(x, null);
            rear.next = rear;
            return;
        }
        if (x > rear.number) {
            Item temp = rear;
            rear = new Item(x, rear.next);
            temp.next = rear;
            return;
        }
        Item prev = Prev(rear, x);
        if (prev.next.number == x) return;
        prev.next = new Item(x, prev.next);
    }
    // Удаляет значение из множества, если оно там есть, если нет ничего не делать
    @Override
    public void DELETE(int x) {
        if (rear == null || x > rear.number) return;
        if (rear == rear.next) {
            if (rear.number == x) rear = null;
            return;
        }
        Item prev = Prev(rear, x);
        if (prev.next.number == x) {
            if (prev.next == rear) {
                prev.next = rear.next;
                rear = prev;
            }
            else prev.next = prev.next.next;
        }
    }
    //присваивает множеству А в качестве значения множество В.
    @Override
    public void ASSIGN(Set B) {
        if (B == this) return;
        if (B.rear == null) {
            rear = null;
            return;
        }
        rear = copy(B.rear.next, B.rear);
    }
    @Override
    public int MIN() {
        if (rear == null) throw new SetException("Список пустой");
        return rear.next.number;
    }
    @Override
    public int MAX() {
        if (rear == null) throw new SetException("Список пустой");
        return rear.number;
    }
    @Override
    public boolean EQUAL(Set B){
        if (this == B) return true;
        if (this.rear == null || B.rear == null) return this.rear == B.rear;

        Item currentA = this.rear;
        Item currentB = B.rear;
        while (currentA.next != this.rear && currentB.next != B.rear) {
            if (currentA.number != currentB.number) return false;
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return currentA.next.number == currentB.next.number;
    }
    @Override
    public Set FIND(Set B, int x) {
        if (isMember(x)) return this;
        else if (B.isMember(x)) return B;
        else return null;
    }
    // метод для поиска элемента с наибольшим значением, но меньшим, чем x
    private Item Prev(Item start, int x) {
        Item prev = start;
        Item current = start.next;
        while (current != this.rear) {
            if (current.number >= x) {
                return prev;
            }
            prev = current;
            current = current.next;
        }
        return prev;
    }
    // метод для копирования всех элементов от start до end
    private Item copy(Item start, Item end) {
        Item rear = new Item(end.number, null);
        rear.next = rear;
        Item temp = rear;
        while (start != end) {
            temp.next = new Item(start.number, rear);
            start = start.next;
            temp = temp.next;
        }
        return rear;
    }
    private boolean isMember(int x){
        Item prev = Prev(rear, x);
        return prev.next.number == x;
    }
    public void print() {
        if (rear == null) {
            System.out.println("Список пустой");
            return;
        }
        Item current = rear.next;
        do {
            System.out.print(current.number + " ");
            current = current.next;
        }
        while (current != rear.next);

        System.out.println();
    }
}


