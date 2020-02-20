import java.util.ArrayList;
import java.util.Arrays;

public class Library {
	private int numBooks, processTime, shipRate;
	
	public static int[] bookScores;
	
	private ArrayList<Integer> books;
	
	public Library(int numBooks, int processTime, int shipRate) {
		this.numBooks = numBooks;
		this.processTime = processTime;
		this.shipRate = shipRate;
	}
	
	public void setBooks(String[] strs) {
		books = new ArrayList<>();
		
		for (int i = 0; i < strs.length - 1; i++) {
			books.add(bookScores[Integer.parseInt(strs[i])]);
		}
	}
	
	@Override
	public String toString() {
		return "# Books: " + numBooks + " // Processing Time: " + processTime + " // Ship Rate: " + shipRate + "\n\tBooks: " + books.toString();
	}
	
	public int getNumBooks() {
		return this.numBooks;
	}
	public int getProcessTime() {
		return this.processTime;
	}
	public int getShipRate() {
		return this.shipRate;
	}
	
	public ArrayList<Integer> getBooks() {
		return this.books;
	}
}