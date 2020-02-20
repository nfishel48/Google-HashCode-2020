import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Library {
	private int id, totalScore, numBooks, processTime, shipRate;
	
	public static int[] bookScores;
	
	private ArrayList<Integer> books;
	
	public Library(int id, int numBooks, int processTime, int shipRate) {
		this.id = id;
		this.numBooks = numBooks;
		this.processTime = processTime;
		this.shipRate = shipRate;
		this.totalScore = 0;
	}
	
	public void setBooks(String[] strs) {
		books = new ArrayList<>();
		
		for (int i = 0; i < strs.length; i++) {
			books.add(Integer.parseInt(strs[i]));
			
			totalScore += bookScores[books.get(i)];
		}
		
		books.sort(new BookComparator());
	}
	public void passDay() {
		this.processTime--;
	}
	
	@Override
	public String toString() {
		return "# Books: " + numBooks + " // Processing Time: " + processTime + " // Ship Rate: " + shipRate + "\n\tBooks: " + books.toString();
	}
	
	public int getId() {
		return this.id;
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
	public int getTotalScore() {
		return this.totalScore;
	}
	
	public ArrayList<Integer> getBooks() {
		return this.books;
	}
}

class BookComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		return Library.bookScores[o2] - Library.bookScores[o1];
	}
}