/*
This class is used to read in text files and return them as 2D arrays
to make use of data for the google hash code competition
*/

import java.io.*;   
import java.util.*;

public class Reader{
	private int numBooks, numLibraries, numDays;
	
	String fileName;
	
	private int[] bookScores;
	
	private String[] specs;
	
	private ArrayList<Library> libraries;
	
	private BufferedReader file;
    
	private Scanner input;

    public Reader() {
    	input = new Scanner(System.in);
    }
    
    public void readData(char letter) {
    	try {
			file = new BufferedReader(new FileReader(letter + ".txt"));
			
			specs = file.readLine().split(" ");
			
			String[] strScores = file.readLine().split(" ");
			
			bookScores = new int[strScores.length];
			Library.bookScores = bookScores;
			
			for (int i = 0; i < strScores.length; i++) {
				bookScores[i] = Integer.parseInt(strScores[i]);
			}
			
			numBooks = Integer.parseInt(specs[0]);
			numLibraries = Integer.parseInt(specs[1]);
			numDays = Integer.parseInt(specs[2]);
			
			libraries = new ArrayList<>();
			
			int index = 0;
			String line;
			
			while ((line = file.readLine()) != null) {
				String temp[] = line.split(" ");
				
				Library lib = new Library(index, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
				
				if (lib.getProcessTime() < numDays) { // Only add the library if the processing time is before the deadline
					lib.setBooks(file.readLine().split(" "));
					
					libraries.add(lib);
				}
				
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void printLibraries() {
    	System.out.println("# Days: " + numDays + " / # Libraries: " + numLibraries);
    	System.out.println("Book Scores: " + Arrays.toString(bookScores) + "\n");
    	
    	for (int i = 0; i < libraries.size(); i++) {
    		System.out.println("Library " + libraries.get(i).getId() + " // " + libraries.get(i));
    	}
    }
    
    public int getNumBooks() {
    	return this.numBooks;
    }
    public int getNumLibraries() {
    	return this.numLibraries;
    }
    public int getNumDays() {
    	return this.numDays;
    }
    
    public String getFileName() {
    	return this.fileName;
    }
    
    public int[] getBookScores() {
    	return this.bookScores;
    }
    
    public String[] getSpecs() {
    	return this.specs;
    }
    
    public ArrayList<Library> getLibraries(){
    	return this.libraries;
    }
}