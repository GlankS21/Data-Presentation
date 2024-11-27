import CursorList.CursorList;
import CursorList.Position;
import ListResources.Value;
public class TestCursorList {
    public static void main(String[] args) {
        CursorList cursorList = new CursorList();
        initCursorList(cursorList);
        CursorList cursorList2 = new CursorList();
        initCursorList2(cursorList2);
        System.out.println("Начальный список 1: ");
        cursorList.printList();
        System.out.println();

        System.out.println("Начальный список 2: ");
        cursorList2.printList();
        System.out.println();

        delete_dublicate(cursorList);
        System.out.println("После удаления 1: ");
        cursorList.printList();
        System.out.println();

        delete_dublicate(cursorList2);
        System.out.println("После удаления 2: ");
        cursorList2.printList();
        System.out.println();

        CursorList cursorList3 = new CursorList();
        initCursorList3(cursorList3);
        System.out.println("Начальный список 3: ");
        cursorList3.printList();
    }
    static void initCursorList(CursorList CursorList) {
        CursorList.insert(new Value("Name 1", "Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1"), CursorList.first());
        CursorList.insert(new Value("Name 1", "Address 3"), CursorList.end());
        CursorList.insert(new Value("Name 2", "Address 2"), CursorList.end());
        CursorList.insert(new Value("Name 2", "Address 2"), CursorList.end());
        CursorList.insert(new Value("Name 1", "Address 1"), CursorList.end());
        CursorList.insert(new Value("Name 1", "Address 1"), CursorList.end());
        CursorList.insert(new Value("Name 4", "Address 4"), CursorList.next(CursorList.first()));
    }

    static void initCursorList2(CursorList CursorList) {
        CursorList.insert(new Value("Name 30", "Address 30"), CursorList.end());
        CursorList.insert(new Value("Name 15", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 15", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 15", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 15", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 10", "Address 10"), CursorList.next(CursorList.first()));
    }

    static void initCursorList3(CursorList CursorList) {
        CursorList.insert(new Value("Name 300", "Address 30"), CursorList.end());
        CursorList.insert(new Value("Name 150", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 150", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 150", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 150", "Address 15"), CursorList.end());
        CursorList.insert(new Value("Name 100", "Address 10"), CursorList.next(CursorList.first()));
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
    static void delete_dublicate(CursorList cursorList){
        Position cur = cursorList.first();
        while(!cur.equals(cursorList.end())){
            Position next = cursorList.next(cur);
            while(!next.equals(cursorList.end())){
                if(cursorList.retrieve(cur).equals(cursorList.retrieve(next))){
                    (cursorList).delete(next);
                    next =(cursorList).next(cur);
                }
                else next =(cursorList).next(next);
            }
            cur =(cursorList).next(cur);
        }
    }
}
