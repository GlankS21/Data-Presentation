import LinkedList.Position;
import ListResources.Value;
import LinkedList.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        initLinkedList(linkedList);
        System.out.println("Начальный список: ");
        linkedList.printList();
        delete_dublicate(linkedList);
        System.out.println("После удаления: ");
        linkedList.printList();
    }
    static void initLinkedList(LinkedList linkedList){
        linkedList.insert(new Value("Name 1", "Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1 Address 1"), linkedList.first());
        linkedList.insert(new Value("Name 1", "Address 3"), linkedList.first());
        linkedList.insert(new Value("Name 2", "Address 2"), linkedList.first());
        linkedList.insert(new Value("Name 2", "Address 2"), linkedList.first());
        linkedList.insert(new Value("Name 1", "Address 1"), linkedList.first());
        linkedList.insert(new Value("Name 1", "Address 1"), linkedList.first());
        linkedList.insert(new Value("Name 4", "Address 4"), linkedList.next(linkedList.first()));
    }
    /**
     * 1.инициализация position cur - первый элемент
     * 2. Пока !cur.equals(end())
     *  2.1. Position next = следуюший элемент элемента cur
     *  2.2. Пока ! next.equals(end())
     *    2.2.1. Если значение в позиции cur равно значению в позиции next => удаляем позцию next
     *           next = next(cur) - Обновляем ссылку на следующий текущего элемента cur
     *    2.2.2. Наоборот перейдем к следующему элементу: next = next(next)
     *  2.3. перейдем к следующему элементу: cur = next(cur)
     */
    static void delete_dublicate(LinkedList linkedList){
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