package Stack.StackATD;
import List.DoubleLinkedList.List;
import Resources.Value;
import Stack.IStack;
public class Stack implements IStack{
    private List list;
    public Stack(){ list = new List();}
    public void MAKENULL(){
        list.makeNull();
    };
    public char TOP(){
        return list.retrieve(list.first()).getChar();
    }
    public char POP(){
        Value value = list.retrieve(list.first());
        list.delete(list.first());
        return value.getChar();
    }
    public void PUSH(char x){
        list.insert(new Value(x), list.first());
    }
    public boolean EMPTY(){
        return list.first().item == null;
    }
    public boolean FULL(){
        return false;
    }
}
