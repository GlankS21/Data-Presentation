import ArrayList.ArrayList;
import ArrayList.Position;
import ListResources.Value;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        initArrayList(arrayList);
        System.out.println("Начальный список: ");
        arrayList.printList();
        delete_dublicate(arrayList);
        System.out.println("После удаления: ");
        arrayList.printList();
    }
    static void initArrayList(ArrayList arrayList) {
        arrayList.insert(new Value("Name 1", "Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1"), arrayList.first());
        arrayList.insert(new Value("Name 1", "Address 3"), arrayList.first());
        arrayList.insert(new Value("Name 2", "Address 2"), arrayList.end());
        arrayList.insert(new Value("Name 2", "Address 2"), arrayList.end());
        arrayList.insert(new Value("Name 1", "Address 1"), arrayList.end());
        arrayList.insert(new Value("Name 1", "Address 1"), arrayList.end());
        arrayList.insert(new Value("Name 4", "Address 4"), arrayList.next(arrayList.first()));
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
    static void delete_dublicate(ArrayList arrayList){
        Position cur = arrayList.first();
        while(!cur.equals(arrayList.end())){
            Position next = arrayList.next(cur);
            while(!next.equals(arrayList.end())){
                if(arrayList.retrieve(cur).equals(arrayList.retrieve(next))) {
                    arrayList.delete(next);
                }
                else next = arrayList.next(next);
            }
            cur = arrayList.next(cur);
        }
    }
}