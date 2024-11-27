package LinkedList;

import ListResources.Value;
public class Item {
    private Value value;
    private Item next;
    public Item(Value value){
        this.value = new Value(value.getName(), value.getAddress());
    }
    public Item getNext(){return next;}
    public void setNext(Item next){ this.next = next;}
    public Value getValue() {return value;}
    public void setValue(Value value) { this.value = value;}
}
