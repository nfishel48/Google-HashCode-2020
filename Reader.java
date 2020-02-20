/*
This class is used to read in text files and return them as 2D arrays
to make use of data for the google hash code competition
*/

import java.io.*;   
import java.util.*;

public class Reader{
	private String[] specs;
	
	private ArrayList<String[]> rawData;
	
	private BufferedReader file;
    
	private Scanner input;

    public Reader() {
    	input = new Scanner(System.in);
    }
    
    public void readData() {
    	System.out.print("Please enter a file name: ");
    	
    	try {
			file = new BufferedReader(new FileReader(input.nextLine()));
			
			specs = file.readLine().split(" ");
			
			String line;
			
			while ((line = file.readLine()) != null) {
				rawData.add(line.split(" "));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String[] getSpecs() {
    	return this.specs;
    }
    
    public ArrayList<String[]> getRawData(){
    	return this.rawData;
    }
}