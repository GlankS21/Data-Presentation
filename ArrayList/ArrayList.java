package ArrayList;
import ListResources.ListException;
import ListResources.Value;
public class ArrayList implements IList {
    private int last = -1; //последний занятый
    private final Value[] arr = new Value[50];
    public Position end() {
        return new Position(last + 1);
    }
    /**
     * 1) Если позиция неудовлетворительная
     *      1.1. position < 0
     *      1.2. (position > last) || (last == arr.length)) => return;
     * 2) Переместить все элементы с position до last на 1 ячейку назад
     * 3) заменить значение в позиции position на new Value(x)
     * 4) last++;
     */
    public void insert(Value x, Position position) {
        if (position.pos < 0 || position.pos > last + 1 || last == arr.length) return;
        last ++;
        for (int i = last; i > position.pos; i--)
            arr[i] = arr[i - 1];
        arr[position.pos] = new Value(x);
    }
    /**
     * 1) инициализировать переменную i = 0
     * 2) проверка от начала до конца массива, если найден элемент = x => return new Position(i)
     * 3) Если не найдено => возвращается new Position(last + 1);
     */
    public Position locate(Value x) {
        for(int i = 0; i <= last; i++){
            if(arr[i].equals(x)) return new Position(i);
        }
        return new Position(last + 1);
    }
    /**
     * 1) Проверка удовлетворенности позиции
     * 2) возвращается arr[position];
     */
    public Value retrieve(Position position) {
        if(position.pos < 0 || position.pos > last) throw new ListException("Позиция не найдена");
        return arr[position.pos];
    }
    /**
     * 1) Проверка удовлетворенности позиции
     * 2) Переместить все элементы с position до last на 1 ячейку вперед
     * 3) last.increase();
     */
    public void delete(Position position) {
        if (position.pos < 0 || position.pos > last) return;
        for (int i = position.pos; i < last; i++)
            arr[i] = arr[i + 1];
        last--;
    }
    /**
     * 1) Проверка удовлетворенности позиции (0 <= position <= last)
     * 2) возвращается new Position(position.pos + 1);
     */
    public Position next(Position position) {
        if(position.pos < 0 || position.pos > last) throw new ListException("Позиция не найдена");
        return new Position(position.pos + 1);
    }
    /**
     * 1) Проверка удовлетворенности позиции (0 < position <= last)
     * 2) возвращается  new Position(position.pos - 1);
     */
    public Position previous(Position position){
        if(position.pos <= 0 || position.pos > last) throw new ListException("Позиция не найдена");
        return new Position(position.pos - 1);
    }
    public Position first() { return new Position(0);}
    /**
     * 1) last = -1;
     * 2) return new Position(last + 1)
     */
    public Position makeNull() {
        last = -1;
        return new Position(last + 1);
    }
    /**
     *  отображение на экране массив list (с 0 до last)
     */
    public void printList() {
        System.out.println("List : ");
        for(int i = 0; i <= last; ++i)
            arr[i].print();
        System.out.println();
    }
}