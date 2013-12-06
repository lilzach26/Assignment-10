import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Zach LaVigne
//12/4/13  110
//War GUI

/**
WarGUI class extends JFrame and is called to create the frame with which the card game war can be played. 
*/

public class WarGUI extends JFrame
{
   //Declare fields
   private War game; //The guts of the game
   private JPanel battlefieldPanel, buttonPanel, headingPanel, titlePanel, gameStatusPanel; //Break up regions
   private JLabel title, gameStatus, playerOneDeck, playerTwoDeck, playerOneCard, playerTwoCard; //Declare all labels
   private JButton battleButton, warButton, takeoverButton, resetButton; //Declare all buttons
   
   //Define constructor
   
   /**
   Constructor initializes all fields and lays out the GUI frame
   */
   public WarGUI()
   {
      //Change layout
      setLayout(new BorderLayout());
      //Initialize game field
      game = new War();
      
      //Create the panels 
      battlefieldPanel = new JPanel(new BorderLayout()); //The area where battles are carried out
      buttonPanel = new JPanel(); //The area where buttons are layed out
      headingPanel = new JPanel(new BorderLayout()); //The area where title and gameStatus labels are layed out
      titlePanel = new JPanel(); //Area to hold the title (used to ensure title is centerd)
      gameStatusPanel = new JPanel(); //Area to hold the gameStatus (used to ensure the gameStatus is centered)
      
      //Create buttons and add action listeners
      battleButton = new JButton("Battle!");
      battleButton.addActionListener(new battleButtonListener());
      
      warButton = new JButton("War!");
      warButton.addActionListener(new warButtonListener());
      //Initially disable war button
      warButton.setEnabled(false);
      
      takeoverButton = new JButton("Takeover");
      takeoverButton.addActionListener(new takeoverButtonListener());
      //Initially disable takeoverButton
      takeoverButton.setEnabled(false);
      
      resetButton = new JButton("New Game");
      resetButton.addActionListener(new resetButtonListener());
      
      //Define the remaining fields
      title = new JLabel("WAR"); //Set title
      gameStatus = new JLabel("Waiting for a battle..."); //Set gameStatus
      playerOneDeck = new ImageLabel("back.jpg"); //Represent the decks as backs of cards
      playerTwoDeck = new ImageLabel("back.jpg");
      playerOneCard = new ImageLabel("grayrectangle.jpg"); //Outline the locations for each players card with gray rectangles
      playerTwoCard = new ImageLabel("grayrectangle.jpg");
      
      //Add buttons to their panel
      buttonPanel.add(battleButton);
      buttonPanel.add(warButton);
      buttonPanel.add(takeoverButton);
      buttonPanel.add(resetButton);
      
      //Add labels to the battlefield panel
      battlefieldPanel.add(playerOneCard, BorderLayout.WEST);
      battlefieldPanel.add(playerTwoCard, BorderLayout.EAST);
      
      //Add labels to the heading panel
      titlePanel.add(title); //Add title to titlePanel
      gameStatusPanel.add(gameStatus); //Add gameStatus to gameStatusPanel
      headingPanel.add(titlePanel, BorderLayout.NORTH);
      headingPanel.add(gameStatusPanel, BorderLayout.SOUTH);
      
      //Add panels to their respective place in the frame
      add(battlefieldPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      add(playerOneDeck, BorderLayout.WEST);
      add(playerTwoDeck, BorderLayout.EAST);
      add(headingPanel, BorderLayout.NORTH);
   }
   
   /**
   BattleButtonListener implements ActionListener and defines what is done when the battleButton is pushed
   */
   private class battleButtonListener implements ActionListener
   {
      /**
      actionPerformed defines what to do when the battleButton is pushed
      */
      public void actionPerformed(ActionEvent e)
      {
         try //Try to draw cards from each deck and determine if there is a winner
         {
            //Get cards for a battle
            game.draw();
            //Set images for playerOneCard and playerTwoCard to the cards drawn
            playerOneCard.setIcon(new ImageIcon(game.getPlayerOneCard() + ".jpg"));
            playerTwoCard.setIcon(new ImageIcon(game.getPlayerTwoCard() + ".jpg"));
    
            //battle to determine if there is a winner
            game.battle();

            if(game.getWinner() != 0) //If there is a winner, invite winner to take the cards
            {
               //Disable all buttons that should not be clicked
               battleButton.setEnabled(false);
               warButton.setEnabled(false);
               //Enable the takeoverButton
               takeoverButton.setEnabled(true);
               
               //Update gameStatus
               gameStatus.setText("Player " + game.getWinner() + " has won! Click 'takeover' to retrieve cards");
            }  
            else //If there is no winner, invite clicking of the war button
            {
               //Enable warButton and disable the battleButton
               warButton.setEnabled(true);
               battleButton.setEnabled(false);
               
               //Update gameStatus
               gameStatus.setText("We must have a war! Click 'war' to fight!");
            }
         }
         catch(DeckEmptyException exception) //If one of the decks is empty, inform user that there is a game
         {
            //Update gameStatus
            gameStatus.setText("Game is over! Player " + game.getWinner() + " has won!");
            
            //Set the loser's deck to a gray rectangle to indicate he has no cards left
            if(game.getWinner() == 1)
               playerTwoDeck.setIcon(new ImageIcon("grayrectangle.jpg"));
            else if(game.getWinner() == 2)
               playerOneDeck.setIcon(new ImageIcon("grayrectangle.jpg"));
            
            //Disable all buttons relating to a continuing game
            battleButton.setEnabled(false);
            warButton.setEnabled(false);
            takeoverButton.setEnabled(false);
         }
      }
   }
   
   /**
   warButtonListener implements ActionListener and defines what should be done when the warButton is clicked
   */
   private class warButtonListener implements ActionListener
   {
      /**
      actionPerformed defines what will be done when the warButton is clicked
      */
      public void actionPerformed(ActionEvent e)
      {
         try //Try to have a war
         {
            //Have a war and change the image for each player's card to the resulting cards
            game.war();
            playerOneCard.setIcon(new ImageIcon(game.getPlayerOneCard() + ".jpg"));
            playerTwoCard.setIcon(new ImageIcon(game.getPlayerTwoCard() + ".jpg"));
            
            //React
            if(game.getWinner() != War.NO_WINNER) //If there is a winner, invite him to collect his cards
            {
               //Disable warButton and enable takeoverButton
               warButton.setEnabled(false);
               takeoverButton.setEnabled(true);
               
               //Update gameStatus
               gameStatus.setText("Player " + game.getWinner() + " has won the war! Click 'takeover to retrieve cards");
            }
            else //Invite the player to continue his war
            {
               gameStatus.setText("We must continue war! Click 'war' to fight!");
            }
         }
         catch(DeckEmptyException exception) //If one of the decks is empty, inform the user that there is a winner
         {
            //Update gameStatus
            gameStatus.setText("Game is over! Player " + game.getWinner() + " has won!");
            
            //Set the loser's deck to a gray rectangle to indicate he has no cards left
            if(game.getWinner() == 1)
               playerTwoDeck.setIcon(new ImageIcon("grayrectangle.jpg"));
            else if(game.getWinner() == 2)
               playerOneDeck.setIcon(new ImageIcon("grayrectangle.jpg"));
            
            //Disable all buttons except reset button
            battleButton.setEnabled(false);
            warButton.setEnabled(false);
            takeoverButton.setEnabled(false);
         }
      }
   }
   
   /**
   takeoverButton implements ActionListener and defines what is done when the takeoverButton is pressed
   */
   private class takeoverButtonListener implements ActionListener
   {
      /**
      actionPerformed defines what is done when the takeoverButton is pressed
      */
      public void actionPerformed(ActionEvent e)
      {
         if(playerOneDeck == null || playerTwoDeck == null) //If any of the decks are empty, end the game
         {
            //Update gameStatus
            gameStatus.setText("Game is over! Player " + game.getWinner() + " has won!");
            
            //Set the loser's deck to a gray rectangle to indicate he has no cards left
            if(game.getWinner() == 1)
               playerTwoDeck.setIcon(new ImageIcon("grayrectangle.jpg"));
            else if(game.getWinner() == 2)
               playerOneDeck.setIcon(new ImageIcon("grayrectangle.jpg"));
            
            //Disable all buttons except reset button
            battleButton.setEnabled(false);
            warButton.setEnabled(false);
            takeoverButton.setEnabled(false);
         }
         else //If both decks still contain cards, add the winner's hand to the winner's deck
         {
            //Add winner hand to the winner's deck
            game.takeover(game.getWinner());
            
            //Remove all card images from the battlefieldPanel
            playerOneCard.setIcon(new ImageIcon("grayrectangle.jpg"));
            playerTwoCard.setIcon(new ImageIcon("grayrectangle.jpg"));
            
            //Enable battleButton and disable takeoverButton
            battleButton.setEnabled(true);
            takeoverButton.setEnabled(false);
            
            //Update gameStatus
            gameStatus.setText("Waiting for a battle...");
         }
      }
   }
   
   /**
   resetButtonListener implements ActionListener and defines what is done when the reset button is pressed
   */
   private class resetButtonListener implements ActionListener
   {
      /**
      actionPerformed defines what is done when the reset button is pressed
      */
      public void actionPerformed(ActionEvent e)
      {
         //Reset the war game code
         game.reset();
         
         //Update gameStatus
         gameStatus.setText("Waiting for a battle...");
         
         //Clear images of cards from the battlefieldPanel
         playerOneCard.setIcon(new ImageIcon("grayrectangle.jpg"));
         playerTwoCard.setIcon(new ImageIcon("grayrectangle.jpg"));
         //Set images of decks back to the backs of card
         playerOneDeck.setIcon(new ImageIcon("back.jpg"));
         playerTwoDeck.setIcon(new ImageIcon("back.jpg"));
         
         //Disable all buttons except the battleButton and the resetButton
         battleButton.setEnabled(true);
         warButton.setEnabled(false);
         takeoverButton.setEnabled(false);
      }
   }
}
