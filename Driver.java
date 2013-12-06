import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Zach LaVigne
//12/4/13  110
//Driver class

public class Driver
{
   public static void main(String[] args)
   {
      JFrame frame = new WarGUI();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
      System.out.println("something");
   }
}