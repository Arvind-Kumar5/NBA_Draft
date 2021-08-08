import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Drafting {
	private Hashtable<String, Double> drafted ;
	private static int draftPick;
	private int playersDrafted;
	private DraftingDriver d;
	
	public Drafting() {
		drafted = new Hashtable<String, Double>(5);
		draftPick = 0;
		playersDrafted = 0;
		try {
			d = new DraftingDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showBoard() {
		Hashtable<String, Double> t = d.getDraftable();
		
		
		Set<String> keys = t.keySet();
        for(String key: keys){
            System.out.println(key);
        }
	}
	
	private boolean checkPlayerAvailability(String name) {
		boolean availability = false;
		
		if(d.getDraftable().containsKey(name) == true) {
			availability = true;
			return availability;
		}else {
			availability = false;
		}
		
		return availability;
	}
	
	public void draftPlayer(String name) throws IOException, IllegalArgumentException {
		
		NBAPlayers n = new NBAPlayers(name);
		
		if(n.getPlayer() == null) {
			throw new NullPointerException();
		}
		
		if(checkPlayerAvailability(name) == false) {
			throw new IllegalArgumentException();
		}
		
		drafted.put(n.getPlayer(), n.getPower());
		draftPick++;
		playersDrafted++;
		d.getDraftable().remove(n.getPlayer());
	}
	
	public void draftHighestAvailable(String teamName) {
		 
		 Object maxKey=null;
		 Double maxValue = Double.MIN_VALUE; 
		 for(Map.Entry<String,Double> entry : d.getDraftable().entrySet()) {
		      if(entry.getValue() > maxValue) {
		          maxValue = entry.getValue();
		          maxKey = entry.getKey();
		      }
		 }
		 
		 try {
			draftPlayer(maxKey.toString());
			System.out.println("Team " + teamName + " has drafted " + maxKey.toString() + " at " + draftPick);
			return;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Hashtable getDraft(){
		
		
		return drafted;
	}
	
	public int getDraftPick() {
		return draftPick;
	}
	
	public double totalPower() {
		double totalPower = 0;
		Set<String> keys = drafted.keySet();
        for(String key: keys){
            double power = drafted.get(key);
            totalPower = totalPower + power;
        } 
        
        return totalPower;
	}
}
