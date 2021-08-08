import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NBAPlayers {
	private double power = 0;
	private String player;
	//Player drafted
	public NBAPlayers (String name) throws IOException {
		try {
			player = getNBAPlayers(name);
			powerCheck(player);
				
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	//Drafting a specific player 
	public String getNBAPlayers(String name) throws IOException {
		
		 File f1=new File("players.txt"); //Creation of File Descriptor for input file
	      String[] words=null;  //Intialize the word Array
	      FileReader fr = new FileReader(f1);  //Creation of File Reader object
	      BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
	      String s;     
	      int count=0;   //Intialize the word to zero
	      while((s=br.readLine())!=null)   //Reading Content from the file
	      {
	         words=s.split(",");  //Split the word using space
	         if (words[0].equals(name)) {
	        	 power = Double.parseDouble(words[1]);
	        	 return words[0];
	         }
	      }
	      
	         fr.close();
	         
	         return null;
	   }
	
	public void powerCheck(String player) {
		if(player.equals("Giannis Antentokoumpo")) {
			Random r = new Random();
			double low = 53.8;
			this.power = r.nextInt(5) + low;	
			return;
		}
		
		if(player.equals("James Harden")) {
			Random r = new Random();
			double low = 52.4;
			this.power = r.nextInt(5) + low;	
			return;
		}
		
		if(player.equals("Lebron James")) {
			Random r = new Random();
			double low = 50.1;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Kawhi Leonard")) {
			Random r = new Random();
			double low = 48.8;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Zion Williamson")) {
			Random r = new Random();
			double low = 32.6;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Derrick Rose")) {
			Random r = new Random();
			double low = 26.1;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Zach Lavine")) {
			Random r = new Random();
			double low = 34.5;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		
		if(player.equals("Anthony Davis")) {
			Random r = new Random();
			double low = 45.6;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Joel Embiid")) {
			Random r = new Random();
			double low = 44.4;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Nikola Jokic")) {
			Random r = new Random();
			double low = 37;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Paul George")) {
			Random r = new Random();
			double low = 43.3;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Damian Lillard")) {
			Random r = new Random();
			double low = 43.2;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Luka Doncic")) {
			Random r = new Random();
			double low = 51.3;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Kyrie Irving")) {
			Random r = new Random();
			double low = 41.1;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Jimmy Butler")) {
			Random r = new Random();
			double low = 36.8;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Karl Anthony Towns")) {
			Random r = new Random();
			double low = 44;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Russell Westbrook")) {
			Random r = new Random();
			double low = 37.3;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Trae Young")) {
			Random r = new Random();
			double low = 41.4;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Donovan Mitchell")) {
			Random r = new Random();
			double low = 36.4;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Devin Booker")) {
			Random r = new Random();
			double low = 36.6;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("D'Angelo Russell")) {
			Random r = new Random();
			double low = 37.2;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Andre Drummond")) {
			Random r = new Random();
			double low = 36.8;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Andrew Wiggins")) {
			Random r = new Random();
			double low = 34.6;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Kemba Walker")) {
			Random r = new Random();
			double low = 36.7;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Ben Simmons")) {
			Random r = new Random();
			double low = 31.2;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Pascal Siakam")) {
			Random r = new Random();
			double low = 40.2;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Kristaps Porzingis")) {
			Random r = new Random();
			double low = 29.3;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Brandon Ingram")) {
			Random r = new Random();
			double low = 39.1;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Lou Williams")) {
			Random r = new Random();
			double low = 32.4;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Kevin Durant")) {
			Random r = new Random();
			double low = 47.3;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Stephen Curry")) {
			Random r = new Random();
			double low = 46.8;
			this.power = r.nextInt(4) + low;	
			return;
		}
		
		if(player.equals("Klay Thompson")) {
			Random r = new Random();
			double low = 32.7;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Jayson Tatum")) {
			Random r = new Random();
			double low = 38.6;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Chris Paul")) {
			Random r = new Random();
			double low = 37.4;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("DeMar Derozen")) {
			Random r = new Random();
			double low = 33.4;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		
		if(player.equals("Jaylen Brown")) {
			Random r = new Random();
			double low = 29;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Bradley Beal")) {
			Random r = new Random();
			double low = 42.8;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Bam Adebayo")) {
			Random r = new Random();
			double low = 35.8;
			this.power = r.nextInt(3) + low;	
			return;
		}
		
		if(player.equals("Rudy Gobert")) {
			Random r = new Random();
			double low = 37.3;
			this.power = r.nextInt(2) + low;	
			return;
		}
		
		if(player.equals("Khris Middleton")) {
			Random r = new Random();
			double low = 32.6;
			this.power = r.nextInt(3) + low;	
			return;
		}
	}
	
	
	public double getPower() {
		return power;
	}
	
	public String getPlayer() {
		return player;
	}
	
}
	
