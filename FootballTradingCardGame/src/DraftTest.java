import java.io.IOException;
import java.util.Scanner;

public class DraftTest {
	public static void testDrafting(String name) {
		Drafting d = new Drafting();
		
		try {
			d.draftPlayer(name);
		} catch (IOException e) {
			System.out.println("No such file");
			e.printStackTrace();
		}
	}
	
	public static void testShowBoard() {
		Drafting d = new Drafting();
		
		d.showBoard();
	}
	
	/*
	 * public static void testShowMyTeam() { Teams t = new Teams(); Drafting d = new
	 * Drafting();
	 * 
	 * try { d.showBoard(); t.showMyTeam(); } catch (IOException e) {
	 * System.out.println("No such file"); e.printStackTrace(); }
	 * 
	 * }
	 */
	
	public static void main(String[] args) {
		Scanner src = new Scanner(System.in);
		
		testDrafting(src.nextLine());
		testShowBoard();
		//testShowMyTeam();
		
		src.close();
	}
}
