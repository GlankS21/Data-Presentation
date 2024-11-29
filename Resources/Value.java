package Resources;
public class Value {
    private char[] name = new char[20];
    private char[] address = new char[50];
    /**
     * 1. Сделаем копию массива name
     * 2. Сделаем копию массива address
     */
    public Value(char[] name, char[] address){
        for (int i = 0; i < name.length; i++)
            this.name[i] = name[i];
        for (int i = 0; i < address.length; i++)
            this.address[i] = address[i];
    }
    /**
     *  1. name_array = name.toCharArray();
     *  2. address_array = address.toCharArray();
     *  3. Сделаем копию массива name (Или просто Value(name_array, address_array)
     *  4. Сделаем копию массива address
     */
    public Value(String name, String address){
        char[] name_array = name.toCharArray();
        char[] address_array =address.toCharArray();
        for (int i = 0; i < name_array.length && i < this.name.length; i++)
            this.name[i] = name_array[i];
        for (int i = 0; i < address_array.length && i < this.address.length; i++)
            this.address[i] = address_array[i];
    }
    public Value(Value obj) {
        int i = 0;
        while (i < this.name.length && obj.name[i] != '\0') {
            this.name[i] = obj.name[i];
            i++;
        }
        i = 0;
        while (i < this.address.length &&  obj.address[i] != '\0') {
            this.address[i] = obj.address[i];
            i++;
        }
    }
    /**
     * 1. отображение на экране массив name
     * 2. отображение на экране массив address
     */
    public void print(){
        System.out.print("Name: ");
        for (char c : name){
            if (c == '\0') break;
            System.out.print(c);
        }
        System.out.print(" | Address: ");
        for (char c : address){
            if (c == '\0') break;
            System.out.print(c);
        }
        System.out.println();
    }
    public char[] getName(){return name;}
    public char[] getAddress(){return address;}
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null) return false;
        Value obj = (Value) o;
        int i = 0;
        while(i < name.length && obj.name[i] != '\0'){
            if (name[i] != obj.name[i]) return false;
            i++;
        }
        if(i < name.length && name[i] != '\0') return false;
        i = 0;
        while(i < address.length && obj.address[i] != '\0'){
            if (address[i] != obj.address[i]) return false;
            i++;
        }
        if(i < address.length && address[i] != '\0') return false;
        return true;
    }
}