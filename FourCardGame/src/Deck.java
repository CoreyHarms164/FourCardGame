import java.util.Random;
public class Deck{
	
	Card[] deck = new Card[52];
	Card[] discard = new Card[52];
	int nextCard;
	
	public Deck(){
		nextCard = 0;
		int cardCount = 0;
		for(int i = 0; i <= 3; i ++){
			for(int k = 1; k <= 13; k++){
				deck[cardCount] = new Card(k, i);
				cardCount ++;
			}
		}
	}
	
	
	public void printDeck(){
		for(int i = 0; i <= 51; i ++){
			System.out.println(deck[i]);
		}
	}
	
	public void printDiscard(){
		int count = 0;
		while(discard[count] != null){
			System.out.println(discard[count]);
			count ++;
		}
	}
	
	public void shuffle(){
		nextCard = 0;
		
		Random r = new Random();
		int index = r.nextInt(52);
		Card[] shuffle = new Card[52];
		for(int i = 0; i <= 51; i++){
			while(shuffle[index] != null)
				index = r.nextInt(52);
			
			shuffle[index] = deck[i];
			
		}
		
		deck = shuffle;
		
	}
	
	
	public Card dealTopCard(){
		Card c = deck[nextCard];
		nextCard ++;
		return c;
	}
	
	public boolean isEmpty(){
		return nextCard == 52;
	}

}
