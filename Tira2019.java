
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tira2019
{
	private List<String> readInput(String filename) {
		String line;
		List<String> values = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader( new FileReader(filename));
			
			while((line = br.readLine()) != null) {
				values.add(line);
				//System.out.println(line); // DEBUG
			}
			br.close();
			
		} catch(IOException e) {
			System.out.println("File not found.");
		}
		//System.out.println(values); // DEBUG
		return values;
	}
	
	private void writeOutput(int[][] data, String outputFilename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename)); 		
			for (int i = 0; i < data.length; i++) {
				if (data[i][0] == 0) // Null skip
					continue;

				int column1 = data[i][0];
				int column2 = data[i][1];
				String outputrow = column1+ " "+column2 + "\n";
				bw.write(outputrow);
			}
			//bw.write(output);
			bw.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		System.out.println("Writing file...");
	}

	// MAIN
	public static void main(String[] args) {
		// Saimme käyttää ArrayListia.
		List<String> setA = new ArrayList<String>();
		List<String> setB = new ArrayList<String>();

		Tira2019 ht=new Tira2019();   
		setA = ht.readInput("setA.txt");
		setB = ht.readInput("setB.txt");

		Hashtable hashtable = new Hashtable();

		/* OR  */

		// Insert file contents to hashtable
		for (int i = 0; i < setA.size(); i++) {
			hashtable.insert(setA.get(i));
		}
		for (int i = 0; i < setB.size(); i++) {
			hashtable.insert(setB.get(i));
		}
		System.out.println("THE TABLE BEFORE OR() IS:");
		hashtable.printTable();

		int[][] resultsOR = new int[hashtable.size()][2];
		resultsOR = hashtable.OR();

		System.out.println("\nCLEARING\n");
		hashtable.clear();

		/* AND */

		System.out.println("Inserting A again\n");
		for (int i = 0; i < setA.size(); i++) {
			hashtable.insert(setA.get(i));
		}

		System.out.println("THE TABLE BEFORE AND() IS:");
		hashtable.printTable();

		int[][] resultsAND = new int[hashtable.size()][2];
		resultsAND = hashtable.AND();

		System.out.println("\nCLEARING\n");
		hashtable.clear();

		/* XOR */

		// Insert file contents to hashtable
		for (int i = 0; i < setA.size(); i++) {
			hashtable.insert(setA.get(i));
		}
		for (int i = 0; i < setB.size(); i++) {
			hashtable.insert(setB.get(i));
		}

		System.out.println("THE TABLE BEFORE XOR() IS:");
		hashtable.printTable();

		int[][] resultsXOR = new int[hashtable.size()][2];
		resultsXOR = hashtable.XOR(setA, setB);

		System.out.println("\nCLEARING\n");
		hashtable.clear();

		// You can remove a value from hash table with this method.
		//hashtable.remove(value);

		System.out.println("Writing results");
		ht.writeOutput(resultsOR, "or.txt");
		ht.writeOutput(resultsAND, "and.txt");
		ht.writeOutput(resultsXOR, "xor.txt");

		System.out.println("Finished writing.");
	}
}