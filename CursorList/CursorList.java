package CursorList;
import ListResources.ListException;
import ListResources.Value;

public class CursorList implements IList {
    private static final Item[] items;
    private static final Position space;
    private int head;
    // Создаем массив и space для всех объектов
    static {
        items = new Item[50];
        space = new Position(0);
        for(int i = 0; i < items.length - 1; i++){
            items[i] = new Item(i+1);
        }
        items[items.length - 1] = new Item(-1);
    }
    public CursorList(){ head = -1;}
    @Override
    public void insert(Value value, Position p) {
        /**
          1. Случай 1 — Если p.position == -1
           1.1. Если head == -1 => Поставим в начале
               1.1.1. head = space.position
               1.1.2. space.position = items[space.position].getNext()
               1.1.3. items[head].setItem(new Value(value), -1));
           1.2. Если head != -1 => Поставим в конце
               2.1. Найдем позицию перез последнего Item Last = Last()
               2.2. Найдем новую позицию, где поставим новый элемент: Space = space.position
               2.3. space.position = items[space.position].getNext()
               2.4. Изменяем ссылку на следуюший элемент элемента last новой позицией: items[last].setNext(Space);
               2.5. Заменим значение и next нового элемента Space: items[Space].setItem(new Value(value),-1)
         */
        if (p.position == -1){
            // Вариант: Если массив пустой
            if (head == -1){
                head = space.position;
                space.position = items[space.position].getNext();
                items[head].setItem(new Value(value), -1);
                return;
            }
            // Вариант: Поставим в конце
            int last = Last();
            int Space = space.position;
            space.position = items[space.position].getNext();
            items[last].setNext(Space);
            items[Space].setItem(new Value(value), -1);
            return;
        }
        /**
          2.Случай 2 - p.position == head - Поставим в начале массива
           2.1. Найдем новую позицию, где поставим новый элемент: Space = space.position
           2.2. Изменим значение Space следующим пустом элементом: space.position = items[space.position].getNext();
           2.3. Копируем head и поставим его в позиции Space: items[Space] = new Item(new Value(items[head].getValue()), items[head].getNext());
           2.3. Изменим head: items[head].setItem(new Value(value), Space);
         */
        // Вариант: Поставим в начале массива
        if (p.position == head){
            int Space = space.position;
            space.position = items[space.position].getNext();
            items[Space].setItem(items[head].getValue(), items[head].getNext());
            items[head].setItem(new Value(value), Space);
            return;
        }
        /** temp = Prev(p)
          3.3. Если существует, поставим новый элемент в середину списка. (temp != -1)
            3.3.1. Найдем элемент current в позиции position: current = items[temp].getNext();
            3.3.2. Найдем новую позицию, где поставим новый элемент: Space = space.position
            3.3.2. Изменим значение Space следующим пустом элементом: space.position = items[space.position].getNext();
            3.3.3. Копируем текущий элемент current и поставим его в позиции Space: items[Space] = new Item(new Value(items[current].getValue()), items[current].getNext());
            3.3.4. Изменим значение текущего элемента: items[current].setValue(new Value(value));
            3.3.5. Изменим ссылку на следуюший элемент: items[current].setNext(Space);
         */
        // Поставим в середине
        int temp = Prev(p);
        if (temp != -1){
            int current = items[temp].getNext();
            int Space = space.position;
            space.position = items[space.position].getNext(); // Новая позция элемента space
            items[Space] = new Item(new Value(items[current].getValue()), items[current].getNext()); // Создаем копии current в позиции space
            items[current].setValue(new Value(value)); // В позиции current, старый элемент изменен new Value
            items[current].setNext(Space);
        }
    }
    /**
     * 1. инициализируем переменную result = start
     * 2. Пока result != null
     *  2.1. Если items[result].getValue().equals(value) => return new Position(result)
     *  2.2. result = items[result].getNext()
     * 3. Если не найдено => return new Position(-1)
     */
    @Override
    public Position locate(Value value) {
        int result = head;
        while (result != -1){
            if (items[result].getValue().equals(value)) {
                return new Position(result);
            }
            result = items[result].getNext();
        }
        return new Position(-1);
    }
    /**
     * 1. Случай 1: Если p.position < 0 || p.position > items.length => Exception
     * 2. Случай 2: Если p.position == head => return items[head].getValue()
     * 3. Остальные случаи:
     *  3.1. Мы найдем элемент temp в предующей позиции position: temp = prev(p)
     *  3.2. Если позиция не найдена => Исключение
     *  3.3. В противном случае вернем items[items[temp].getNext()].getValue().
     */
    @Override
    public Value retrieve(Position p){
        if (p.position < 0 || p.position > items.length) throw new ListException("Позиция не известна");
        if (p.position == head) return items[head].getValue();
        int temp = Prev(p);
        if (temp == -1) throw new ListException("Позиция не известна");
        return items[items[temp].getNext()].getValue();
    }
    /**
     * 1. Случай 1: Если p.position = -1 || head == -1 => return;
     * 2. Случай 2: Если p.position == head
     *      2.1. Изменим значение space
     *          space.position = head;
     *          items[space.position].setNext(space.position);
     *      2.2. head = items[head].getNext();
     * 3. Остальные случаи:
     *  3.1. Найдем элемент перед position: prev = Prev(p)
     *  3.2. Если позиция найдена (prev != -1).
     *      3.2.1. Надем элемент в позиции position: current = items[prev].getNext();
     *      3.2.2. Если current != -1
     *          3.2.2.1. items[prev].setNext(items[current].getNext());
     *          3.2.2.2. items[current].setNext(space.position);
     *          3.2.2.3. space.position = current;
     */
    @Override
    public void delete(Position p) {
        if (head == -1) return;
        // Удалить head
        if (p.position == head){
            space.position = head;
            items[space.position].setNext(space.position);
            head = items[head].getNext();
            return;
        }
        // Другий элемент
        int prev = Prev(p);
        if (prev != -1) {
            int current = items[prev].getNext();
            items[prev].setNext(items[current].getNext());
            items[current].setNext(space.position);
            space.position = current;
        }
    }

    @Override
    public Position next(Position p){
        if (p.position > items.length) throw new ListException("Позиция не известна");
        if (p.position == head) return new Position(items[head].getNext());
        int prev = Prev(p);
        if (prev == -1) throw new ListException("Позиция не известна");
        return new Position(items[items[prev].getNext()].getNext());
    }
    /**
     * 1. Если p.position > items.length || position == head => Exception
     * 2. Найдем позицю result = Prev(p)
     * 3. Если позиция не найдена (result == -1) => exception
     * 4. return new Position(result)
     */
    @Override
    public Position previous(Position p) {
        if (p.position > items.length || p.position == head) throw new ListException("Позиция не известна");
        int result = Prev(p);
        if (result == -1) throw new ListException("Позиция не известна");
        return new Position(result);
    }
    @Override
    public Position first() {
        return new Position(head);
    }

    /**
     * 1. Если list пустой => return new Position(-1)
     * 2. Если не пустой:
     *  2.1. Изменим next последнего элемента = позиции space
     *  2.2. Space получит позицию первый элемент head: space.position = head
     *  2.3. head = -1
     *  return new Position(-1)
     */
    @Override
    public Position makeNull() {
        if(head == -1) return new Position(-1);
        items[Last()].setNext(space.position);
        space.position = head;
        head = -1;
        return new Position(-1);
    }

    /**
     * 1. Если head == -1 => The List is empty
     * 2. temp = head
     * 3. Пока items[temp].getNext() != -1:
     *  3.1. items[temp].getValue().print()
     *  3.2. temp = items[temp].getNext()
     */
    @Override
    public void printList() {
        if (head == -1){
            System.out.println("The List is empty");
            return;
        }
        int temp = head;
        System.out.print(String.format("%-3s", head) + " : ");
        while (items[temp].getNext() != -1){
            items[temp].getValue().print();
            System.out.print(String.format("%-3s", items[temp].getNext()) + " : ");
            temp = items[temp].getNext();
        }
        items[temp].getValue().print();
    }

    @Override
    public Position end() {
        return new Position(-1);
    }
    /**
     *  1. current = head
     *  2. Создаем prev = -1
     *  3. Пока temp != -1
     *   3.1. Если p.position == -1 => return prev
     *   3.2. Если нет prev = current
     *                 current = items[current].getNext()
     *  4. Если не надейно => return -1;
     */
    private int Prev(Position p) {
        int current = head;
        int prev = -1;
        while (current != -1){
            if (p.position == current) {
                return prev;
            }
            prev = current;
            current = items[current].getNext();
        }
        return -1;
    }
    /** Элемент в последней позиции
     *  1. current = head
     *  2. prev = -1
     *  3. Пока current != -1
     *   3.1. prev = current
     *        current = items[current].getNext()
     *  4. return prev
     */
    private int Last() {
        int current = head;
        int prev = -1;
        while (current != -1){
            prev = current;
            current = items[current].getNext();
        }
        return prev;
    }
}
