package DoubleLinkedList;
import ListResources.ListException;
import ListResources.Value;

public class DoubleLinkedList implements IList{
    private Item start;
    private Item end;
    @Override
    public void insert(Value value, Position position) {
        /**
         * 1. Случай 1 — Если position.item == null
         *  1.1. Если start == null
         *      1.1.1. start = new Item(new Value(value))
         *      1.1.2. end = start
         *  1.2. Если start != null => добавляем в конце
         *      1.2.1. end.next = new Item(new Value(value))
         *      1.2.2. Заменим prev последнего элемента: end.getNext.prev = end
         *      1.2.3. end = end.next
         */
        if(position.item == null){
            if(start == null){
                start = new Item(new Value(value));
                end = start;
                return;
            }
            end.setNext(new Item(new Value(value)));
            end.getNext().setPrev(end);
            end = end.getNext();
            return;
        }
        /**
         * 2. Если start == end
         *  2.1. start.next = new Item(start.getValue())
         *  2.2. start.setValue = new Value(Value)
         *  2.3. end = start.getNext()
         *  2.4. end.setPrev(start)
         */
        if(start == end){
            start.setNext(new Item(start.getValue()));
            start.setValue(new Value(value));
            end = start.getNext();
            end.setPrev(start);
            return;
        }
        if(position.item == end){
            end.setNext(new Item(end.getValue()));
            end.getNext().setPrev(end);
            end.setValue(new Value(value));
            return;
        }
        /**
         * 3.3. Если position существует, вставьте новый элемент в середину списка.
         *   3.3.1. Найдем элемент current в позиции position: current = position.item
         *   3.3.2. Создаем новый элемент с значение элемента current: newPos = new Position(new Item(new Value(current.value))
         *   3.3.3. Заменим ссылку на следующий и предующий элемент нового элемента
         *          newPos.setNext = current.getNext
         *          newPos.setPrev = current
         *   3.3.4. Изменим значение текущего элемента: current.setValue(copy)
         *   3.3.5. Изменим ссылку на следуюший элемент:
         *          current.setNext(newPosition.item)
         */
        if (InList(position)){
            Item current = position.item;
            Position newPosition = new Position(new Item(new Value(current.getValue())));
            newPosition.item.setNext(current.getNext());
            newPosition.item.setPrev(current);
            current.setValue(new Value(value));
            current.setNext(newPosition.item);
        }
    }
    /**
     * 1. инициализируем переменную result = start
     * 2. Пока result != null
     *  2.1. Если result.Value.equals(value) => return new Position(result)
     *  2.2. result = result.getNext()
     * 3. Если не найдено => return new Position(null)
     */
    @Override
    public Position locate(Value value) {
        Item result = start;
        while (result != null){
            if (result.getValue().equals(value)) return new Position(result);
            result = result.getNext();
        }
        return new Position(null);
    }
    /**
     * 1. Случай 1: Если start == null => Exception
     * 2. Случай 2: Если position.item == head => head.getValue()
     * 3. Остальные случаи:
     *  3.1. Проверим, существует ли элемент в позиции position: InList(position)
     *  3.2. Если позиция не найдена => Исключение
     *  3.3. В противном случае вернем position.item.getValue().
     */
    @Override
    public Value retrieve(Position position){
        if (start == null) throw new ListException("Позиция не известна");
        if (position.item == null) throw new ListException("Позиция не известна");
        if (!InList(position)) throw new ListException("Позиция не известна");
        return position.item.getValue();
    }
    /**
     * 1. Случай 1: Если start == null || end == null || position.item == null => return;
     * 2. Случай 2: Если position.item == start
     *      start = start.getNext();
     *      start.setPrev(null);
     * 3. Случай 3: Если position.item == end
     *      end = end.getPrev();
     *      end.setNext(null);
     * 4. Остальные случаи:
     *  4.1. Проверим, существует ли элемент в позиции position: InList(position)
     *  4.2. Если позиция найдена.
     *      4.2.1. Надем предующий элемент: prev = positon.item.getPrev();
     *      4.2.2. prev.setNext() = position.getNext();
     *      4.2.3. Заменим prev следующего элемента = prev: position.getNext().setPrev(prev);
     */
    @Override
    public void delete(Position position) {
        if (start == null || end == null || position.item == null) return;
        if (position.item == start){
            if(start == end){
                start = null;
                end = null;
                return;
            }
            start = start.getNext();
            start.setPrev(null);
            return;
        }
        if (position.item == end){
            end = end.getPrev();
            end.setNext(null);
            return;
        }
        if (InList(position)){
            Item prev = position.item.getPrev();
            prev.setNext(position.item.getNext());
            position.item.getNext().setPrev(prev);
        }
    }
    /**
     * 1. Если position.item == null => Exception
     * 2. Если position.item == start => return start.getNext()
     * 3. Если position.item == end => return new Position(null)
     * 4. Остальные случаи:
     *  4.1. Проверим, существует ли элемент в позиции position: InList(position)
     *  4.2. Если InList(position) == false => Exception
     *  4.3. return new Position.getNext(position.getNext())
     */
    @Override
    public Position next(Position position) {
        if (position.item == null) throw new ListException("Позиция не известна");
        if (position.item == start) return new Position(start.getNext());
        if (position.item == end) return new Position(null);
        if (!InList(position)) throw new ListException("Позиция не известна");
        return new Position(position.item.getNext());
    }
    /**
     * 1. Если position.item == null => Exception
     * 2. Если position.item == start => Exception
     * 3. Если position.item == end => return new Position(end.getPrev())
     * 4. Остальные случаи:
     *  4.1. Проверим, существует ли элемент в позиции position: InList(position)
     *  4.2. Если InList(position) == false => Exception
     *  4.3. return new Position.getNext(position.getPrev())
     */
    @Override
    public Position previous(Position position){
        if (position.item == null) throw new ListException("Позиция не известна");
        if (position.item == end) return new Position(end.getPrev());
        if (position.item == start)throw new ListException("Позиция не известна");
        if (!InList(position))throw new ListException("Позиция не известна");
        return new Position(position.item.getPrev());
    }
    @Override
    public Position first() {
        return new Position(start);
    }
    @Override
    public Position makeNull() {
        start = null;
        end = null;
        return new Position(null);
    }
    /**
     * 1. Если start == null => The List is empty
     * 2. Сделаем копию head: temp = start.item
     * 3. Пока temp != null:
     *  3.1. temp.getValue().print()
     *  3.2. temp = temp.getNext()
     */
    @Override
    public void printList() {
        if (start == null || start.getValue() == null){
            System.out.println("The List is empty");
            return;
        }
        Item temp = start;
        while (temp != null){
            temp.getValue().print();
            temp = temp.getNext();
        }
    }
    @Override
    public Position end() {
        return new Position(null);
    }
    /**
    *  1. result = start
     * 2. Пока result != null
     *      2.1. Если position.item == result => return true
     *      2.2 result = result.getNext()
     * 3. return false;
    * */
    private Boolean InList(Position position){
        Item result = start;
        while (result != null){
            if (position.item == result) {
                return true;
            }
            result = result.getNext();
        }
        return false;
    }

}
