import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {	
	static int numDays, score, totalValue = 0, totalLibraries;
	
	static Reader r;
	
	static FileWriter output;
	
	public static void main(String[] args) {
		char[] letters = new char[] {'a', 'b', 'c', 'd', 'e', 'f'};
		
		for (int i = 0; i < letters.length; i++) {
			r = new Reader();
			r.readData(letters[i]);
			
			totalLibraries = r.getLibraries().size();
			
			for (int j = 0; j < r.getLibraries().size(); j++) {
				totalValue += r.getLibraries().get(j).getValue();
			}
			
			try {
				output = new FileWriter(new File(letters[i] + "_output.txt"));
				
				numDays = r.getNumDays();
				
				sortLibraries();
				r.printLibraries();
				
				score += chooseLibraries();
				
				System.out.println("SCORE: " + score);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		
		for (int i = 0; i < r.getNumDays(); i++) {
			for (int j = 0; j < shipping.size(); j++) {
				Library lib = shipping.get(j);
				
				for (int k = 0; k < lib.getShipRate(); k++) {
					int book = lib.getNextBook();
					
					if (book == -1) {
						shipping.remove(j);
						
						break;
					} else {
						if (!libraries.contains(lib))
							libraries.add(lib);
						
						score += Library.bookScores[book];
						lib.useBook(book);
					}
				}
			}
			
			currentLibrary.passDay();
			
			if (currentLibrary.getProcessTime() == 0) {
				shipping.add(currentLibrary);
				
				numLibraries++;
				index++;
				
				if (index < r.getLibraries().size())
					currentLibrary = r.getLibraries().get(index);
			}
		}
		
		output.write("" + libraries.size());
		output.write("\n");
		
		for (int i = 0; i < libraries.size(); i++) {
			Library library = libraries.get(i);
			
			output.write("" + library.getId() + " " + library.getUsedBooks().size());
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
    	double l1Score = ((double)(l1.getNumBooks() / l1.getShipRate()) / l1.getTotalScore() + l1.getProcessTime()) * Library.bookScores[l1.getBooks().get(0)],
    			l2Score = ((double)(l2.getNumBooks() / l2.getShipRate()) / l2.getTotalScore() + l2.getProcessTime()) * Library.bookScores[l2.getBooks().get(0)];
    	
    	if (l1Score > l2Score)
    		return 1;
    	if (l1Score < l2Score)
    		return -1;
    	
    	return 0;
    }
}