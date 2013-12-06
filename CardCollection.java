import java.util.ArrayList;
import java.util.Random;

//Zach LaVigne
//12/4/13  110
//CardCollection class

/**
CardCollection is a super class for things that contain some amount of Card objects in flux (i.e., Deck and Hand). The 
class contains an ArrayList of cards stored in a field named deck and an int field (size) sstoring the number of Card objects present
in the array. The class has two possible constructors and a series of methods appropriate for collections of cards.
*/
public class CardCollection
{
   //Define fields
   private ArrayList<Card> deck; 
   private int size;
   
   //Declare constants
   public static final int STANDARD_SIZE = 52;
   
   //Define constructors
   
   /**
   Default constructor initializes the deck field to an empty array of 52 Card objects and the size field to 52. 
   */
   public CardCollection()
   {
      this.deck = new ArrayList<Card>(STANDARD_SIZE);
      this.size = STANDARD_SIZE;
   }
   
   /**
   Alternate constructor initializes the deck field to an empty array of Cards of length equal to 52
   and sets the size field to the size argument. 
   @param size number of Cards that will be added to the deck field of the CardCollection object. 
   */
   public CardCollection(int size)
   {
      this.deck = new ArrayList<Card>(STANDARD_SIZE);
      this.size = size;
   }
   
   //Define getter and setter methods
   
   /**
   getSize method returns the integer value stored in the size field of the calling Deck object
   @return integer stored in the size field of the calling Deck object
   */
   public int getSize()
   {
      return this.size;
   }
   
   /**
   getDeck method returns the Card ArrayList stored in the deck field for the calling Deck object
   @return Card ArrayList stored in the deck field of the calling object
   */
   public ArrayList<Card> getDeck()
   {
      return this.deck;
   }
   
   /**
   setDeck method takes in an array of Cards and copies the elements one-to-one into the deck field of the calling
   object. 
   @param cardArray the array of Cards to be copied into the deck field of the calling object.
   */
   public void setDeck(ArrayList<Card> cardArray)
   {
      //Fill the deck field of the CardCollection object by copying Cards from the card array 
      for(int i=0; i<this.size; i++)
      {
         Card newCard = cardArray.get(i);
         
         //If the deck has not been filled before add the new card, or else replace the current card there
         if(this.deck.size() < this.size) 
            this.deck.add(i, newCard);
         else
            this.deck.set(i, newCard);
      }
   }
   
   /**
   setSize method takes in an int and sets the size field to that new int
   @param newSize the updated number of cards in the deck field
   */
   public void setSize(int newSize)
   {
      this.size = newSize;
   }
   
   //Define other methods
   
   /**
   shuffle loops through the deck field and changes each card's place with another, randomly chosen card's place. The 
   final effect is that each card is located randomly throughout the array.
   */
   public void shuffle()
   {
      //Create a new random number generator for determining a card's new location
      Random newIndexGenerator = new Random();
      
      //Loop through the deck field ArrayList and change every card's place randomly.
      for(int i = 0; i < this.size-1; i++)
      {  
         //Randomly generate a new index
         int exchangeIndex;
         do
         {
            exchangeIndex = newIndexGenerator.nextInt(this.size);
         }while(exchangeIndex == i); //Ensure a card cannot keep its same location
         
         //Switch the card with the card at the random index
         Card temp = deck.get(i);
         deck.set(i, deck.get(exchangeIndex));
         deck.set(exchangeIndex, temp);   
      }
   }
   
   /**
   add method adds an ArrayList of Card objects to the end of the ArrayList in the deck field of the calling object. The 
   ArrayList itself is not added, but the cards are added to respective cells in the deck field. 
   @param cardArray an Array of Card objects of any size that is to be added to the deck field
   */
   public void add(ArrayList<Card> cardArray)
   {
      for(int i=0; i<cardArray.size(); i++)
      {  
         this.deck.add(this.size, cardArray.get(i));
         this.size++;
      }
   }
   
   /**
   alternate add method adds a single Card object to the end of the ArrayList in the deck field of the calling object. 
   @param card A Card object to be added to the end of the calling object
   */
   public void add(Card card)
   {
      this.deck.add(this.size, card);
      this.size++;
   }
}