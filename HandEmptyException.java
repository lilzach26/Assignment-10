//Zach LaVigne
//12/4/13  110
//HandEmptyException class

/**
HandEmptyException extends NullPointerException and is thrown when a method attempts to inappropriately use an ArrayList 
of Card objects that does not have a size.
*/
public class HandEmptyException extends NullPointerException
{
   public HandEmptyException(String s)
   {
      super(s);
   }
}