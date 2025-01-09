package Map;
import Map.MapLinked.*;
public class TestMap {
    public static void main(String[] args) {
        Map map = new Map();
        map.ASSIGN("Name1".toCharArray(), "Address1".toCharArray());
        map.ASSIGN("Name2".toCharArray(), "Address2".toCharArray());
        map.ASSIGN("Name3".toCharArray(), "Address3".toCharArray());
        map.print();
        char[] r = new char[50];
        if(map.COMPUTE("Name1".toCharArray(),r)){
            String s = "";
            for(int  i = 0; i < r.length && r[i] != '\0'; i++){
                s += r[i];
            }
            System.out.println("r : " + s);
        }
//        map.print();
    }
}