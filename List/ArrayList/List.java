package List.ArrayList;
import Resources.ListException;
import Resources.Value;
public class List implements IList {
    private int last = -1; //последний занятый
    private final Value[] arr = new Value[50];
    public Position end() {
        return new Position(last + 1);
    }
    public void insert(Value x, Position position) {
        if (position.pos < 0 || position.pos > last + 1 || last == arr.length) return;
        last ++;
        for (int i = last; i > position.pos; i--)
            arr[i] = arr[i - 1];
        arr[position.pos] = new Value(x);
    }
    public Position locate(Value x) {
        for(int i = 0; i <= last; i++){
            if(arr[i].equals(x)) return new Position(i);
        }
        return new Position(last + 1);
    }
    public Value retrieve(Position position) {
        if(position.pos < 0 || position.pos > last) throw new RuntimeException();
        return arr[position.pos];
    }
    public void delete(Position position) {
        if (position.pos < 0 || position.pos > last) return;
        for (int i = position.pos; i < last; i++)
            arr[i] = arr[i + 1];
        last--;
    }
    public Position next(Position position) {
        if(position.pos < 0 || position.pos > last) throw new ListException("Позиция не найдена");
        return new Position(position.pos + 1);
    }
    public Position previous(Position position){
        if(position.pos <= 0 || position.pos > last) throw new ListException("Позиция не найдена");
        return new Position(position.pos - 1);
    }
    public Position first() { return new Position(0);}
    public Position makeNull() {
        last = -1;
        return new Position(last + 1);
    }
    public void printList() {
        System.out.println("List : ");
        for(int i = 0; i <= last; ++i)
            arr[i].print();
        System.out.println();
    }
}