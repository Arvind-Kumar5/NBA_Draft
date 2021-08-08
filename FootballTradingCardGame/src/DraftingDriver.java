import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.callback.ConfirmationCallback;

public class DraftingDriver {
	
	private static Hashtable<String, Double> draftable;
	private final static int numPlayers = 4;
	
	public DraftingDriver() throws IOException {
		draftable = new Hashtable<String, Double>();
		
		File f1=new File("players.txt"); //Creation of File Descriptor for input file
	      String[] words=null;  //Intialize the word Array
	      FileReader fr = new FileReader(f1);  //Creation of File Reader object
	      BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
	      String s;     
	      int count=0;   //Intialize the word to zero
	      while((s=br.readLine())!=null)   //Reading Content from the file
	      {
	         words=s.split(",");  //Split the word using space
	         
	         Double powerTransform = Double.parseDouble(words[1]);
	         
	         draftable.put(words[0], powerTransform);
	      }
	      
	         fr.close();
	         
	   }
	
	public Hashtable<String, Double> getDraftable() {
		return draftable;
	}
	
	private static void chooseOption(Drafting player, Scanner src, String team) throws IllegalArgumentException, IOException {
		if(team.equals("Computer") || team.equals("AutoDrafter") || team.equals("Robot")) {
			player.draftHighestAvailable(team);
		}else {
			System.out.println("\nTeam " + team + " choose what you want to do");
			System.out.println(" [1] Show board \n [2] Draft Player \n [3] Get Your Draft");
			String selection = src.nextLine();
			
			if(selection.equals("1") || selection.equals("Show board")) {
				player.showBoard();
				chooseOption(player, src, team);
			}
			
			else if(selection.equals("2") || selection.equals("Draft Player")) {
				System.out.println("Choose which player you want to draft:");
				
				String playerDraft = src.nextLine();
				
				try {
					player.draftPlayer(playerDraft);
					draftable.remove(playerDraft);
					System.out.println("Team " + team + " has drafted " + playerDraft + " at " + player.getDraftPick());
					return;	
				} catch (NullPointerException e) {
					System.out.println("There is no player with that name, please try again");
					chooseOption(player, src, team);
				} catch (IllegalArgumentException e) {
					System.out.println("Player has already been drafted, try again");
					chooseOption(player, src, team);
				}
			}
			
			else if(selection.equals("3") || selection.equals("Get Your Draft")) {
				System.out.println(player.getDraft());
				chooseOption(player, src, team);
			}
			
			else {
				System.out.println("Please enter a number 1-3 or name what you want to do from the list");
				chooseOption(player, src, team);
			}
		}	
	}
	
	public static void main(String [] args) {
		System.out.println("========== Welcome to the NBA Drafting Game ========");
		System.out.println("Choose how many people are drafting \n [1] \n [2] \n [3] \n [4]");
		
		Scanner src = new Scanner(System.in);
		
		String numPlayers = src.nextLine();

		if(numPlayers.equals("1")) {
			
			Drafting p1 = new Drafting();
			Drafting p2 = new Drafting();
			Drafting p3 = new Drafting();
			Drafting p4 = new Drafting();
			
			System.out.println("Create your team names");
			System.out.println("Player 1 create team name:");
			String playerOne = src.nextLine();
			
			
			String playerTwo = "Robot";
			String playerThree = "Computer";
			String playerFour = "AutoDrafter";
			
			List<String> solution = new ArrayList<String>();
			
			solution.add(playerOne);
			solution.add(playerTwo);
			solution.add(playerThree);
			solution.add(playerFour);
			
			Collections.shuffle(solution);
			
			String teamOne = solution.get(0);
			String teamTwo = solution.get(1);
			String teamThree = solution.get(2);
			String teamFour = solution.get(3);
			
			System.out.println("\nDraft Order: 1) " + teamOne + " 2) " + teamTwo + " 3) "  + teamThree +
					 " 4) "+ teamFour + "\n");
			
			System.out.println("Press x to start draft");
			String confirm = src.nextLine();
			
			if(confirm.equals("x")) {
				final int totalDraft = draftable.size();
				for(int i = 0; i < totalDraft/(DraftingDriver.numPlayers * 2); i++) {
					try {
						chooseOption(p1, src, teamOne);
						
						chooseOption(p2, src, teamTwo);
						
						chooseOption(p3, src, teamThree);
						
						chooseOption(p4, src, teamFour);
						
						System.out.println("");
						
						chooseOption(p4, src, teamFour);
						
						chooseOption(p3, src, teamThree);
						
						chooseOption(p2, src, teamTwo);
						
						chooseOption(p1, src, teamOne);
						
						System.out.println("");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
				
				System.out.println("\nTeam " + teamOne + "'s draft:" + p1.getDraft());
				System.out.println("\nTeam " + teamTwo + "'s draft:" + p2.getDraft());
				System.out.println("\nTeam " + teamThree + "'s draft:" + p3.getDraft());
				System.out.println("\nTeam " + teamFour + "'s draft:" + p4.getDraft());
				
				System.out.println("\nPress x to get into game series");
				String confirmation = src.nextLine();
				
				if(confirmation.equals("x")) {
					DraftGames d1 = new DraftGames(p1, p2, teamOne, teamTwo);
					String winner = d1.gameSeries(teamOne, teamTwo);
					
					System.out.println("The winner of the first series is Team " + winner);
					
					System.out.println("\nPress x to get into game series");
					String confirmation2 = src.nextLine();
					
					if(confirmation2.equals("x")) {
						DraftGames d2 = new DraftGames(p3, p4, teamThree, teamFour);
						String winner2 = d2.gameSeries(teamThree, teamFour);
						
						System.out.println("The winner of the second series is Team " + winner2);
						
						System.out.println("\nPress x to get into game series");
						String confirmation3 = src.nextLine();
						
						if(confirmation3.equals("x")) {
							DraftGames d3 = new DraftGames(d1.getWinner(), d2.getWinner(), winner, winner2);
							String winner3 = d3.gameSeries(winner, winner2);
							
							System.out.println("\nThe winner of the finals is Team " + winner3);	
						}
					}	
				}
			}
			}
			
			else if(numPlayers.equals("2")) {
				
				Drafting p1 = new Drafting();
				Drafting p2 = new Drafting();
				Drafting p3 = new Drafting();
				Drafting p4 = new Drafting();
				
				System.out.println("Create your team names");
				
				System.out.println("Player 1 create team name:");
				String playerOne = src.nextLine();
				
				System.out.println("Player 2 create team name:");
				String playerTwo = src.nextLine();
				
				String playerThree = "Computer";
				String playerFour = "AutoDrafter";
				
				final int totalDraft = draftable.size();
				
				
				
				List<String> solution = new ArrayList<String>();
				
				solution.add(playerOne);
				solution.add(playerTwo);
				solution.add(playerThree);
				solution.add(playerFour);
				
				Collections.shuffle(solution);
				
				String teamOne = solution.get(0);
				String teamTwo = solution.get(1);
				String teamThree = solution.get(2);
				String teamFour = solution.get(3);
				
				System.out.println("\nDraft Order: 1) " + teamOne + " 2) " + teamTwo + " 3) "  + teamThree +
						 " 4) "+ teamFour + "\n");
				
				System.out.println("Press x to start draft");
				String confirm = src.nextLine();
				
				if(confirm.equals("x")) {
					for(int i = 0; i < totalDraft/(DraftingDriver.numPlayers * 2); i++) {
						try {
						
							
							chooseOption(p1, src, teamOne);
							
							chooseOption(p2, src, teamTwo);
							
							chooseOption(p3, src, teamThree);
							
							chooseOption(p4, src, teamFour);
							
							System.out.println("");
							
							chooseOption(p4, src, teamFour);
							
							chooseOption(p3, src, teamThree);
							
							chooseOption(p2, src, teamTwo);
							
							chooseOption(p1, src, teamOne);
							
							System.out.println("");
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
					
					System.out.println("\nTeam " + teamOne + "'s draft:" + p1.getDraft());
					System.out.println("\nTeam " + teamTwo + "'s draft:" + p2.getDraft());
					System.out.println("\nTeam " + teamThree + "'s draft:" + p3.getDraft());
					System.out.println("\nTeam " + teamFour + "'s draft:" + p4.getDraft());
				
					System.out.println("\nPress x to get into game series");
					String confirmation = src.nextLine();
					
					if(confirmation.equals("x")) {
						System.out.println("=============================================");
						
						DraftGames d1 = new DraftGames(p1, p2, teamOne, teamTwo);
						String winner = d1.gameSeries(teamOne, teamTwo);
						
						System.out.println("The winner of the first series is Team " + winner);
						
						System.out.println("=============================================");
						
						System.out.println("\nPress x to get into game series");
						String confirmation2 = src.nextLine();
						
						if(confirmation2.equals("x")) {
							DraftGames d2 = new DraftGames(p3, p4, teamThree, teamFour);
							String winner2 = d2.gameSeries(teamThree, teamFour);
							
							System.out.println("The winner of the second series is Team " + winner2);
							
							System.out.println("=============================================");
							
							System.out.println("\nPress x to get into game series");
							String confirmation3 = src.nextLine();
							
							if(confirmation3.equals("x")) {
								DraftGames d3 = new DraftGames(d1.getWinner(), d2.getWinner(), winner, winner2);
								String winner3 = d3.gameSeries(winner, winner2);
								
								System.out.println("\nThe winner of the finals is Team " + winner3);
							}
						}	
					}
				}
			
		}
		
			else if(numPlayers.equals("3")) {
				
				Drafting p1 = new Drafting();
				Drafting p2 = new Drafting();
				Drafting p3 = new Drafting();
				Drafting p4 = new Drafting();
				
				System.out.println("Create your team names");
				
				System.out.println("Player 1 create team name:");
				String playerOne = src.nextLine();
				
				System.out.println("Player 2 create team name:");
				String playerTwo = src.nextLine();
				
				System.out.println("Player 3 create team name:");
				String playerThree = src.nextLine();
				
				String playerFour = "Computer";
				
				final int totalDraft = draftable.size();
				
				List<String> solution = new ArrayList<String>();
				
				solution.add(playerOne);
				solution.add(playerTwo);
				solution.add(playerThree);
				solution.add(playerFour);
				
				Collections.shuffle(solution);
				
				String teamOne = solution.get(0);
				String teamTwo = solution.get(1);
				String teamThree = solution.get(2);
				String teamFour = solution.get(3);
				
				System.out.println("\nDraft Order: 1) " + teamOne + " 2) " + teamTwo + " 3) " + teamThree
						+ " 4) " + teamFour + "\n");
				
				System.out.println("Press x to start draft");
				String confirm = src.nextLine();
				
				if(confirm.equals("x")) {
					for(int i = 0; i < totalDraft/(DraftingDriver.numPlayers * 2); i++) {
						try {
							
							chooseOption(p1, src, teamOne);
							
							chooseOption(p2, src, teamTwo);
							
							chooseOption(p3, src, teamThree);
							
							chooseOption(p4, src, teamFour);
							
							System.out.println("");
							
							chooseOption(p4, src, teamFour);
							
							chooseOption(p3, src, teamThree);
							
							chooseOption(p2, src, teamTwo);
							
							chooseOption(p1, src, teamOne);
							
							System.out.println("");
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
					
					System.out.println("\nTeam " + teamOne + "'s draft:" + p1.getDraft());
					System.out.println("\nTeam " + teamTwo + "'s draft:" + p2.getDraft());
					System.out.println("\nTeam " + teamThree + "'s draft:" + p3.getDraft());
					System.out.println("\nTeam " + teamFour + "'s draft:" + p4.getDraft());
				
					System.out.println("\nPress x to get into game series");
					String confirmation = src.nextLine();
					
					if(confirmation.equals("x")) {
						System.out.println("=============================================");
						DraftGames d1 = new DraftGames(p1, p2, teamOne, teamTwo);
						String winner = d1.gameSeries(teamOne, teamTwo);
						
						System.out.println("The winner of the first series is Team " + winner);
						
						System.out.println("=============================================");
						
						System.out.println("\nPress x to get into game series");
						String confirmation2 = src.nextLine();
						
						if(confirmation2.equals("x")) {
							DraftGames d2 = new DraftGames(p3, p4, teamThree, teamFour);
							String winner2 = d2.gameSeries(teamThree, teamFour);
							
							System.out.println("The winner of the second series is Team " + winner2);
							
							System.out.println("=============================================");
							
							System.out.println("\nPress x to get into game series");
							String confirmation3 = src.nextLine();
							
							if(confirmation3.equals("x")) {
								DraftGames d3 = new DraftGames(d1.getWinner(), d2.getWinner(), winner, winner2);
								String winner3 = d3.gameSeries(winner, winner2);
								
								System.out.println("\nThe winner of the finals is Team " + winner3);	
							}
						}	
					}
				}
				
		}
		
			else if(numPlayers.equals("4")) {
				
				Drafting p1 = new Drafting();
				Drafting p2 = new Drafting();
				Drafting p3 = new Drafting();
				Drafting p4 = new Drafting();
				
				System.out.println("Create your team names");
				
				System.out.println("Player 1 create team name:");
				String playerOne = src.nextLine();
				
				System.out.println("Player 2 create team name:");
				String playerTwo = src.nextLine();
				
				System.out.println("Player 3 create team name:");
				String playerThree = src.nextLine();
				
				System.out.println("Player 4 create team name:");
				String playerFour = src.nextLine();
				
				final int totalDraft = draftable.size();
				
				List<String> solution = new ArrayList<String>();
				solution.add(playerOne);
				solution.add(playerTwo);
				solution.add(playerThree);
				solution.add(playerFour);
				
				Collections.shuffle(solution);
				
				String teamOne = solution.get(0);
				String teamTwo = solution.get(1);
				String teamThree = solution.get(2);
				String teamFour = solution.get(3);
				
				System.out.println("\nDraft Order: 1) " + teamOne + " 2) " + teamTwo + " 3) " + teamThree
						+ " 4) " + teamFour + "\n");
				
				System.out.println("Press x to start draft");
				String confirm = src.nextLine();
				
				if(confirm.equals("x")) {
					for(int i = 0; i < totalDraft/(DraftingDriver.numPlayers * 2); i++) {
						try {
							chooseOption(p1, src, teamOne);
							
							chooseOption(p2, src, teamTwo);
							
							chooseOption(p3, src, teamThree);
							
							chooseOption(p4, src, teamFour);
							
							System.out.println("");
							
							chooseOption(p4, src, teamFour);
							
							chooseOption(p3, src, teamThree);
							
							chooseOption(p2, src, teamTwo);
							
							chooseOption(p1, src, teamOne);
							
							System.out.println("");
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
					
					System.out.println("Team " + teamOne + "'s draft:" + p1.getDraft() + "\n");
					System.out.println("\nTeam " + teamTwo + "'s draft:" + p2.getDraft() + "\n");
					System.out.println("\nTeam " + teamThree + "'s draft:" + p3.getDraft() + "\n");
					System.out.println("\nTeam " + teamFour + "'s draft:" + p4.getDraft() + "\n");
					
					System.out.println("\nPress x to get into 7 game series");
					String confirmation = src.nextLine();
					
					if(confirmation.equals("x")) {
						System.out.println("=============================================");
						DraftGames d1 = new DraftGames(p1, p2, teamOne, teamTwo);
						String winner = d1.gameSeries(teamOne, teamTwo);
						
						System.out.println("The winner of the first series is Team " + winner);
						
						System.out.println("=============================================");
						
						System.out.println("\nPress x to get into 7 game series");
						String confirmation2 = src.nextLine();
						
						if(confirmation2.equals("x")) {
							DraftGames d2 = new DraftGames(p3, p4, teamThree, teamFour);
							String winner2 = d2.gameSeries(teamThree, teamFour);
							
							System.out.println("The winner of the second series is Team " + winner2);
							
							System.out.println("=============================================");
							
							System.out.println("\nPress x to get into 7 game series");
							String confirmation3 = src.nextLine();
							
							if(confirmation3.equals("x")) {
								DraftGames d3 = new DraftGames(d1.getWinner(), d2.getWinner(), winner, winner2);
								String winner3 = d3.gameSeries(winner, winner2);
								
								System.out.println("The winner of the finals is Team " + winner3);	
							}
						}	
					}
				}
				
				
				
		}
		
		src.close();
		
		
		
	System.out.println("\nThank you for drafting!");
	}
	  
	}

