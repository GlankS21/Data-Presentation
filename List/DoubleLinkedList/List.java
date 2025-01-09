package List.DoubleLinkedList;
import Resources.ListException;
import Resources.Value;
public class List implements IList{
    private Item start;
    private Item end;
    @Override
    public void insert(Value value, Position position) {
        if(start == end){
            if(position.item == null){
                if(start == null){ // Если массив пустой
                    start = new Item(new Value(value));
                    end = start;
                    return;
                }
                end = new Item(new Value(value)); // Добавим в конце
                end.setPrev(start);
                start.setNext(end);
                return;
            } // Если массив содержит только 1 элемент
            start.setNext(new Item(start.getValue()));
            start.setValue(new Value(value));
            end = start.getNext();
            end.setPrev(start);
            return;
        }
        if(position.item == null){ // Добавим в конце
            end.setNext(new Item(new Value(value)));
            end.getNext().setPrev(end);
            end = end.getNext();
            return;
        }
        // Поставим в end
        if(position.item == end){
            end.setNext(new Item(end.getValue()));
            end.getNext().setPrev(end);
            end.setValue(new Value(value));
            end = end.getNext();
            return;
        }
        // Поставим в середине
        if (InList(position)){
            Item current = position.item;
            Item next = current.getNext();
            // Создаем новый элемент
            Item newPosition = new Item(new Value(current.getValue())); // Новый элемент
            newPosition.setNext(next); //setNext
            newPosition.setPrev(current); //setPrev

            next.setPrev(newPosition); // set next

            current.setValue(new Value(value)); // set current
            current.setNext(newPosition);
        }
    }
    @Override
    public Position locate(Value value) {
        Item result = start;
        while (result != null){
            if (result.getValue().equals(value)) return new Position(result);
            result = result.getNext();
        }
        return new Position(null);
    }
    @Override
    public Value retrieve(Position position){
        if (start == null) throw new ListException("Позиция не известна");
        if (position.item == null) throw new ListException("Позиция не известна");
        if (!InList(position)) throw new ListException("Позиция не известна");
        return position.item.getValue();
    }
    @Override
    public void delete(Position position) {
        if (start == null || end == null || position.item == null) return;
        if(start == end && position.item == start){ // Если массив только содержит 1 элемент
            start = null;
            end = null;
            return;
        }
        if (position.item == start){
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
    @Override
    public Position next(Position position) {
        if (position.item == null) throw new ListException("Позиция не известна");
        if (position.item == start) return new Position(start.getNext());
        if (position.item == end) return new Position(null);
        if (!InList(position)) throw new ListException("Позиция не известна");
        return new Position(position.item.getNext());
    }
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
