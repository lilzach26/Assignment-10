import javax.swing.*;

/**
ImageLabel class is used to expedite the process and simplify the code needed to create a label whose only purpose is to
present an image. 
*/

public class ImageLabel extends JLabel
{  
   //Define constructor
   
   /**
   Constructor takes in the string represenation of an image file name and creates a new JLabel of the image. 
   @param imageFileName the file name of the image that you would like to become the JLabel
   */
   public ImageLabel(String imageFileName)
   {
      super(new ImageIcon(imageFileName));
   }
}