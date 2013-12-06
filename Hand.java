//Zach LaVigne
//12/4/13  110
//Hand class

/**
Hand class extends Deck class and differs in that its constructor creates an EMPTY array of 52 cards rather than creating
a functional Deck. This provides a space for Card objects to be added and removed as a game progresses. It is rather like
an airlock for Card objects
*/

public class Hand extends CardCollection
{
   //No new fields; define constructor
   public Hand()
   {
      super(CardCollection.STANDARD_SIZE);
      this.setSize(0);
   }
}