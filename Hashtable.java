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
    
    // Constructor.
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

    // Populates the table with EMPTY items
    public void clear() {
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
        //int hashIndex =  (int) value % table.length; // Convert object to int.
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
    
    /**
     * The first column contains the value of integers in input files
     * The Second column contains the information how many times an integer appears in input files.
     * Returns int array in the format [value][how many times occurs]. Duplicates are not entered into this array.
     * @return int[][]
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
                //System.out.println("EMPTYYYYYY I"); // DEBUG
                continue;
            } else {
                int counter = 0;
                valueString = table[i].getValue();  // Value to check in duplicates
                valueInt = Integer.valueOf((String) table[i].getValue()); // Value to do the rest with
                
                // If valueString is already in duplicate array, then skip this iteration
                if (duplicates.contains(valueString)) {
                    //System.out.println("DUPLICATE " + valueString); // DEBUG
                    continue;
                }
                duplicates.add(valueString);
    
                for (int j = 0; j < table.length; j++) { // Check if more instances found
                    // Skip empty entries
                    if (table[j].getValue() == null) {
                        //System.out.println("EMPTYYYYYY J"); // DEBUG
                        continue;
                    } else {
                        if (Integer.valueOf((String) table[j].getValue()) == valueInt) {
                            counter++;
                        }
                    }
                }
                results[i][0] = valueInt;
                results[i][1] = counter;
                counter = 0; // Reset
            }
        }

        // Print results
        System.out.println("OR OPERATION");
        for (int i = 0; i < table.length; i++) {
            if (results[i][0] == 0) {
                //System.out.println("SKIPPISTAE"); // DEBUG
                continue;
            }
            System.out.println(results[i][0] + " " + results[i][1]);
        }
        return results;
    }
    
    public int AND() {
        return 0;
    }
    
    public int XOR() {
        return 0;
    }

    /**
     * A small helper to print the hash table.
     */
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