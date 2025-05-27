package Map;

public interface IMap {
    public void MAKENULL();
    public void ASSIGN(char[] d, char[] r); // делает M(d) равным r независимо от того, как M(d) было определено ранее.
    public boolean COMPUTE(char[] d, char[] r); //возвращает значение true и присваивает переменной r значение M(d), если последнее определено, и возвращает false в противном случае.
}
