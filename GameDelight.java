package gameDelight;

import java.util.Stack;
import java.util.Scanner;

public class GameDelight {

	private Deck theDeck;
	private Stack<Card>[] stacks;
	private int cardsRemaining;

	public static void main(String[] args) {

		GameDelight game = new GameDelight();
		Scanner input = new Scanner(System.in);

		while (!game.gameWon()) {
			
			game.display();// display game's status

			// directions
			System.out.println("1. If there are two cards with the same rank showing, discard both.");
			System.out.println("2. If there are two cards of the same suit showing, discard the one with the lower rank. ");
			System.out.println("3. Deal 4 new cards, one on top of each stack.");

			// take the user's choice
			int choice = input.nextInt();
			while(choice<1 || choice>3){
				System.out.println("1. If there are two cards with the same rank showing, discard both.");
				System.out.println("2. If there are two cards of the same suit showing, discard the one with the lower rank. ");
				System.out.println("3. Deal 4 new cards, one on top of each stack.");
				choice=input.nextInt();
			}

			switch (choice) {
			case 1:
				System.out.println("Please indicate the numbers of the stacks from which to discard.(Separated by a space or on two lines.");
				int stack1 = input.nextInt();
				int stack2 = input.nextInt();
				while(stack1>4 || stack1<1 || stack2>4 ||stack2<1){
					System.out.println("Please indicate the numbers of the stacks from which to discard.");
					stack1 = input.nextInt();
					stack2 = input.nextInt();
				}
				game.discard2(stack1, stack2);
				break;
			case 2:
				System.out.println("Please indicate the number of the stack with lower rank.");
				int lowRankStack = input.nextInt();
				while(lowRankStack<1 || lowRankStack>4){
					System.out.println("Please indicate the number of the stack with lower rank.");
					lowRankStack = input.nextInt();
				}
				game.discard1(lowRankStack);
				break;
			case 3:
				// need to deal new cards
				boolean dealt = game.deal();
				// if can't deal any more cards, the user lost and exit game
				if (!dealt) {
					System.out.println("There are no more cards to be dealt. Goodbye!");
					System.exit(0);
				}
				break;

			}

		} // while
			// if the game has been won
		System.out.println("Great job! You won!");

	}// main

	public GameDelight() {

		theDeck = new Deck();
		theDeck.shuffle();
		cardsRemaining = 52;

		stacks = (Stack<Card>[]) new Stack[4];
		// the array stacks will have each element be a stack

		for (int index = 0; index < stacks.length; index++) {
			
			stacks[index] = new Stack();// each element of array will now be referencing a stack

			stacks[index].push(theDeck.deal()); // and adding a card to each stack

		}
	}// constructor

	public void display() {

		// loop through array
		for (int index = 0; index < stacks.length; index++) {
			// if the stack is not empty can display
			if (!stacks[index].isEmpty()) {
				System.out.println("Stack " + (index + 1) + ": " + stacks[index].peek());
			} 
			else
				System.out.println("Stack " + (index + 1) + ": -");
		}
		System.out.println("Cards Remaining: " + cardsRemaining);
	}// display()

	public void discard2(int stack1, int stack2) {
		// computer: stacks 0-3 human using numbers 1-4 so need to decrement
		stack1 -= 1;
		stack2 -= 1;
		// if ranks match then pop both cards
		// first check neither is empty to prevent emptyStackException()
		if (!stacks[stack1].isEmpty() && !stacks[stack2].isEmpty()) {
			if (stacks[stack1].peek().getRank().equals(stacks[stack2].peek().getRank())) {

				stacks[stack1].pop();
				stacks[stack2].pop();
				cardsRemaining -= 2;// and decrement cards remaining because just popped 2
			}
		}
		// and if he was trying to cheat/made a mistake his request is ignored

	}// discard2

	/*Professor's comments: once you pop the card, return from the method, 
	 * no need to continue looping any longer , mission accomplished*/
	public void discard1(int stack1) {
		// again, need to decrement stack because the user is using 1-4 and computer 0-3
		stack1 -= 1;
		boolean flag = false;

		for (int index = 0; index < stacks.length && flag == false; index++) {
			// find a stack with top card of equal suit
			// check that neither stack is empty
			if (!stacks[index].empty() && !stacks[stack1].empty()) {
				// check not comparing to itself!
				if (index != stack1 && stacks[stack1].peek().getSuit().equals(stacks[index].peek().getSuit())) {
					flag = true;// yes, there are two stacks of matching suit
					// if indicated one lower rank or equal rank, pop it
					if (stacks[stack1].peek().getRank().getNum()<=stacks[index].peek().getRank().getNum()) {
						stacks[stack1].pop();
						cardsRemaining--;
						return;
					} // inner if
					else // if higher rank, pop the other
					{
						stacks[index].pop();
						cardsRemaining--;
						return;
					}

				} // if
			} // if !empty()
		} // for

	}// discard1

	public boolean gameWon() {
		// check to make sure each stack is empty and that there are no more
		// cards to be dealt
		if (cardsRemaining==0) {
			return true;
		} else
			return false;
	}

	public boolean deal() {
		boolean flag = true;
		// go through the array, and as long as there are more cards to deal,
		// push one to each stack
		// if there are no more cards to deal(and only if not one was dealt),
		// then this method will return false
		for (int index = 0; index < stacks.length && flag == true; index++) {
			if (!theDeck.isEmpty()) {
				stacks[index].push(theDeck.deal());
			} else
				flag = false;
		} // for
		return flag;
	}// deal4new

}// class
