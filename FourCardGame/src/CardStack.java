
public class CardStack {
	
	Card[] cards = new Card[53];
	int index;
	
	public CardStack(){
		index = 0;
		
	}
	
	public void insert(Card c){
		index ++;
		cards[index] = c;
		
	}
	
	public Card remove(){
		Card hold = cards[index];
		index--;
		return hold;
	}
	public int getIndex(){
		return index;
	}
	
	public Card showTopCard(){
		return cards[index];
	}
	
	public int getLength(){
		return cards.length;
	}

	public boolean isEmpty(){
		return index == 0;
	}
	public void clear(){
		for(int i = 0; i <= 52; i ++){
			cards[i] = null;
		}
		index = 0;
	}
	public boolean isAce(){
		if(index == 0){
			return false;
	}
		else{
		String s = this.showTopCard().toString();
		return s.contains("Ace");
		}
	}
}
