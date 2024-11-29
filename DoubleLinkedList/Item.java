package DoubleLinkedList;

import Resources.Value;

public class Item {
    private Value value;
    private Item next;
    private Item prev;
    public Item(Value value) {
        this.value = new Value(value.getName(),value.getAddress());
    }
    public Item getNext(){return next;}
    public void setNext(Item next){ this.next = next;}
    public Item getPrev(){return prev;}
    public void setPrev(Item prev){ this.prev = prev;}
    public Value getValue() {return value;}
    public void setValue(Value value) { this.value = value;}
}
