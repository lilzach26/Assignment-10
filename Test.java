import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test
{
   public static void main(String[] args)
   {
      War game = new War();
      game.draw();
         
         //Create image labels to represent each of the cards 
        JLabel playerOnePic = new ImageLabel(game.getPlayerOneCard() + ".jpg");
        JLabel playerTwoPic = new ImageLabel(game.getPlayerTwoCard() + ".jpg");
   }
}