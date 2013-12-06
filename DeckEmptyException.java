//Zach LaVigne
//12/4/13  110
//DeckEmptyException will be thrown when a Deck object is asked to return a Card when the Deck object's deck field is
//an empty array. 

/**
DeckEmptyException extends NullPointerException and is thrown when one player's deck is empty
*/
public class DeckEmptyException extends NullPointerException
{
   //Define constructor
   
   /**
   Constructor simply calls the super.
   */
   public DeckEmptyException(String s)
   {
      super(s);
   }
}