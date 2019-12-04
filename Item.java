public class Item {
    /* A class represnting a single item in a hashtable 
    Kinda silly that we don't really need keys here. */
    
    // Attributes
    private Object key;
    private Object value;

    /*
    // Constructor
    public Item(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    */
    // Constructor
    public Item(Object value) {
        this.value = value;
    }
    
    // Getters
    private Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    // Setters
    private void setKey(Object key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

// https://stackoverflow.com/questions/9958216/simple-hashtable-implementation-using-an-array-in-java