import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame
{
   private JLabel messageLabel;
   private JButton redButton;
   private JPanel panel;
   
   public Gui()
   {
      setTitle("Colors");
      messageLabel = new JLabel("Click the damn button");
      redButton = new JButton("red");
      redButton.addActionListener(new RedButtonListener());
      
      panel = new JPanel();
      panel.add(messageLabel);
      panel.add(redButton);
      
      add(panel);
      
      setVisible(true);
   }
   
   private class RedButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         panel.setBackground(Color.RED);
         messageLabel.setForeground(Color.BLUE);
      }
   }
   
   public static void main(String[] args)
   {
      new Gui();
   }
}
   
   