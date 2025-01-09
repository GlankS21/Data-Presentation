package Resources;
public class Value {
    private char[] name = new char[20];
    private char[] address = new char[50];
    private char character;
    /**
     * 1. Сделаем копию массива name
     * 2. Сделаем копию массива address
     */
    public Value(char character){
        this.character = character;
    }
    public Value(char[] name, char[] address){
        for(int i = 0; i < name.length && name[i] != '\0'; i++)
            this.name[i] = name[i];
        for (int i = 0; i < address.length && address[i] != '\0'; i++)
            this.address[i] = address[i];
    }
    public Value(String name, String address){
        char[] name_array = name.toCharArray();
        char[] address_array = address.toCharArray();
        for (int i = 0; i < name_array.length && i < this.name.length; i++)
            this.name[i] = name_array[i];
        for (int i = 0; i < address_array.length && i < this.address.length; i++)
            this.address[i] = address_array[i];
    }
    public Value(Value obj) {
        if(obj.character == '\0'){
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
        }else{
            this.character = obj.character;
        }
    }
    public void print(){
        if(character == '\0'){
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
        }else{ System.out.print(character); }
    }
    public char[] getName(){
        return name;
    }
    public char[] getAddress(){
        return address;
    }
    public void setAddress(char[] address){
        int i;
        for (i = 0; i < address.length && address[i] != '\0'; i++)
            this.address[i] = address[i];
        if(i < address.length){
            this.address[i] = '\0';
        }
    }
    public char getChar(){return character;}
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null) return false;
        Value obj = (Value) o;
        return CompareCharArray(this.name, obj.name) && CompareCharArray(this.address, obj.address);
    }
    public static boolean CompareCharArray(char[] array1, char[] array2){
        int i;
        for(i = 0; i < array2.length && i < array1.length; i++){
            if(array1[i] != array2[i]) return false;
            if(array1[i] == '\0' || array2[i] == '\0') return true;
        }
//        return (array1.length == array2.length);
        if(array2.length > array1.length){
            return array2[i] == '\0';
        }
        if(array2.length < array1.length){
            return array1[i] == '\0';
        }
        return true;
    }
}