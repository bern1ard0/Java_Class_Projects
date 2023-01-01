import structure5.Vector;
import java.util.Scanner;
import java.util.Random;

/* Use the TwoPlayerGame interface to implement the
 * "Silver Dollar Game". You may add as many additional
 * methods as you need.
 */


public class CoinStrip implements TwoPlayerGame {
  private Vector<Integer> board;
  private int currentPlayer;
  private int numberOfCoins;
  private int size;
  private int minCoins = 3;    // setting minimum number of coins to 3
  private int locationOfCoin;

  /**
   * Initializes a new Nim board game.
   *
   * @param numPiles The number of matchstick piles.
   * @param minMatches The smallest number of matches per pile.
   * @param maxMatches The largest number of matches per pile.
   */
  public CoinStrip(int size) {
      this.size = 10;
      size= 10;
      int minCoins = 3;
      //generates random number of coins.
      Random num1= new Random();
      //generates a random locus.
      board= new Vector<Integer>(size);
      //populating board with 0s.
      int i=0;
      while(i<size){
        board.add(0);
        i++;
      }
      //randomizing numbers in array, either 1 or 2. 
      int rand;
      for (int j = 0; j<board.size(); j++){
        Random num2= new Random();
        rand = num2.nextInt(2);      
        board.setElementAt(rand, j);
      }
      //updating the count for every 1 found in vector list.
      int k = 0;
      int count=0;
      while( k < size){
        if(board.get(k)==1){
          board.setElementAt(board.get(k)+count, k);
          count++;
        }else if(board.get(k) == 0) {
        }
        k++;

      }
      this.numberOfCoins = count;
      numberOfCoins = count;
      //set current player value
      currentPlayer = 0;
}

/*
 * checks if board is correctly set. 
 */

    /**
   * Returns the current value for a given resource.
   *
   * @param resource Describes the game element.
   * @returns The current value of the resource.
   */  
  public int getResource(int resource) {
    return board.indexOf(resource);
  
  }
  
  /**
   * Sets the game state. Should not check whether the given
   * parameters are valid. isValidMove should be called before
   * calling this method to ensure that a move is legal.
   *
   * @param resource Which resource to alter (e.g., positionetc.)
   * @param updatedValue New position of coin after n moves (e.g.2moves to the left of current position)
   */
  public void setResource(int resource, int updatedValue) {
    board.setElementAt(resource, updatedValue);
  }
  
  /**
   * Returns the number representing the current player.
   * 
   * @returns The current player.
   */
  public int getPlayer() { 
    return currentPlayer;
  }
  
  /**
   * Changes the current player to the given player.
   * 
   * @param player A player number.
   */
  public void setPlayer(int player) {
    currentPlayer = player;
  }

    /**
   * Displays the board on screen.
   */
  public void displayBoard() {
    for(int i=0;i<board.size();i++) {
      if(board.get(i) == 0){
        System.out.print("[ ]");
      }else{
        System.out.print("["+board.get(i)+"]");
      }                   
  }
  System.out.println();
}

    /**
   * Returns true if the game is over.
   * @returns True if the game is over, false otherwise.
   */
  public boolean isGameOver() {
    //condition for game to be over. 
    if(board.get(numberOfCoins-1)==numberOfCoins){
      return true;
    }
    return false;
  }

    /**
   * Returns true if the specification of a move describes a legal move
   * given all the rules of the game. Note: this does not check whether the
   * move is *good* move, only whether it is legal.
   *
   * @param resource Which resource to alter (e.g., position, gamepiece, pile of matches, etc.)
   * @param updatedValue The updated value of the resource (e.g., how many matches remain, the updated position of a piece, etc.)
   * @return True iff the move is valid.
   */
  public boolean isValidMove(int resource, int updatedValue){
    //if coin number is greater than other coins. 
    if(resource>numberOfCoins){
      return false;
    }
    //if location is already filled
    if(board.get(updatedValue) != 0){
      return false;
    }
    //if final location is below index 0
    if(updatedValue < 0){
      System.out.println(2);
      return false;
    }
    //if user enters a negative number (tries moving it to the right. )
    if(updatedValue > getResource(resource)){
      System.out.println(3);
      return false;
    }
    // to avoid crossing other coins.
    for(int i = getResource(resource)-1; i > updatedValue; i--){
      if(board.get(i) != 0){
        return false;
      }
    }
    return true;
  }

  /*
   * Steps taken when running the game
   */
  public void TakeATurn() {
    while(!isGameOver()) {
      int player= getPlayer();
      System.out.println("Player "+ (player+1) + ":");
      displayBoard();
      System.out.print("Enter the coin: " );
      int coin = getUserInput();
      int coinIndex = getResource(coin);
      System.out.println("Enter number of moves: ");
      int move = getUserInput();
      int updatedValue = coinIndex - move;
      if(isValidMove(coin, updatedValue )){
      //update the board.
      setResource(0, coinIndex);  //resetting value of coinIndex back to 01
      setResource(coin, updatedValue);
      //change player.
      if (player == 1) {
          setPlayer(0);
      }else {
          setPlayer(1);
      }
      //display updatedboard
      displayBoard();
      } else {
          System.out.println("Move is invalid");
      }
    }
    if(getPlayer()==0){
      System.out.println("Game Over! Player" + 2 + " Wins");
    } else{
      System.out.println("Game Over! Player" + 1 + " Wins");
    }
    System.out.println();
  }

  // Helper function to help with input of values.
  private int getUserInput() {
  int input = 0;
  // try and catch makes sure the input is an int. 
  // If not, it reruns while telling user, the input was invalid.
  try{
    Scanner scan= new Scanner(System.in);
    input= scan.nextInt();          
    } catch (Exception e) {
      System.out.println("Invalid input! Try again!");
      getUserInput();
    }
    return input;
  }

  public static void main(String[] args) {
    CoinStrip game= new CoinStrip(10);
    game.TakeATurn();
  }
}

