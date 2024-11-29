package ArrayList;
import Resources.Value;
public interface IList {
    Position end();   /** возвращает позицию после последнего */
    void insert(Value value, Position position);  /** вставим x в позицию p */
    Position locate(Value x);   /** возвращает позицию в списке L объекта x */
    Value retrieve(Position position);   /** возвращается объект списка L в позиции p */
    void delete(Position position);   /** удалить элемент списка L в позиции p */
    Position next(Position position);   /** возвращает следующую за p позицию в списке L */
    Position previous(Position position);   /** возвращает предыдущую перед p позицию в списке L */
    Position makeNull();   /** делает список пустым и возвращает позицию после последнего. */
    Position first();   /** возвращает 1-ую позицию в списке L */
    void printList();   /** вывод списка на печать в порядке расположения элементов в списке */
}
