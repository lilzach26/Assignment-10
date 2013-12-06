import java.util.Random;
import java.util.ArrayList;
//Zach LaVigne
//12/4/13  110
//Deck class

/**
Deck class represents a deck of cards where a "deck" is simply considered a collection of cards. It has an array of Card 
objects and an integer representing the number of cards in the deck. It will have a default constructor that creates a 
standard 52 card deck. It will have methods that shuffle the deck and deal a card from the top (and decrement the number
of cards accordingly). It will have an overriding toString method that details the number of cards in the deck, and it 
will rely on the default equals method defined in the Object class.
*/

public class Deck extends CardCollection
{  
   //Declare constants
   public static final int HALF_DECK = 26;
   
   //Define constructor method
   
   /**
   Constructor takes in no arguments and creates a standard 52 card deck. The arrangement is such that the first element
   in the deck field corresponds to the ace of spades, the second to the king of spades, and so on down the ranks. This
   is then repeated for hearts, diamonds, and clubs respectively.
   */
   public Deck()
   {
      super();
      ArrayList<Card> cardList = new ArrayList<Card>(52);
      int suit, rank, index = 0;
      for(int i1 = 1; i1 <= 4; i1++)
      {
         suit = i1;
         for(int i2 = 2; i2 <= 14; i2++)
         {
            rank = i2;
            Card newCard = new Card(rank, suit);
            cardList.add(index, newCard);
            index++;
         }
      }
      this.setDeck(cardList);   
   }
   
   /**
   Alternate constructor takes in an integer argument that details its size. The deck field is then an empty array of size
   corresponding to the argument, and the size field is set to that value. 
   @param size the number of cards that the Deck object will hold
   @throws IndexOutOfBoundsException if a number less than one is passed as an argument
   */
   public Deck(int size) throws IndexOutOfBoundsException
   {
      super(size);
   }
   
   //Define accessor methods
   
   //Define setter methods
   
   
   
   //Define other methods
   
   /**
   deal returns the Card object that is referenced by the 0 index in the deck array. The size field is decremented by 
   one and the deck field "moves forward" so that the Card referenced by the 1 index will now be referenced by the 0 
   index, and that referenced by the 2 index will now be referenced by the 1 index etc.
   @return the Card object referenced by the first element in the deck field. 
   @throws DeckEmptyException e
   */
   public Card deal() throws DeckEmptyException
   {
      ArrayList<Card> deck = this.getDeck();
      if(deck != null)
      {
         Card dealtCard = deck.remove(0);
         int size = this.getSize();
         int newSize = --size;
         this.setSize(newSize);
         this.setDeck(deck);
         return dealtCard;
      }
      else
         throw new DeckEmptyException("You cannot deal from an empty deck");
   }
   
   
   //Define toString method
   
   /**
   toString represents a Deck as a string that reveals how many card objects are stored in the deck field of the Deck
   @return a String describing the Deck object. It represents how many card objects are stored in the deck field
   @throws DeckEmptyException e
   */
   public String toString() throws DeckEmptyException
   {
      String deckAsString;
      int size = this.getSize();
      if(size != 0)
      {
         deckAsString = size + " card deck";
         return deckAsString;
      }
      else
         throw new DeckEmptyException("Attempt to reference an empty deck");
   }
}
