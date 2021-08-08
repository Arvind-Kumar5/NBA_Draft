import java.util.ArrayList;

public class Teams {
	Drafting d;
	ArrayList<Drafting> drafted;
	
	public Teams(Drafting d) {
		this.d = d;
		drafted = new ArrayList<Drafting>();
	}
	
	public String showDraft() {
		String temp = "";
		
		for(int i = 0; i < drafted.size(); i++) {
			temp = drafted.get(i).toString();
		}
		
		return temp;
	}
}
