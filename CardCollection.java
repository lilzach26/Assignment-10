import java.util.ArrayList;
import java.util.Random;

//Zach LaVigne
//12/4/13  110
//CardCollection class

/**
CardCollection is a super class for things that contain some amount of Card objects in flux (i.e., Deck and Hand). The 
class contains an array of cards stored in a field named deck and an int field storing the number of Card objects present
in the array. It does not have any specific methods, but has a constructor that creates an empty 52 card array for the 
deck field and initializes the int value to 52.
*/
public class CardCollection
{
   //Define fields
   private ArrayList<Card> deck; 
   private int size;
   public static final int STANDARD_SIZE = 52;
   
   //Define constructors
   
   /**
   Constructor initializes the deck field to an empty array of 52 Card objects and the size field to 52. 
   */
   public CardCollection()
   {
      this.deck = new ArrayList<Card>(STANDARD_SIZE);
      this.size = STANDARD_SIZE;
   }
   
   /**
   Alternate constructor initializes the deck field to an empty array of Cards of length given by the argument and sets
   the size field to size. 
   @param size Number of Cards that can fit in the deck field of the CardCollection. 
   */
   public CardCollection(int size) throws IndexOutOfBoundsException
   {
      if(size >= 1 && size <= STANDARD_SIZE)
      {
         this.deck = new ArrayList<Card>(STANDARD_SIZE);
         this.size = size;
      }
      else if(size > STANDARD_SIZE)
      {
         this.deck = new ArrayList<Card>(size);
         this.size = size;
      }
      else 
         throw new IndexOutOfBoundsException("Not an appropriate size for a collection of cards");
   }
   
   /**
   getSize method returns the integer value stored in the size field of the calling Deck object
   @returns integer stored in the size field of the calling Deck object
   */
   public int getSize()
   {
      return this.size;
   }
   
   /**
   getDeck method returns the Card ArrayList stored in the deck field for the calling Deck object
   @returns Card ArrayList stored in the deck field of the calling object
   */
   public ArrayList<Card> getDeck()
   {
      return this.deck;
   }
   
   /**
   setDeck method takes in an array of Cards and copies the elements one-to-one into the deck field of the calling Deck
   object. 
   @param cardArray the array of Cards to be copied into the deck field of the calling object
   @throws IndexOutOfBoundsException if the Card array trying to be copied is a different size than the deck field 
   */
   public void setDeck(ArrayList<Card> cardArray)
   {
      if(this.size == cardArray.size())
      {
         for(int i=0; i<this.size; i++)
         {
            Card newCard = cardArray.get(i);
            if(this.deck.size() < this.size)
               this.deck.add(i, newCard);
            else
               this.deck.set(i, newCard);
         }
      }
      else
         throw new IndexOutOfBoundsException("Array sizes do not match");
   }
   
   /**
   setSize method takes in an int and sets the size field to that new int
   @param newSize the updated number of cards in the deck field
   */
   public void setSize(int newSize)
   {
      this.size = newSize;
   }
   
   /**
   shuffle loops through the deck field and changes each card's place with another, randomly chosen card's place. The 
   final effect is that each card is located randomly throughout the array; there is no order to the array.
   */
   public void shuffle()
   {
      Random newIndexGenerator = new Random();
      for(int i = 0; i < this.size-1; i++)
      {  
         int exchangeIndex;
         do
         {
            exchangeIndex = newIndexGenerator.nextInt(this.size);
         }while(exchangeIndex == i);
         Card temp = deck.get(i);
         deck.set(i, deck.get(exchangeIndex));
         deck.set(exchangeIndex, temp);   
      }
   }
   
   /**
   add method adds an array of Card objects to the end of the ArrayList in the deck field of the calling object. The 
   array itself is not added, but the cards are added to respective cells in the ArrayList. 
   @param cardArray an Array of Card objects of any size that is to be added to the deck field
   */
   public void add(ArrayList<Card> cardArray)
   {
      for(int i=0; i<cardArray.size(); i++)
      {  
         this.deck.add(this.size, cardArray.get(i));
         size++;
      }
   }
   
   /**
   alternate add method adds a single Card object to the end of the ArrayList in the deck field of the calling object. 
   @param card A Card object to be added to the end of the calling object
   */
   public void add(Card card)
   {
      this.deck.add(this.size, card);
      size++;
   }
}