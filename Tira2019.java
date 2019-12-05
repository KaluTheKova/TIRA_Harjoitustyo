
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tira2019
{
	private String[] readInput(String filename) {
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
		
		String[] arr = new String[values.size()];

		// Save list values to Object array because yes.
		for (int i = 0; i < values.size(); i++) {
			arr[i] = values.get(i);
		}
		return arr;
	}
	
private void writeOutput()
{
    int esim1=5;
    int	esim2=1;
    String outputrow = esim1+ " "+esim2;
    try {
		 BufferedWriter bw = new BufferedWriter(new FileWriter("outp.txt")); 		
         	 //bw.write(outputrow);
		 //bw.newLine();
		 bw.write(outputrow);
		 bw.close();
    } catch (IOException e) {
    System.err.format("IOException: %s%n", e);
}
  System.out.println("Writing file...");
}

// MAIN
public static void main(String[] args)
	{
		String[] setA;
		String[] setB;

		Tira2019 ht=new Tira2019();   
		setA = ht.readInput("setA.txt");
		setB = ht.readInput("setB.txt");

		Hashtable hashtable = new Hashtable();


		/* OR  */
		// Insert file contents to hashtable
		for (int i = 0; i < setA.length; i++) {
			hashtable.insert(setA[i]);
		}
		for (int i = 0; i < setB.length; i++) {
			hashtable.insert(setB[i]);
		}
		System.out.println("THE TABLE BEFORE OR() IS:");
		hashtable.printTable();

		hashtable.OR();

		System.out.println("CLEARING\n");
		hashtable.clear();

		/* AND */

		System.out.println("Inserting A again\n");
		for (int i = 0; i < setA.length; i++) {
			hashtable.insert(setA[i]);
		}

		System.out.println("THE TABLE BEFORE AND() IS:");
		hashtable.printTable();

		hashtable.AND();
		System.out.println("CLEARING\n");
		hashtable.clear();

		/* XOR */

		// Insert file contents to hashtable
		for (int i = 0; i < setA.length; i++) {
			hashtable.insert(setA[i]);
		}
		for (int i = 0; i < setB.length; i++) {
			hashtable.insert(setB[i]);
		}

		System.out.println("THE TABLE BEFORE XOR() IS:");
		hashtable.printTable();

		// Ohjelma kysyy alkion poistoa ennen tiedostojen kirjoittamista.
		


		/*
		hashtable.insert(1);
		hashtable.insert(2);
		hashtable.insert(3);
		hashtable.insert(4);
		hashtable.insert(5);
		hashtable.insert(6);
		hashtable.insert(7);
		hashtable.insert(8);
		hashtable.insert(9);
		hashtable.insert(10); // 10
		hashtable.insert(3);
		hashtable.insert(1);
		System.out.println();
		System.out.println("TABLE IS:");
		for (int i = 0; i < hashtable.table.length; i++) {
			System.out.println(hashtable.table[i].getValue());
		}

		hashtable.remove(10);
		hashtable.remove(1);
		hashtable.remove(1);
		hashtable.remove(1);
		hashtable.remove(1);
		*/
		/*
		System.out.println();
		System.out.println("TABLE AFTER REMOVING IS:");
		hashtable.printTable();
		*/
	}
}