import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static Reader r;
	
	public static void main(String[] args) {
		r = new Reader();
		r.readData();
		r.printLibraries();
		
		sortLibraries();
		
		r.printLibraries();
	}
	
	// Sorts libraries by processing time
	static void sortLibraries() {
		r.getLibraries().sort(new LibraryComparator());
	}
}

class LibraryComparator implements Comparator<Library> 
{
    @Override
    public int compare(Library l1, Library l2) {
        return l2.getProcessTime() - l1.getProcessTime();
    }
}