//Zach LaVigne
//12/4/13  110
//NoWinnerException class

/**
NoWinnerException extends NullPointerException and is used when a battle would return a null Card array. In this case, 
the Cards must be equal in ranks
*/
public class NoWinnerException extends NullPointerException
{
   public NoWinnerException(String s)
   {
      super(s);
   }
}