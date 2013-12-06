import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Zach LaVigne
//12/4/13  110
//Driver for the WarGUI class

public class Driver
{
   public static void main(String[] args)
   {
      //Create a new WarGUI to be the UI frame
      JFrame frame = new WarGUI();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set default close operation
      frame.pack(); //Pack the window
      frame.setVisible(true); //Make the window visible
   }
}