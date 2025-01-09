package List.LinkedList;
import Resources.ListException;
import Resources.Value;

public class List implements IList{
    private Item head;
    @Override
    public Position end() { return new Position (null);}
    @Override
    public void insert(Value value, Position position) {
        if (position.item == null){
            if (head == null) head = new Item(new Value(value)); // empty list
            else{  // поставим в конце
                Item last = Last();
                last.setNext(new Item(new Value(value)));
            } return;
        }
        Item prev = Prev(position.item);
        if (prev != null) {
            Item current = prev.getNext();
            Item newItem = new Item(new Value(current.getValue()));
            newItem.setNext(current.getNext());
            current.setValue(new Value(value));
            current.setNext(newItem);
        }
    }
    @Override
    public Position locate(Value value) {
        Item result = head;
        while (result != null){
            if (result.getValue().equals(value)) return new Position(result);
            result = result.getNext();
        }
        return new Position(null);
    }
    @Override
    public Value retrieve(Position position){
        if (position.item == null) throw new ListException("Позиция не известна");
        if (position.item == head) return head.getValue();
        Item temp = Prev(position.item);
        if (temp == null) throw new ListException("Позиция не известна");
        return temp.getNext().getValue();
    }
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
    @Override
    public Position next(Position position){
        if (position.item == null ) throw new ListException("Позиция не известна");
        if (position.item == head) return new Position(head.getNext());
        Item prev = Prev(position.item); // Если позиция = last => next не существует, поэтому только надо проверить позиции, которые находятся перед last
        if (prev == null) throw new ListException("Позиция не известна");
        return new Position(prev.getNext().getNext());
    }
    @Override
    public Position previous(Position position){
        if (position.item == null || position.item == head) throw new ListException("Позиция не известна");
        Item result = Prev(position.item);
        if(result == null) throw new ListException("Позиция не известна");
        return new Position(result);
    }
    @Override
    public Position first() { return new Position(head);}
    @Override
    public Position makeNull() {
        head = null;
        return new Position(null);
    }
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
    private Item Last() {
        Item temp = head;
        Item prev = null;
        while (temp != null){
            prev = temp;
            temp = temp.getNext();
        }
        return prev;
    }
}
