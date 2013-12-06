//Zach LaVigne
//12/4/13  110
//DeckTooSmallException is thrown if a method requires a deck whose size is larger than that of the calling deck object

public class DeckTooSmallException extends IndexOutOfBoundsException
{
   public DeckTooSmallException(String s)
   {
      super(s);
   }
}