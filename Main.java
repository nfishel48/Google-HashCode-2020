import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {	
	static Reader r;
	
	static FileWriter output;
	
	public static void main(String[] args) {
		r = new Reader();
		r.readData();
		r.printLibraries();
		
		sortLibraries();
		
		try {
			System.out.println("SCORE: " + chooseLibraries());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Sorts libraries by processing time
	static void sortLibraries() {
		r.getLibraries().sort(new LibraryComparator());
	}
	static int chooseLibraries() throws IOException {
		int index = 0, score = 0, numLibraries = 0;
		
		Library currentLibrary = r.getLibraries().get(0);
		
		ArrayList<Library> libraries = new ArrayList<>(),
				shipping = new ArrayList<>();
		
		output = new FileWriter(new File("output.txt"));
		
		for (int i = 0; i < r.getNumDays(); i++) {
			for (int j = 0; j < shipping.size(); j++) {
				Library lib = shipping.get(j);
				
				for (int k = 0; k < lib.getShipRate(); k++) {
					int book = lib.getNextBook();
					
					if (book == -1) {
						shipping.remove(j);
						
						break;
					} else {
						lib.useBook(book);
					}
				}
			}
			
			currentLibrary.passDay();
			
			if (currentLibrary.getProcessTime() == 0) {
				libraries.add(currentLibrary);
				shipping.add(currentLibrary);
				
				numLibraries++;
				index++;
				
				currentLibrary = r.getLibraries().get(index);
			}
		}
		
		output.write("" + libraries.size());
		output.write("\n");
		
		for (int i = 0; i < libraries.size(); i++) {
			Library library = libraries.get(i);
			
			output.write("" + library.getId());
			output.write("\n");

			for (int j = 0; j < library.getUsedBooks().size(); j++) {
				output.write(library.getUsedBooks().get(j) + " ");
			}
			
			output.write("\n");
		}
		
		output.close();
		
		return score;
	}
	
	static boolean isBookAvailable(int index) {
		return r.getBookScores()[index] > -1;
	}
}

class LibraryComparator implements Comparator<Library> {
    @Override
    public int compare(Library l1, Library l2) {
    	double l1Score = ((double)(l1.getNumBooks() / l1.getShipRate()) / l1.getTotalScore() + l1.getProcessTime()),
    			l2Score = ((double)(l2.getNumBooks() / l2.getShipRate()) / l2.getTotalScore() + l2.getProcessTime());
    	
    	if (l1Score > l2Score)
    		return 1;
    	if (l2Score > l1Score)
    		return -1;
    	
    	return 0;
    }
}