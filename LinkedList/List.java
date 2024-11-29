package LinkedList;
import Resources.ListException;
import Resources.Value;

public class List implements IList {
    private Item head;
    @Override
    public Position end() { return new Position (null);}
    @Override
    public void insert(Value value, Position position) {
        /*
          1. Случай 1 — Если position.item == null
           1.1. Если head == null
               1.1.1. head = new Item(copy)
           1.2. Если head != null => добавляем в конце
               2.1. Найдем позицию перез последнего Item Last = Last() (last = null, поэтому я вызывал эту переменную last)
               2.2. Изменяем next последнего позиции = new Item(copy): Last.setNext(new Item(copy))
         */
        if (position.item == null){
            if (head == null) head = new Item(new Value(value)); // empty list
            else{  // поставим в конце
                Item last = Last();
                last.setNext(new Item(new Value(value)));
            } return;
        }
        // поставим в начале
        /*
          2.Случай 2 - Вставляется в начале массива (position = head)
           2.1. Найдем следующий элемент head: temp = head.getNext
           2.2. Изменим ссылку на следующий элемент: head.setNext = new Item(head.value)
           2.3. Изменим ссылку нового элемента (следующий элемент следующего): head.next.setNext = temp: head.next.setNext = temp
           2.4. Изменим значения head = copy
         */
        if (position.item == head){
            Item temp = head.getNext();
            head.setNext(new Item(head.getValue()));
            head.getNext().setNext(temp);
            head.setValue(new Value(value));
            return;
        }
        // поставим в середине
        /*
          3.3. Если существует, вставьте новый элемент в середину списка.
            3.3.1. Найдем элемент current в позиции position (находится в позиуии position): current = prev.getNext()
            3.3.2. Создаем новый элемент с значение элемента current: newPosition = new Position(new Item(new Value(current.value))
            3.3.3. Заменим ссылку на следующий элемент нового элемента newPosition.setNext = current.getNext
            3.3.4. Изменим значение текущего элемента: current.setValue(copy)
            3.3.5. Изменим ссылку на следуюший элемент: current.setNext(newPosition.item)
         */
        Item prev = Prev(position.item);
        if (prev != null) {
            Item current = prev.getNext();
            Position newPosition = new Position(new Item(new Value(current.getValue())));
            newPosition.item.setNext(current.getNext());
            current.setValue(new Value(value));
            current.setNext(newPosition.item);
        }
    }
    /**
     * 1. инициализируем переменную result = head
     * 2. Пока result != null
     *  2.1. Если result.Value.equals(value) => return new Position(result)
     *  2.2. result = result.Next
     * 3. Если не найдено => return new Position(null)
     */
    @Override
    public Position locate(Value value) {
        Item result = head;
        while (result != null){
            if (result.getValue().equals(value)) return new Position(result);
            result = result.getNext();
        }
        return new Position(null);
    }
    /**
     * 1. Случай 1: Если position.item == null => Exception
     * 2. Случай 2: Если position.item == head => head.getValue()
     * 3. Остальные случаи:
     *  3.1. Мы найдем элемент temp в предующей позиции position: temp = prev(position)
     *  3.2. Если позиция не найдена == null => Исключение
     *  3.3. В противном случае вернем temp.item.getNext().getValue().
     */
    @Override
    public Value retrieve(Position position){
        if (position.item == null) throw new ListException("Позиция не известна");
        if (position.item == head) return head.getValue();
        Item temp = Prev(position.item);
        if (temp == null) throw new ListException("Позиция не известна");
        return temp.getNext().getValue();
    }
    /**
     * 1. Случай 1: Если position == null или head == null => return;
     * 2. Случай 2: Если position.item == head => head = head.getNext()
     * 3. Остальные случаи:
     *  3.1. Найдем элемент перед position: temp = Prev(position)
     *  3.2. Если позиция найдена (temp != null).
     *      3.2.1. Надем элемент в позиции position: current = temp.item.getNext()
     *      3.2.2. Если current != null => temp.item.setNext() = current.getNext()
     */
    @Override
    public void delete(Position position) {
        if (position.item == null || head == null) return;
        if (position.item == head) {
            head = head.getNext();
            return;
        }
        Item prev = Prev(position.item);
        if (prev != null){
            Item current = prev.getNext();
            prev.setNext(current.getNext());
        }
    }
    /**
     * 1. Если position == null => Exception
     * 2. Если position.item == head => return head.getNext()
     * 3. Остальные случаи:
     *  3.1. Найдем предуюший элемент prev = Prev(position.item)
     *  3.2. Если prev == null (позиция не найдена) или (current = prev.getNext) == null => Exception
     *  3.3. return current.getNext() (prev.getNext.getNext)
     */
    @Override
    public Position next(Position position){
        if (position.item == null ) throw new ListException("Позиция не известна");
        if (position.item == head) return new Position(head.getNext());
        Item prev = Prev(position.item); // Если позиция = last => next не существует, поэтому только надо проверить позиции, которые находятся перед last
        if (prev == null) throw new ListException("Позиция не известна");
        return new Position(prev.getNext().getNext());
    }
    /**
     * 1. Если position == null || position == head => Exception
     * 2. Найдем позицю result = Prev(position.item)
     * 3. Если позиция не найдена => exception
     * 4. return new Position(Prev(position))
     */
    @Override
    public Position previous(Position position){
        if (position.item == null || position.item == head) throw new ListException("Позиция не известна");
        Item result = Prev(position.item);
        if(result == null) throw new ListException("Позиция не известна");
        return new Position(Prev(position.item));
    }
    @Override
    public Position first() { return new Position(head);}
    @Override
    public Position makeNull() {
        head = null;
        return new Position(null);
    }
    /**
     * 1. Если head == null => The List is empty
     * 2. Сделаем копию head: temp = head.item
     * 3. Пока temp != null:
     *  3.1. temp.getValue().print()
     *  3.2. temp = temp.getNext()
     */
    @Override
    public void printList() {
        if (head == null || head.getValue() == null){
            System.out.println("The List is empty");
            return;
        }
        Item temp = head;
        while (temp != null){
            temp.getValue().print();
            temp = temp.getNext();
        }
        System.out.println();
    }
    /**
     *  1. Сделаем компью temp = head
     *  2. Создаем prev = null
     *  3. Пока temp != null
     *   3.1. Если position.item == temp => return new Position(prev)
     *   3.2. Если нет prev = temp
     *                 temp = temp.getNext()
     *  4. Если не надейно => return null
     */
    private Item Prev(Item item){
        Item temp = head;
        Item prev = null;
        while (temp != null){
            if (temp == item) return prev;
            prev = temp;
            temp = temp.getNext();
        }
        return null;
    }
    /**
     *  1. Сделаем компью temp = head
     *  2. Создаем prev = null
     *  3. Пока temp != null
     *   3.1. prev = temp
     *        temp = temp.getNext()
     *  4. return prev
     */
    private Item Last() { // Найти последную позицю
        Item temp = head;
        Item prev = null;
        while (temp != null){
            prev = temp;
            temp = temp.getNext();
        }
        return prev;
    }
}
