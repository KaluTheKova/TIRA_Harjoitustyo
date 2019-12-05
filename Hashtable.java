import java.util.ArrayList;
import java.util.List;

public class Hashtable {
    // Array based hashtable that uses open addressing for solving collisions.
    
    // Attributes
    Item[] table; // List of Items. Represents our table.
    private int capacity = 10; // Table's storage capacity.
    private float threshold = 0.7f; // Determines when we resize the table.
    private int size = 0; // To keep track of table's current size.
    private static final Item EMPTY = new Item(null); // Empty item to populate the table.
    
    /**
     * Create a new hashtable populated with EMPTY Items.
     * Default capacity 10.
     */
    public Hashtable() {
        table = new Item[capacity];

        // Populate the table with empty entries.
        clear();
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;

        return false;
    }

    public int size() {
        return size;
    }

    /**
     * Clears the table by populating it with EMPTY Items and resetting size to 0.
     */
    public void clear() {
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = EMPTY;
        }
    }

    // Controls how big the resized table should be.
    private void setThreshold(float threshold) {
        this.threshold = threshold;
        capacity = (int) (table.length * threshold);
    }

      
    // Returns a hash.
    private int hash(String value) {
        if (value == null) // Null check
            return 0;

        int hashIndex = Integer.valueOf(value) % table.length; // Convert string to int.
        return hashIndex;
    }
    
    /*** CLASS METHODS ***/
    
    /**
     * Adds parameter value to hash table using linear probing.
     * Resizes the table if size exceeds capacity.
     * @param value to add
     */
    public void insert(String value) {
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

    /**
     * Removes the parameter value from hash table using linear probing.
     * @param value to remove from hash table
     */
    public void remove(String value) {
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
    }
    
    // Increase the table size. 
    private void resize() {
        System.out.println("\nRESIZING!\n"); // DEBUG
        
        int newSize = 2 * table.length;
        capacity = (int) (newSize * threshold);
        Item[] oldTable = table; // Old table from which we'll copy the elements

        table = new Item[capacity]; // Initialise a new table
        for (int i = 0; i < table.length; i++) { // Populate the new table with empty entries.
            table[i] = EMPTY;
        }
        size = 0; // Reset size

        for (int i = 0; i < oldTable.length; i++) {
            insert(oldTable[i].getValue());
        }
    }
    
    /**
     * The first column contains the value of integers in input files
     * The Second column contains the information how many times an integer appears in input files.
     * Returns int array in the format [value][frequency]. Duplicates are not entered into this array.
     * @return int[value][frequency]
     */
    public int[][] OR() {
        int[][] results = new int[table.length][2];
        List<String> duplicates = new ArrayList<String>(); // For storing duplicates
        String valueString;
        int valueInt;
        
        // Linear approach for now
        for (int i = 0; i < table.length; i++) {

            // Skip empty entries
            if (table[i].getValue() == null) {
                continue;
            } else {
                int frequency = 0;
                valueString = table[i].getValue();  // Value to check in duplicates
                valueInt = Integer.valueOf((String) table[i].getValue()); // Value to do the rest with
                
                // If valueString is already in duplicate array, then skip this iteration
                if (duplicates.contains(valueString)) {
                    continue;
                }
                duplicates.add(valueString);
    
                for (int j = 0; j < table.length; j++) { // Check if more instances found
                    if (table[j].getValue() == null) { // Skip empty entries
                        continue;
                    } else {
                        if (Integer.valueOf((String) table[j].getValue()) == valueInt) {
                            frequency++;
                        }
                    }
                }
                results[i][0] = valueInt;
                results[i][1] = frequency;
                frequency = 0; // Reset
            }
        }

        // Print results
        System.out.println("OR OPERATION");
        for (int i = 0; i < table.length; i++) {
            if (results[i][0] == 0) {
                continue;
            }
            System.out.println(results[i][0] + " " + results[i][1]);
        }
        return results;
    }
    
    /**
     * The first column contains the value of integers in input files
     * The Second column contains the row number where an integer appears the first time.
     * Returns int array in the format [value][row number]. Duplicates are not entered into this array.
     * 
     * @return int[value][row number]
     */
    public int[][] AND() {
        int[][] results = new int[table.length][2];
        List<String> appeared = new ArrayList<String>(); // For storing duplicates
        String valueString;
        int valueInt;
        
        // Linear approach for now
        for (int i = 0; i < table.length; i++) {

            // Skip empty entries
            if (table[i].getValue() == null) {
                continue;
            } else {
                valueString = table[i].getValue();  // Value to check in duplicates
                valueInt = Integer.valueOf((String) table[i].getValue()); // Value to do the rest with
                
                // If valueString is already in duplicate array, then skip this iteration
                if (appeared.contains(valueString)) {
                    continue;
                }
                appeared.add(valueString);
    
                results[i][0] = valueInt;
                results[i][1] = i; // Row number
            }
        }

        // Print results
        System.out.println("AND OPERATION");
        for (int i = 0; i < table.length; i++) {
            if (results[i][0] == 0) {
                continue;
            }
            System.out.println(results[i][0] + " " + results[i][1]);
        }
        return results;
    }
    
    /**
     * The first column contains the value of integers in input files
     * The Second column contains 1 if value appears in setA, 2 if value appears in setB.
     * Note that if value appears in both, it will be marked as 1.
     * @return int[value][1 or 2]
     */
    public int[][] XOR(List<String> setA, List<String> setB) {
        int[][] results = new int[table.length][2];
        List<String> duplicates = new ArrayList<String>(); // For storing duplicates
        String value;

        for (int i = 0; i < table.length; i++) {
            if (table[i].getValue() == null) { // Null check. Skip this iteration
                continue;
            }
            value = table[i].getValue();

            if (duplicates.contains(value)) { // Check for duplicates
                continue;
            }

            duplicates.add(value);

            if (setA.contains(value)) {
                results[i][0] = Integer.parseInt(value);
                results[i][1] = 1;
            }
            else if (setB.contains(value)) {
                results[i][0] = Integer.parseInt(value);
                results[i][1] = 2;
            }
        }

        // Print results
        System.out.println("XOR OPERATION");
        for (int i = 0; i < table.length; i++) {
            if (results[i][0] == 0) {
                continue;
            }
            System.out.println(results[i][0] + " " + results[i][1]);
        }
        return results;
    }

    /**
     * A small helper to print the hash table.
     */
    public void printTable() {
        // TODO: Move to try-catch if needed
        if (isEmpty()) { 
            System.out.println("Error! The table is empty.");
            return;
        }
        for (int i = 0; i < table.length; i++) {
            System.out.println(table[i].getValue());
        }
        System.out.println(); // Empty linebreak
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