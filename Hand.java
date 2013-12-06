//Zach LaVigne
//12/4/13  110
//Hand class

/**
Hand class extends CardCollection but includes no new methods. The only differents is that it has a constructor that 
sets the deck field to have a 52 card capacity but sets its size equal to zero to represent the fact that the CardCollection
is initially empty. 
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