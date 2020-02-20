import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static Reader r;
	
	public static void main(String[] args) {
		r = new Reader();
		r.readData();
		r.printLibraries();
		
		sortLibraries();
		chooseLibraries();
		
		r.printLibraries();
	}
	
	// Sorts libraries by processing time
	static void sortLibraries() {
		r.getLibraries().sort(new LibraryComparator());
	}
	static void chooseLibraries() {
		int index = 0;
		
		Library currentLibrary = r.getLibraries().get(0);
		
		ArrayList<Library> libraries = new ArrayList<>(),
							processing = new ArrayList<>();
		
		
		for (int i = 0; i < r.getNumDays(); i++) {
			currentLibrary.passDay();
			
			if (currentLibrary.getProcessTime() == 0) {
				processing.add(currentLibrary);
				
				index++;
				
				currentLibrary = r.getLibraries().get(index);
			}
		}
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