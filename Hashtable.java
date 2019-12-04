public class Hashtable {
    // Array based hashtable that uses open addressing for solving collisions.
    
    // Attributes
    Item[] table; // List of Items. Represents our table.
    private int capacity = 10; // Table's storage capacity.
    private float threshold = 0.7f; // Determines when we resize the table.
    private int size = 0; // To keep track of table's current size.
    private static final Item EMPTY = new Item(null); // Empty item to populate the table.
    
    // Constructor.
    public Hashtable() {
        table = new Item[capacity];

        // Populate the table with empty entries.
        for (int i = 0; i < table.length; i++) {
            table[i] = EMPTY;
        }
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;

        return false;
    }

    // Controls how big the resized table should be.
    private void setThreshold(float threshold) {
        this.threshold = threshold;
        capacity = (int) (table.length * threshold);
    }

      
    // Returns a hash.
    private int hash(Object value) {
        //int hashIndex =  (int) value % table.length; // Convert object to int.
        int hashIndex = Integer.valueOf((String)value) % table.length; // Convert string object to int.
        return hashIndex;
    }
    
    /*** CLASS METHODS ***/
    
    // Insertion. Linear probing.
    public void insert(Object value) {
        int hashIndex = hash(value);

        while (true) {          
            //System.out.println("Hash is: " + hashIndex); // DEBUG
            if (table[hashIndex] == EMPTY || table[hashIndex] == null) {
                table[hashIndex] = new Item(value);
                size++;
                //System.out.println("Inserted: " + value + "\nBreaking."); // DEBUG
                //System.out.println("Size: " + size + "\n"); // DEBUG
                break;
            }
            hashIndex = (hashIndex + 1) % table.length; // Linear probing.
        }
        // Resize if needed.
        if (size >= capacity) {
            resize();
        }
    }
    // Remove. Linear probing.
    public void remove(Object value) {
        int hashIndex = hash(value);
        int counter = 0; // To prevent infinite loop

        while (hashIndex < table.length) {
            if (table[hashIndex].getValue() == value) { // == check for exact same object
                table[hashIndex] = EMPTY;
                size--;
                System.out.println("Removed: " + value); // DEBUG
                break;
            }
            if (counter >= table.length * 2) { // Error checking to prevent infinite loop
                System.out.println("Error in remove! " + value + " not found in table.");
                break;
            }
            hashIndex = (hashIndex + 1) % table.length; // Linear probing.
            counter++;
        }
        // Resize?
    } 

    public void find(Object value) {
        
    }
    
    // Increase the table size. 
    private void resize() {
        System.out.println("RESIZING!"); // DEBUG
        
        int newSize = 2 * table.length;
        capacity = (int) (newSize * threshold);
        Item[] oldTable = table; // Old table from which we'll copy the elements

        table = new Item[capacity]; // Initialise a new table
        for (int i = 0; i < table.length; i++) { // Populate the new table with empty entries.
            table[i] = EMPTY;
        }
        size = 0; // Reset size

        for (int i = 0; i < oldTable.length; i++) {
            //System.out.println("OldTable:" +oldTable[i].getValue()); // DEBUG
            insert(oldTable[i].getValue());
        }
    }
    
    
    public int OR() {
        Object[][] results = new Object[table.length][2];
        
        // Linear approach for now
        for (int i = 0; i < table.length; i++) {
            int counter = 0;
            Object value = table[i].getValue(); // Value to check

            for (int j = 0; j < table.length; j++) { // Check if more instances found
                if (table[j].getValue() == value) {
                    counter++;
                }
            }
            results[i][0] = value;
            results[i][1] = counter;
            counter = 0; // Reset
        }

        // Print test
        System.out.println("OR OPERATION");
        for (int i = 0; i < table.length; i++) {
            System.out.println(results[i][0] + " " + results[i][1]);
        }

        return 0;
    }
    
    public int AND() {
        return 0;
    }
    
    public int XOR() {
        return 0;
    }

    // Print table lines [value, value]
    public void printTable() {
        // TODO: Move to try-catch if needed
        if (isEmpty()) { 
            System.out.println("Error! Table is empty.");
            return;
        }
        for (int i = 0; i < table.length; i++) {
            System.out.println(table[i].getValue());
        }
    }
}



//https://dzone.com/articles/custom-hashmap-implementation-in-java
// https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
// https://stackoverflow.com/questions/9958216/simple-hashtable-implementation-using-an-array-in-java
// https://odino.org/this-is-how-a-dumb-hashtable-works/
// http://www.algolist.net/Data_structures/Hash_table/Dynamic_resizing
// http://www.algolist.net/Data_structures/Hash_table/Open_addressing
// https://intelligentjava.wordpress.com/tag/open-addressing/
// https://www.tutorialspoint.com/data_structures_algorithms/hash_data_structure.htm