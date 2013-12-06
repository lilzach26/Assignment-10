import java.util.Random;
import java.util.ArrayList;
//Zach LaVigne
//12/4/13  110
//Deck class

/**
Deck class extends CardCollection but includes methods necessary only for decks of cards rather than hands, where hands
are temporary collections and decks are the source from which the game stems.
*/

public class Deck extends CardCollection
{  
   //Declare constants
   public static final int HALF_DECK = 26;
   
   //Define constructor methods
   
   /**
   Constructor takes in no arguments and creates a standard 52 card deck. The arrangement is such that the first element
   in the deck field corresponds to the ace of spades, the second to the king of spades, and so on down the ranks. This
   is then repeated for hearts, diamonds, and clubs respectively.
   */
   public Deck()
   {
      //Call the CardCollection constructor
      super();
      
      //Create an empty ArrayList of 52 cards to fill 
      ArrayList<Card> cardList = new ArrayList<Card>(52);
      int suit, rank, index = 0;
      
      //Loop through the suits 
      for(int i1 = 1; i1 <= 4; i1++)
      {
         suit = i1;
         
         //Loop through the ranks for each suit
         for(int i2 = 2; i2 <= 14; i2++)
         {
            rank = i2;
            
            //Create a card corresponding to the current rank and suit combination and add it to the ArrayList
            Card newCard = new Card(rank, suit);
            cardList.add(index, newCard);
            index++;
         }
      }
      //Set the deck field to the created ArrayList
      this.setDeck(cardList);   
   }
   
   /**
   Alternate constructor takes in an integer argument that details its size. Constructor calls the CardCollection 
   constructor characterized by an integer argument
   @param size the number of cards that the Deck object will hold
   */
   public Deck(int size) throws IndexOutOfBoundsException
   {
      super(size);
   }   
   
   //Define methods specific to a Deck object
   
   /**
   deal returns the Card object that is referenced by the 0 index in the deck array. The size field is decremented by 
   one and the 0 reference is set to null. 
   @return the Card object referenced by the first element in the deck field. 
   @throws DeckEmptyException e
   */
   public Card deal() throws DeckEmptyException
   {
      //Get the deck field to manipulate
      ArrayList<Card> deck = this.getDeck();
      
      if(deck != null) //If deck is not empty
      {
         //Remove the first card
         Card dealtCard = deck.remove(0);
         //Decrement the size
         int size = this.getSize();
         int newSize = --size;
         this.setSize(newSize);
         //Set the deck field to the one-card-smaller Card ArrayList
         this.setDeck(deck);
         //Return the dealt card
         return dealtCard;
      }
      else //If deck is empty, through an empty deck exception 
         throw new DeckEmptyException("You cannot deal from an empty deck");
   }
}
