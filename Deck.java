package gameDelight;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card>cardDeck;
	
	public Deck(){
		
		cardDeck=new ArrayList(52);
		/*looping through "cardDeck" a size 52 AL
		for each card number(rank) need one of each suit with appropriate color
		so instantiate 4 new cards for each number and add to the arrayList
		the loop goes from 0-12 because that is the amount of different ranks
		to ensure the appropriate rank for each added card, make use of the ordinality of enums
		adding the rank that corresponds to the number card we are up to as determined by the variable index*/
		for(int index=0;index<=12;index++){
				
				cardDeck.add(new Card(Rank.values()[index],Suit.HEARTS,Color.RED));
				cardDeck.add(new Card(Rank.values()[index],Suit.DIAMONDS,Color.RED));
				cardDeck.add(new Card(Rank.values()[index],Suit.SPADES,Color.BLACK));
				cardDeck.add(new Card(Rank.values()[index],Suit.CLUBS,Color.BLACK));	
			
		}//for
	}//constructor
	
	
	public void shuffle(){
		for(int index=0;index<cardDeck.size()-1;index++){
			//generate random number between 0 and 52
			Random rand=new Random(/*System.currentTimeMillis()*/);//Prof. P wanted me to do the System.etc but when I do
			//then the first three cards dealt all have the same rank!
			int num=rand.nextInt(52);
			//make a temp variable to hold the card at the current position (index)
			Card temp=cardDeck.get(index);
			//change the card at current position to card at position of rand. generated number
			cardDeck.set(index,cardDeck.get(num));
			//set card at position of rand gen number to the card in temp
			cardDeck.set(num, temp);
			
			
			
		}//for
		
	}//shuffle
	
/*Professor's comment: ??: since an ArrayList can access each position directly it is more 
 * efficient to remove data from a certain position than to have the ArrayList search for the value
 */
	public Card deal(){
		
		Card temp=cardDeck.get(cardDeck.size()-1);//the top card storing in variable
		cardDeck.remove(temp);//so can remove it--pop()
		return temp;//and still have it to return--peek()
		
	

	}//deal
	
	public boolean isEmpty(){
		return cardDeck.isEmpty();
	}
	
	
}//deck
