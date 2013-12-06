//Zach LaVigne
//12/4/13  110
//DeckEmptyException will be thrown when a Deck object is asked to return a Card when the Deck object's deck field is
//an empty array. 

public class DeckEmptyException extends NullPointerException
{
   public DeckEmptyException(String s)
   {
      super(s);
   }
}