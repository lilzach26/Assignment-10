//Zach LaVigne
//12/4/13  110
//Card class

/**
Card class will represent a card in a standard deck. It will have a suit and a rank as its fields. It will implement
the comparable interface and will have an overriding toString method. It will have only one constructor
that takes in an integer for the rank and an integer for the suit. Suits and face-rank integers will be 
represented by static constants defined in the card class.
*/

public class Card implements Comparable
{
   //Declare fields
   private int suit;
   private int rank; 
   
   //Define constants for face cards
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
   public static final int ACE = 14;
   
   //Define constants for suit names
   public static final int CLUBS = 1;
   public static final int DIAMONDS = 2;
   public static final int HEARTS = 3;
   public static final int SPADES = 4;
   
   //Define constructors
   
   /**
   Constructor takes in two integers and creates a card whose rank corresponds to the first integer and whose suit
   corresponds to the second
   @param rank the integer corresponding to the rank of the Card object getting created (2-14)
   @param suit the integer representing the suit of the Card object being created (1-4)
   */
   public Card(int rank, int suit)
   {
      this.rank = rank;
      this.suit = suit;
   }
   
   //Define toString method
   
   /**
   toString represents a Card object as a String composed of its rank and its suit
   @return a String that gives a formatted representation of the Card object by detailing its rank and 
           suit. 
   */
   public String toString()
   {
      //Declare local variables
      String rankString, suitString, cardAsString;
      
      //If the rank is not a face card, represent it with the integer stored in the rank field of the calling Card
      if(this.rank < 11 && this.rank > 1)
      {
         Integer rankInt = new Integer(this.rank);
         rankString = rankInt.toString();
      }
      //else represent the rank with the name of the face card
      else
      {
         switch(this.rank)
         {
            case 11:
               rankString = "jack";
               break;
            case 12:
               rankString = "queen";
               break;
            case 13:
               rankString = "king";
               break;
            case 14: 
               rankString = "ace";
               break;
            default:
               rankString = "0";
               break;
         }
      }
      
      //Represent the suit by its first letter
      switch(this.suit)
      {
         case 1:
            suitString = "c";
            break;
         case 2:
            suitString = "d";
            break;
         case 3:
            suitString = "h";
            break;
         case 4:
            suitString = "s";
            break;
         default: 
            suitString = "";
            break;
      }
      
      //Create a string combined of the rank and the first letter of the suit of the calling card.
      cardAsString = rankString + suitString;
      return cardAsString; 
   }
   
   //Define implement Comparable
   
   /**
   compareTo method takes in another card object and determines if the calling object's rank is greater than, less than,
   or equal to the inputted object's rank
   @param cardToCheck the Card object that the calling object is to be compared to 
   @return an integer corresonding to the difference between the rank of the calling object and that of the 
           cardToCheck object. Negative if the calling object is "smaller" than the cardToCheck and positive if greater. 
           Zero if the rank is the same.
   */
   public int compareTo(Object cardToCheck)
   {
      //Declare the relation variable, which will hold the difference in the ranks of the two cards.
      int relation;
      
      //Cast the passed in Card object back to a Card
      Card toCheckAsCard = (Card)cardToCheck;
      
      if(this.rank < toCheckAsCard.rank) 
         relation = this.rank - toCheckAsCard.rank;
      else if(this.rank > toCheckAsCard.rank)
         relation = this.rank - toCheckAsCard.rank;
      else 
         relation = 0;
      
      return relation;
   }
} 