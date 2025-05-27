package Poset;

public class PoSetTest {
    public static void main(String[] args){
        PoSet Poset = new PoSet();
        int[][] values = {
                {1, 2}, {2, 4}, {4, 6}, {2, 10}, {4, 8}, {6, 3}, {1, 3},
                {3, 5}, {5, 8}, {7, 5}, {7, 9}, {9, 4}, {9, 10}, {3, 4}
        };

        Poset.Init(values);
        Poset.print();
        System.out.println("------------");
        if (Poset.TopoSort()) {
            Poset.print();
        } else{
            System.out.println("Невозможно отсортировать множество");
        }
    }
}
