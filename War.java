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
   //Define fields
   private Deck playerOneDeck, playerTwoDeck;
   private Card playerOneCard, playerTwoCard;
   private Hand winnerHand;
   private int winner, gameWinner;
   public static final int PLAYER_ONE = 1;
   public static final int PLAYER_TWO = 2;
   public static final int NO_WINNER = 0;
   
   //Define constructor
   
   /**
   Default constructor splits a deck of 52 cards into two disjoint decks of 26 cards and sets the Card objects
   representing each player's "move" to null since the constructor is only called at the beginning of a game.
   */
   public War()
   {
      Deck mainDeck = new Deck();
      mainDeck.shuffle();
      playerOneDeck = split(mainDeck);
      playerTwoDeck = mainDeck;
      gameWinner = NO_WINNER;
      winner = NO_WINNER;
   }
   
   //Define methods
   
   /**
   draw method simulates the battle part of the game War. The top card is dealt from each player's deck face-up. The 
   player who played the higher-ranking card adds both cards to his deck. If both cards are the same, a war occurs. Code
   proceeds as follows: 
      Both split decks deal a card. The Cards are compared, and both cards are added to the first two empty spots in the
      deck field of the winning player's Deck object if there is a winner. If there is a war, the war method is called.
   @throws DeckEmptyException if one or both players are trying to use an empty deck. 
   @throws NoWinnerException if neither card's rank is greater than the other
   */ 
   public void draw() throws DeckEmptyException
   {
      if(playerOneDeck != null && playerTwoDeck != null)
      {
         playerOneCard = playerOneDeck.deal();
         playerTwoCard = playerTwoDeck.deal();
         winnerHand = new Hand();
         winnerHand.add(playerOneCard);
         winnerHand.add(playerTwoCard);
      }
      else
         throw new DeckEmptyException("One of the decks is empty");
   }
   
   
   
   /**
   battle method is called to add the two cards to the secondary deck for the winner of a battle
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
   @param winner The integer representing the player who won the hand 
   */
   public void takeover(int winner)
   {
      if(winnerHand != null)
      {
         winnerHand.shuffle();
         if(winner == 1)
            playerOneDeck.add(winnerHand.getDeck());
         else
            playerTwoDeck.add(winnerHand.getDeck());
         winnerHand = null;
      }
      else
         throw new HandEmptyException("You are trying to takeover a hand with no size");
   }
   
   /**
   war method simulates the process that occurs when a battle produces two equally-ranked cards. The top card from each 
   deck is dealt face-down, and the next one is dealt face up. If the ranks are still equal, the process repeats, 
   otherwise the player with the higher ranking card takes all of the cards, including the face-down cards, and adds
   them to the bottom of his deck after shuffling them.
   @returns an array of all the cards that the winner gets to add to the bottom of his pile.
   @throws DeckEmptyException if one or more of the players is trying to use an empty deck
   */
   public void war() throws DeckEmptyException
   {
      if(playerOneDeck == null || playerTwoDeck == null)
         throw new DeckEmptyException("One of the decks is empty");
      else
      {
         boolean continueWar = true;
         while(continueWar && playerOneDeck != null && playerTwoDeck != null)
         {
            winnerHand.add(playerOneDeck.deal());
            winnerHand.add(playerTwoDeck.deal());
            draw();
            battle();
            if(playerOneCard.compareTo(playerTwoCard) < 0)
            {
               continueWar = false;
               winner = PLAYER_TWO;
            }
            else if(playerOneCard.compareTo(playerTwoCard) > 0)
            {
               continueWar = false;
               winner = PLAYER_ONE;
            }    
         }
         if(playerOneDeck == null || playerTwoDeck == null)
         {
            if(playerOneDeck == null)
               gameWinner = PLAYER_TWO;
            else if(playerTwoDeck == null)
               gameWinner = PLAYER_ONE;
            throw new DeckEmptyException("One of the decks is empty");
         }
      }
   }
   
   /**
   split method takes in a Deck object and splits it into two smaller, disjoint subset Deck objects. If the Deck size is 
   even, each new Deck will contain the same amount of cards. If the Deck size is odd, one new Deck will contain one more
   card than the other. The method will return one of the new decks, and the passed Deck object will become the second
   new deck.
   @param deckObject the Deck object that one wishes to be split into two smaller decks
   @returns one of the new Deck objects
   @throws DeckTooSmallException if the deck has size of zero or one
   */
   public Deck split(Deck deckObject)
   {
      //Determine the size of the new Deck that is being returned; truncate so that an integer is received
      int newDeckSize = deckObject.getSize()/2;
      
      //Create an array of cards corresponding to the first newDeckSize number of cards from the deckObject
      ArrayList<Card> newDeckCardArray = new ArrayList<Card>(newDeckSize);
      for(int i=0; i<newDeckSize; i++)
      {
         newDeckCardArray.add(i, deckObject.deal());
      }
      
      //If the new deck has a valid size (has any positive amount of cards in it), create the Deck object and return it
      if(newDeckSize >= 1)
      {
         //Create a deck corresponding to the first half of the Cards in the deckObject
         Deck newDeck = new Deck(newDeckSize);
         newDeck.setDeck(newDeckCardArray);
         
         //Shuffle decks
         newDeck.shuffle();
         deckObject.shuffle();
         
         //Return newDeck, which is one of the split decks; deckObject is now the other split deck
         return newDeck;
      }
      else
         throw new DeckTooSmallException("Deck is too small to split");
   }
   
   //Define accessor methods
   
   /** 
   getPlayerOneCard returns the Card stored in the playerOneCard field.
   @returns the Card object stored in the playerOneCard field
   */
   public Card getPlayerOneCard()
   {
      return this.playerOneCard;
   }
   
   /**
   getPlayerTwoCard returns the Card stored in the playerTwoCard field.
   @returns the Card object stored in the playerTwoCard field
   */
   public Card getPlayerTwoCard()
   {
      return this.playerTwoCard;
   }
   
   /**
   getWinner returns the winner of a battle. 
   @returns the int stored in the winner field
   */
   public int getWinner()
   {
      return this.winner;
   }
   
   /**
   getWinnerHand returns the Hand stored in the winnerHand field
   @returns the winning hand 
   */
   public Hand getWinnerHand()
   {
      return this.winnerHand;
   }
   
   /**
   getGameWinner returns the int stored in the gameWinner field 
   @returns the integer representing the winner of the game
   */
   public int getGameWinner()
   {
      return this.gameWinner;
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
}