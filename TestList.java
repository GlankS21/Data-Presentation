import CursorList.List;
import CursorList.Position;
import Resources.Value;
public class TestList {
    public static void main(String[] args) {
        List List = new List();
        initList(List);
        System.out.println("Начальный список: ");
        List.printList();
        delete_dublicate(List);
        System.out.println("После удаления: ");
        List.printList();
    }
    static void initList(List List) {
        List.insert(new Value("Name 1", "Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1"), List.first());
        List.insert(new Value("Name 1", "Address 3"), List.end());
        List.insert(new Value("Name 2", "Address 2"), List.end());
        List.insert(new Value("Name 2", "Address 2"), List.end());
        List.insert(new Value("Name 1", "Address 1"), List.end());
        List.insert(new Value("Name 1", "Address 1"), List.end());
        List.insert(new Value("Name 4", "Address 4"), List.next(List.first()));
    }
    /**
     * 1.инициализация position cur, next; cur - первый элемент
     * 2. Пока !cur.equals(end())
     *  2.1. next = следуюший элемент элемента cur
     *  2.2. Пока ! next.equals(end())
     *    2.2.1. Если значение в позиции cur равно значению в позиции next => удаляем позцию next
     *    2.2.2. Наоборот перейдем к следующему элементу: next = next(next)
     *  2.3. перейдем к следующему элементу: cur = next(cur)
     */
    static void delete_dublicate(List linkedList){
        Position cur = linkedList.first();
        while(!cur.equals(linkedList.end())){
            Position next = linkedList.next(cur);
            while(!next.equals(linkedList.end())){
                if(linkedList.retrieve(cur).equals(linkedList.retrieve(next))){
                    linkedList.delete(next);
                    next = linkedList.next(cur);
                }
                else next = linkedList.next(next);
            }
            cur = linkedList.next(cur);
        }
    }
}