
public class Card {
	
	int rank, suite;
	
	public Card(){
		
		rank = 0;
		suite = 0;
	}
	
	public Card(int r, int s){
	
		rank = r;
		suite = s;
	}
	
	public String toString(){
		String[] r = {"blank", "blank", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
				"Jack", "Queen", "King", "Ace"};
		String[] s = {"Clubs", "Diamonds", "Hearts", "Spades"};
		
		//return r[this.rank] + " of " + s[this.suite]; 
		return r[this.rank] + s[this.suite]; 
		
	}
	public boolean isSameSuite(Card a){
		return this.suite == a.suite;
		
		
	}
}
