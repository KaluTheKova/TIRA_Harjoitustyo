public class Item {
    /* A class represnting a single item in a hashtable 
    Kinda silly that we don't really need keys here. */
    
    // Attributes
    private String key;
    private String value;

    /*
    // Constructor
    public Item(String key, String value) {
        this.key = key;
        this.value = value;
    }
    */
    // Constructor
    public Item(String value) {
        this.value = value;
    }
    
    // Getters
    private String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    // Setters
    private void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

// https://stackoverflow.com/questions/9958216/simple-hashtable-implementation-using-an-array-in-java