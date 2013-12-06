import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Zach LaVigne
//12/4/13  110
//War GUI

public class WarGUI extends JFrame
{
   //Declare fields
   private War game;
   private JPanel battlefieldPanel, buttonPanel, headingPanel, labelPanel;
   private JLabel title, gameStatus, playerOneDeck, playerTwoDeck, playerOneCard, playerTwoCard;
   private JButton battleButton, warButton, takeoverButton, resetButton;
   
   //Define constructor
   public WarGUI()
   {
      setLayout(new BorderLayout());
      game = new War();
      
      //Create the battlefieldPanel and buttonPanel
      battlefieldPanel = new JPanel(new BorderLayout());
      buttonPanel = new JPanel();
      headingPanel = new JPanel(new BorderLayout());
      labelPanel = new JPanel(new BorderLayout());
      
      //Create buttons and add action listeners
      battleButton = new JButton("Battle!");
      battleButton.addActionListener(new battleButtonListener());
      
      warButton = new JButton("War!");
      warButton.addActionListener(new warButtonListener());
      warButton.setEnabled(false);
      
      takeoverButton = new JButton("Takeover");
      takeoverButton.addActionListener(new takeoverButtonListener());
      takeoverButton.setEnabled(false);
      
      resetButton = new JButton("New Game");
      resetButton.addActionListener(new resetButtonListener());
      
      //Define the remaining fields
      title = new JLabel("WAR");
      gameStatus = new JLabel("Waiting for a battle...");
      playerOneDeck = new ImageLabel("back.jpg");
      playerTwoDeck = new ImageLabel("back.jpg");
      playerOneCard = new ImageLabel("grayrectangle.jpg");
      playerTwoCard = new ImageLabel("grayrectangle.jpg");
      
      //Add buttons to their respective panel
      buttonPanel.add(battleButton);
      buttonPanel.add(warButton);
      buttonPanel.add(takeoverButton);
      buttonPanel.add(resetButton);
      
      //Add labels to the battlefield panel
      battlefieldPanel.add(playerOneCard, BorderLayout.WEST);
      battlefieldPanel.add(playerTwoCard, BorderLayout.EAST);
      
      //Add labels to the heading panel
      labelPanel.add(title, BorderLayout.NORTH);
      labelPanel.add(gameStatus, BorderLayout.SOUTH);
      headingPanel.add(labelPanel, BorderLayout.CENTER);
      
      //Add everything to its respective place in the frame
      add(battlefieldPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      add(playerOneDeck, BorderLayout.WEST);
      add(playerTwoDeck, BorderLayout.EAST);
      add(headingPanel, BorderLayout.NORTH);
   }
   
   private class battleButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            //Get cards for a battle
            game.draw();
            //Set images for playerOneCard and playerTwoCard to the cards drawn
            playerOneCard.setIcon(new ImageIcon(game.getPlayerOneCard() + ".jpg"));
            playerTwoCard.setIcon(new ImageIcon(game.getPlayerTwoCard() + ".jpg"));
    
            //battle to determine if there is a winner
            game.battle();
            //update the status label 
            if(game.getWinner() != 0) //If there is a winner, invite winner to take the cards
            {
               battleButton.setEnabled(false);
               warButton.setEnabled(false);
               takeoverButton.setEnabled(true);
               gameStatus.setText("Player " + game.getWinner() + " has won! Click 'takeover' to retrieve cards");
            }  
            else //If there is no winner, invite clicking of the war button
            {
               warButton.setEnabled(true);
               gameStatus.setText("We must have a war! Click 'war' to fight!");
            }
         }
         catch(DeckEmptyException exception) //If one of the decks is empty, inform user that there is a winner
         {
            gameStatus.setText("Game is over! Player " + game.getWinner() + " has won!");
            battleButton.setEnabled(false);
            warButton.setEnabled(false);
            takeoverButton.setEnabled(false);
         }
      }
   }
   
   private class warButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //call the war method 
         try
         {
            battleButton.setEnabled(false);
            warButton.setEnabled(false);
            game.war();
            playerOneCard.setIcon(new ImageIcon(game.getPlayerOneCard() + ".jpg"));
            playerTwoCard.setIcon(new ImageIcon(game.getPlayerTwoCard() + ".jpg"));
            takeoverButton.setEnabled(true);
            gameStatus.setText("Player " + game.getWinner() + " has won the war! Click 'takeover to retrieve cards");
         }
         catch(DeckEmptyException exception) //If one of the decks is empty, inform the user that there is a winner
         {
            gameStatus.setText("Game is over! Player " + game.getWinner() + " has won!");
            battleButton.setEnabled(false);
            warButton.setEnabled(false);
            takeoverButton.setEnabled(false);
         }
      }
   }
   
   private class takeoverButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         game.takeover(game.getWinner());
         playerOneCard.setIcon(new ImageIcon("grayrectangle.jpg"));
         playerTwoCard.setIcon(new ImageIcon("grayrectangle.jpg"));
         battleButton.setEnabled(true);
         takeoverButton.setEnabled(false);
         gameStatus.setText("Waiting for a battle...");
      }
   }
   
   private class resetButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         game.reset();
         gameStatus.setText("Waiting for a battle...");
         playerOneCard.setIcon(new ImageIcon("grayrectangle.jpg"));
         playerTwoCard.setIcon(new ImageIcon("grayrectangle.jpg"));
         playerOneDeck.setIcon(new ImageIcon("back.jpg"));
         playerTwoDeck.setIcon(new ImageIcon("back.jpg"));
         battleButton.setEnabled(true);
         warButton.setEnabled(true);
         takeoverButton.setEnabled(true);
      }
   }
}
