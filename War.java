import java.util.ArrayList;

//Zach LaVigne
//12/4/13  110
//War class

/**
War class contains fields and methods that can be used to play the card game war in which players split a deck of 52 cards
and attempt to win all of the cards back to their own respective piles. 
*/

public class War 
{
   //Declare fields
   private Deck playerOneDeck, playerTwoDeck; //Create two deck objects for the two players
   private Card playerOneCard, playerTwoCard; //Create two Card objects that will store the players' cards during battles
   private Hand winnerHand; //Create a Hand that will hold all of the cards that the winner will get to add to his deck
   private int winner, gameWinner; //Reference an integer that will store the number of the winning player
   
   //Define constants
   public static final int PLAYER_ONE = 1;
   public static final int PLAYER_TWO = 2;
   public static final int NO_WINNER = 0;
   
   //Define constructor
   
   /**
   Constructor splits a deck of 52 cards into two disjoint decks of 26 cards and sets the Card objects
   representing each player's "move" to null.
   */
   public War()
   {
      //Create a deck to split and shuffle it
      Deck mainDeck = new Deck();
      mainDeck.shuffle();
      
      //Split the shuffled deck and set the playerOneDeck and playerTwoDeck fields to the split decks
      playerOneDeck = split(mainDeck);
      playerTwoDeck = mainDeck;
      
      //initialize the remaining fields
      gameWinner = NO_WINNER;
      winner = NO_WINNER;
   }
   
   //Define game methods
   
   /**
   draw method simulates  part of a battle. The top card is dealt from each player's deck face-up and a reference to the 
   cards are stored in the winnerHand to be collected by the eventual winner
   @throws DeckEmptyException if one or both players are trying to use an empty deck. 
   */ 
   public void draw() throws DeckEmptyException
   {
      try //If no deck is empty
      {
         //Set playerOneCard to the card dealt from the top of his deck
         playerOneCard = playerOneDeck.deal();
         //Set playerTwoCard to the card dealt from the top of his deck
         playerTwoCard = playerTwoDeck.deal();
         
         //Add references to the two cards in play to the winnerHand field
         winnerHand = new Hand();
         winnerHand.add(playerOneCard);
         winnerHand.add(playerTwoCard);
      }
      catch(DeckEmptyException e)
      {
         throw new DeckEmptyException("One of the decks is empty");
      }
   }
   
   
   
   /**
   battle method to determine the relation between the two cards in play and sets the winner field to the player with the 
   higher-ranking card. If the cards are the same rank, then there is no winner.
   */
   public void battle()
   {
      if(playerOneCard.compareTo(playerTwoCard) < 0) 
         winner = PLAYER_TWO;
      else if(playerOneCard.compareTo(playerTwoCard) > 0)
         winner = PLAYER_ONE;
      else
         winner = NO_WINNER;
   }
   
   /**
   takeover method is called to add whatever the winning hand is to the winner's deck after shuffling the hand
   @param winner the integer representing the player who won the hand 
   */
   public void takeover(int winner)
   {
      //Shuffle the winnerHand
      winnerHand.shuffle();
      
      //Add winnerHand to the winner's deck
      if(winner == 1)
         playerOneDeck.add(winnerHand.getDeck());
      else
         playerTwoDeck.add(winnerHand.getDeck());
      
      //Re-initialize the winnerHand to a null object 
      winnerHand = null;
   }
  
   /**
   war method simulates the process that occurs when a battle produces two equally-ranked cards. The top card from each 
   deck is dealt face-down, and the next one is dealt face up (both are added as references to the winner hand).
   @throws DeckEmptyException if one or more of the players is trying to use an empty deck
   */
   public void war() throws DeckEmptyException
   {
      if(playerOneDeck == null || playerTwoDeck == null) //If one of the decks is empty, throw an exception
         throw new DeckEmptyException("One of the decks is empty");
      else
      {
         //Take the two top cards from each deck as "face-down" (they go right to the winnerHand)
         winnerHand.add(playerOneDeck.deal());
         winnerHand.add(playerTwoDeck.deal());
         
         //Draw two new cards, play them "face-up" and call the battle method to determine if there is a winner
         draw();
         battle();
      }
   }
   
   /**
   reset sets all fields to null and calls the constructor 
   */
   public void reset()
   {
      playerOneDeck = null;
      playerTwoDeck = null;
      playerOneCard = null;
      playerTwoCard = null;
      winnerHand = null;
      new War();
   }
   
   //Define getter methods
   
   /** 
   getPlayerOneCard returns the Card stored in the playerOneCard field.
   @return the Card object stored in the playerOneCard field
   */
   public Card getPlayerOneCard()
   {
      return this.playerOneCard;
   }
   
   /**
   getPlayerTwoCard returns the Card stored in the playerTwoCard field.
   @return the Card object stored in the playerTwoCard field
   */
   public Card getPlayerTwoCard()
   {
      return this.playerTwoCard;
   }
   
   /**
   getWinner returns the winner of a battle. 
   @return the int stored in the winner field
   */
   public int getWinner()
   {
      return this.winner;
   }
   
   /**
   getWinnerHand returns the Hand stored in the winnerHand field
   @return the winning hand 
   */
   public Hand getWinnerHand()
   {
      return this.winnerHand;
   }
   
   /**
   getGameWinner returns the int stored in the gameWinner field 
   @return the integer representing the winner of the game
   */
   public int getGameWinner()
   {
      return this.gameWinner;
   }
   
   //Define the split method used in the constructor
      
   /**
   split method takes in a Deck object of 52 cards and splits it into two 26-card Deck objects. The method will return one
   of the new decks, and the passed Deck object will become the second new deck.
   @param deckObject the Deck object that one wishes to be split into two smaller decks
   @return one of the new Deck objects
   */
   public Deck split(Deck deckObject)
   {
      //Set the size of the new Deck
      int newDeckSize = Deck.HALF_DECK;
      
      //Create an array of cards corresponding to the first newDeckSize number of cards from the deckObject
      ArrayList<Card> newDeckCardArray = new ArrayList<Card>(newDeckSize);
      for(int i=0; i<newDeckSize; i++)
      {
         newDeckCardArray.add(i, deckObject.deal());
      }
      
      //Create a deck corresponding to the first half of the Cards in the deckObject
      Deck newDeck = new Deck(newDeckSize);
      newDeck.setDeck(newDeckCardArray);
      
      //Shuffle decks
      newDeck.shuffle();
      deckObject.shuffle();
      
      //Return newDeck, which is one of the split decks; deckObject is now the other split deck
      return newDeck;
   }
}