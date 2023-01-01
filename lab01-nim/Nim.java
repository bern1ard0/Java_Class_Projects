import java.util.Random;
import java.util.Scanner;

/**
 * This file contains starter code for the Nim game.  We copied
 * over method names from the TwoPlayerGame interface and provided
 * just enough implementation so that the compiler can compile them
 * without producing any errors.  These implementations are not
 * correct, so you should replace them with real implementations.
 *
 * We also added an empty constructor, which you should implement.
 * s
 * A good place to start is by writing a main method...
 */
public class Nim implements TwoPlayerGame {
    private int[] board;
    private int currentPlayer;
    private int numPiles;

    /**
     * Initializes a new Nim board game.
     *
     * @param numPiles The number of matchstick piles.
     * @param minMatches The smallest number of matches per pile.
     * @param maxMatches The largest number of matches per pile.
     */
    public Nim(int numPiles, int minMatches, int maxMatches) {
        this.numPiles= numPiles;
        board= new int[numPiles];
        for (int i = 0; i < board.length; i++){
            Random randomNumOfMatches= new Random(); //randomize number of matches
            //randomize board with adequate formula
            board[i] = randomNumOfMatches.nextInt(maxMatches - minMatches + 1)+minMatches;
        }
        currentPlayer=0;
    }
  
    /**
     * Returns the current value for a given resource.
     *
     * @param resource Describes the game element.
     * @returns The current value of the resource.
     */  
    public int getResource(int resource)
    
    
     {
        return board[resource - 1]; // (resource-1) is done to match the index of board.

    }
    
    /**
     * Sets the game state. Should not check whether the given
     * parameters are valid. isValidMove should be called before
     * calling this method to ensure that a move is legal.
     *
     * @param resource Which resource to alter (e.g., position, gamepiece, pile of matches, etc.)
     * @param updatedValue The updated value of the resource (e.g., how many matches remain, the updated position of a piece, etc.)
     */
    public void setResource (int resource, int updatedValue) {
        board[resource-1]= updatedValue;   //updates value of board. 
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
        currentPlayer=player; 
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
    public boolean isValidMove(int resource, int updatedValue) {
        //verifies if user entered a valid pile
        if(resource<1 || resource>numPiles){   
            return false;
        }
        //checks if matchsticks in pile are exhausted
        if(updatedValue<0){
            return false;
        }
        //checks if user removes a valid number of matches. 
        if(updatedValue>getResource(resource)){
            return false;
        }
        return true;
    }
    
    /**
     * Returns true if the game is over.
     * @returns True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        //verifies that every integer member of board is not zero, else, game is over.
        for(int i = 0; i < board.length; i++){
            if( board[i] != 0){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Displays the board on screen.
     */
    public void displayBoard() {
        for(int i=0;i<numPiles;i++){
            int numMatches=board[i];                    
            System.out.print("["+ (i+1)+"]"+ " = ");  //Displays on screen. 
            while(numMatches>0){
                System.out.print("|");   
                numMatches--;
            }
            System.out.println();
        }
    }
    /**
     * Gives order in which the game is played. 
     * @param args
     */

    public void TakeATurn(){
        while(!isGameOver()){
            int player= getPlayer();
            System.out.println("Player "+ (player+1) + ":");
            displayBoard();
            System.out.print("Enter the pile number: " );
            int pileNum = getUserInput();
            System.out.println("Enter number of Matches: ");
            int numMatches = getUserInput();
            int updatedValue = getResource(pileNum)-numMatches;
            if(isValidMove(pileNum, updatedValue )){
            //update the board.
            setResource(pileNum, updatedValue);
            //change player.
            if (player==1){
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
        System.out.println("Game Over! Player" + (getPlayer()+1) + " Wins");
  
        //
    }
    // Helper function to help with input of values.
    private int getUserInput(){
        int numberOfMatches = 0;
        // try and catch makes sure the input is an int. 
        // If not, it reruns while telling user, the input was invalid.
        try{
            Scanner scan= new Scanner(System.in);
            numberOfMatches= scan.nextInt();          
        }catch(Exception e){
            System.out.println("Invalid input! Try again!");
            getUserInput();
        }
        return numberOfMatches;
    }


    /**
     * The entry point to the program.  It currently ignores
     * the contents of the args array.
     * @param args
     */
    public static void main(String[] args) {
        Nim game= new Nim (5,2,5);
        game.TakeATurn();

    }
  }
  