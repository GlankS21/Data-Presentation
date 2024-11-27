package ArrayList;
public class Position{
    public int pos;
    public Position(int pos){
        this.pos = pos;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position pos = (Position) obj;
        return (this.pos == pos.pos);
    }
}