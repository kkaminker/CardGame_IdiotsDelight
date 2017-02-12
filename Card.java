package gameDelight;

public class Card {
	
	private Rank rank;
	private Suit suit;
	private Color color;
	
	public Card(Rank rank, Suit suit, Color color){
		
		this.rank=rank;
		this.suit=suit;
		this.color=color;
		
	}
	
	public Rank getRank(){
		return this.rank;
	}
	
	public Suit getSuit(){
		return this.suit;
	}
	
	public Color getColor(){
		return this.color;
	}
	

	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("Card: "+this.color+" "+this.rank+" of "+this.suit);
		return sb.toString();
	}//toString
	
}//class
