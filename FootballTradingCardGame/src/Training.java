import java.util.Hashtable;
import java.util.Set;

public class Training {

	Drafting drafted;
	
	public Training(Drafting d) {
		drafted = d;
		
		Hashtable t = drafted.getDraft();
		
		TrainPlayers(t);
	}
	
	private void TrainPlayers(Hashtable drafted) {
		Set<String> keys = drafted.keySet();
        for(String key: keys){;
        }
	}
}
