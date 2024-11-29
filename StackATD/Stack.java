package StackATD;
import DoubleLinkedList.List;
import Resources.Value;
public class Stack implements IStack{
    private List list;
    public Stack(){ list = new List();}
    public void MAKENULL(){
        list.makeNull();
    };
    public Value TOP(){
        return new Value(list.retrieve(list.first()));
    }
    public Value POP(){
        Value value = list.retrieve(list.first());
        list.delete(list.first());
        return value;
    }
    public void PUSH(Value x){
        list.insert(x, list.first());
    }
    public boolean EMPTY(){
        return list.first().item == null;
    }
    public boolean FULL(){
        return false;
    }
}
