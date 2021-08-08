import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DraftGames {
	private Drafting p1;
	private Drafting p2;
	private int p1Points;
	private int p2Points;
	private String team1;
	private String team2;
	private Drafting winner;
	
	public DraftGames(Drafting p1, Drafting p2, String team1, String team2) {
		this.p1 = p1;
		this.p2 = p2;
		this.team1 = team1;
		this.team2 = team2;
	}
	
	private int randomHigh(int low, int high) {           
		
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        
        return result;
	} 
	
	public String GameWinner() {
		
		if(p1.totalPower() > p2.totalPower()) {
			p1Points = randomHigh(90,108);
			p2Points = randomHigh(88, 105);
			
			if(p1Points > p2Points) {
				return team1;
			} 
			
			if(p1Points == p2Points) {
				GameWinner();
			}
			
			else {
				return team2;
			}
		} else {
			p2Points = randomHigh(90,108);
			p1Points = randomHigh(88, 105);
			
			if(p1Points > p2Points) {
				return team1;
			} 
			
			else if(p1Points == p2Points) {
				GameWinner();
			} 
			
			else {
				return team2;
			}
		}
		
		return null;
	}
	
	public String gameSeries(String teamOne, String teamTwo) {
		ArrayList<String> winning = new ArrayList<String>();
		
		int occurrencesp1 = 0;
		int occurrencesp2 = 0;
		int i = 0;
		
		while(occurrencesp1 < 4 && occurrencesp2 < 4) {
			i = i + 1;
			System.out.println("\nGame " + i + " between " + teamOne + " and team " + teamTwo);
			String winner = GameWinner();
			System.out.println("\nTeam " + winner + " is the winner of Game " + i);
			System.out.println("Score: " + getP1Points() + " : " + getP2Points());	
			winning.add(winner);
			
			occurrencesp1 = Collections.frequency(winning, teamOne);
			occurrencesp2 = Collections.frequency(winning, teamTwo);
		}
		
		
		if(occurrencesp1 > occurrencesp2) {
			winner = p1;
			return teamOne;
		} else {
			winner = p2;
			return teamTwo;
		}
	}
	
	public int getP1Points() {
		return p1Points;
	}
	
	public int getP2Points() {
		return p2Points;
	}
	
	public Drafting getWinner() {
		return winner;
	}
}
