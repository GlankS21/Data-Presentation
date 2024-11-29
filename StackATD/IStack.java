package StackATD;
import Resources.Value;
public interface IStack {
    void MAKENULL(); // делает стек пустым
    Value TOP(); // возвращает элемент (копия) из вершины стека S.
    Value POP(); // удаляет элемент из вершины стека S, и возвращает его в качестве результатата.
    void PUSH(Value x); // вставляет элемент x в вершину стека S.
    boolean EMPTY(); // возвращает значение true, если стек S пустой, и значение false в противном случае.
    boolean FULL(); // возвращает значение true, если стек S полный, и значение false в противном случае.
}
