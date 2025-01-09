package List.LinkedList;

public class Position {
    public Item item;
    Position(Item item){ this.item = item;}
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position pos = (Position) obj;
        return (this.item == pos.item);
    }
}
