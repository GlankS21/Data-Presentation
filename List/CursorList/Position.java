package List.CursorList;

public class Position {
    public int position;
    public Position(int position){
        this.position = position;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position pos = (Position) obj;
        return (this.position == pos.position);
    }

}
