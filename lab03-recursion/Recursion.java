/*
 * Recursion.java
 *
 * Starter code for the recursion lab.
 *
 */

import java.util.Arrays;

import structure5.*;


public class Recursion {

  // Note: Warmup questions are not graded, but you may wish to
  // complete & test them since later, graded questions build
  // on them.

  /***** Warmup 0.1 ********************************************/

  /**
   * Takes a non-negative integer and returns the sum of its digits.
   * @param n a non-negative integer
   * @return the sum of n's digits
   * @pre n must be greater than 0 and less than Max integer. 
   * @post result must be less than max integer.
   */
  public static int digitSum(int n) {
    Assert.pre(n > 0 && n < Integer.MAX_VALUE, "Your input is either negative or above the max integer or it is.");
    int length = String.valueOf(n).length();
    int unit = 0;
    int nextNumBlock = 0;
    int sum = 0;
    if (length == 1){
        sum = n;
    } else {
        unit = n % 10; 
        nextNumBlock = n/10;
        sum = unit + digitSum(nextNumBlock);
    }
    Assert.post(sum < Integer.MAX_VALUE, "Your digit sum is above the max integer.");
    return sum;
  }

  /***** Warmup 0.2 ********************************************/

  /**
   * Helper method that returns all possible subsets of an integer. 
   * @param setOfNums array of integers
   * @param index current index
   * @return a an integer.
   * @pre targetsum should be less than MaxValue of int. 
   * @post should be a boolean. 
   */
  private static boolean canMakeSumHelper (int[] setOfNums, int targetSum, int index){
    if (targetSum == 0){
      return true;
    } else if (index == setOfNums.length){
      return false;
    }       
    return canMakeSumHelper(setOfNums, targetSum - setOfNums[index], index + 1) || canMakeSumHelper(setOfNums, targetSum, index + 1);
    }
 
  
  /**
   * Given a set of integers and a target number, determines
   * whethere there is a subset of those numbers that sum to the
   * target number.
   *
   * @param setOfNums a set of numbers that may appear in the subset
   * @param targetSum the target value for the subset
   * @return true if some subset of numbers in setOfNums sums to targetSum
   * @pre targetSum should be < max value of integer. 
   *      no element in setOfNums should be greater than maxvalue of integer. 
   * @post must be a boolean. 
   */
  public static boolean canMakeSum(int[] setOfNums, int targetSum) {
    return canMakeSumHelper(setOfNums, targetSum, 0);
  }


  /*****  1  ***************************************************/

  /**
   * Return number of cannoballs in pyramid with the given `height`.
   *
   * @param height the height of the cannonball tower
   * @return the number of cannonballs in the entire tower
   * @pre the height should be less > 0 and less than maxvalue of integer.
   * @post the sum of cannon balls must be less than maxvalue of integer. 
   */
  public static int countCannonballs(int height) {
    Assert.pre(height > 0 && height < Integer.MAX_VALUE, "Your input is less than 1 or greater than integer max value");
    int sum = 0;
    if(height == 1){
      sum = 1;
    } else {
      sum = height * height + countCannonballs(height - 1);
    }
    Assert.post(sum < Integer.MAX_VALUE, "Your result is greater than integer max value");
    return sum;
  }


  /*****  2  ***************************************************/

  /**
   * This is a recursive helper method that helps reverse a string
   * @param stringToReverse the string to reverse
   * @return reversed string. 
   * @pre string parameter should be a string and not null
   * @post length of reversed string should be equal to that of initial string.
   */
  private static String reverseString (String stringToReverse, int index){
    String type = stringToReverse.getClass().getSimpleName(); //To get string type
    Assert.pre(type != "String" && stringToReverse != null, "Input is not a string."); //Makes sure it is a String.
    if(index == 0){
      return stringToReverse.charAt(0) + "";
    }else {
      char letter = stringToReverse.charAt(index);
      return letter + reverseString(stringToReverse, index - 1); //prints out string in reverse oder. 
    }
  }

  /**
   * A method that determines if a string reads the same forwards
   * and backwards.
   *
   * @param str the string to check
   * @return true if `str` is a palindrome.
   * @pre string parameter should be a string and non null
   * @post  result is not a boolean
   */
  public static boolean isPalindrome(String str) {
    String reverse = reverseString (str, str.length() - 1);
    String type = str.getClass().getSimpleName(); //To get string type
    Assert.pre(type != "String" && str != null, "Input is not a string."); //Makes sure it is a String.
    if(str.equalsIgnoreCase(reverse)){
      return true;
    }else if(str.isBlank()){
      return true;
    }
    return false;
  }

  /*****  3  ***************************************************/

  /**
   * Checks whether `str` is a string of properly nested and matched
   * parens, brackets, and braces.
   *
   * @param str a string of parens, brackets, and braces
   * @return true if str is properly nested and matched
   * @pre string parameter should be a string and not be a null value
   * @post must be a boolean
   */
  public static boolean isBalanced(String str) {
    String paren = "()";
    String braces = "{}";
    String brackets = "[]";
    String type = str.getClass().getSimpleName(); // To get variable type.
    Assert.pre(type != "String" && str != null, "Input is not a string."); //Makes sure it is a String.
    if(str.length() == 0){
      return true;
    } else if(str.contains(paren)){
      String withoutParen = str.replace(paren, "");    //removing valid parentheses
      return isBalanced(withoutParen);                              
    } else if (str.contains(braces)){
      String withoutBraces = str.replace(braces,"");   //removing valid braces
      return isBalanced(withoutBraces);
    } else if (str.contains(brackets)){
      String withoutBrackets = str.replace(brackets, "");  //removing valid brackets
      return isBalanced(withoutBrackets);
    }
    return false;
  }

  /*****  4  ***************************************************/

  /**
   * A method to print all subsequences of str (order does not matter).
   *
   * @param str string to print all subsequences of
   * @pre it should be a string and it should be non null
   * @post cannot contain an element not in str (except the empty set).
   */
  public static void subsequences(String str) {
    String type = str.getClass().getSimpleName(); // To get variable type.
    Assert.pre(type != "String" && str != null, "Input is not a string."); //Makes sure it is a String.
    subsequenceHelper(str, "");
  }

  /**
   * Helper method for subsequences method
   * `soFar` keeps track of the characters currently in the substring
   *   being built
   * @param str String variable from which subset is derived. 
   * @param soFar String variable.
   * @pre variables str and soFar must be strings.
   * @post cannot contain an element not in str (except the empty set).
   */
  protected static void subsequenceHelper(String str, String soFar) {
    String type = str.getClass().getSimpleName(); // To get variable type.
    Assert.pre(type != "String", "Input is not a string."); //Makes sure it is a String.
    if (str.length() == 0){
      System.out.print(soFar + ",");
    } else{
      String withoutLetter = soFar;                                    //not choosing to add a letter
      subsequenceHelper(str.substring(1), withoutLetter);  //recursive call on route without letter
      String withLetter = soFar + str.charAt(0);                //choosing a letter.
      subsequenceHelper(str.substring(1), withLetter);     //recursive call on route with letter. 
    }
  }

  /*****  5  ***************************************************/

  /**
   * A method to print the binary digits of a number.
   *
   * @param number the number to print in binary
   * @pre
   * @post
   */
  public static void printInBinary(int number) {
    Assert.pre(number >= 0 && number < Integer.MAX_VALUE, "Your input is either negative or above the max integer or it is.");
    int remainder = number % 2; //remainder when divided by 2
    int quotient = number / 2;  // quotient (result) of division
    if (number > 0 && quotient == 0){   
      System.out.print("1");
    } else if (quotient > 0){
      printInBinary(quotient);        //recursive call on quotient
      System.out.print(remainder);    //printing remainder
    } else if (number == 0) {          
      System.out.print("0");        
    }   
  }


  /*****  6a  ***************************************************/

  private static boolean canMakeSumHelper2 (int[] setOfNums, int targetSum, int index){
    if (targetSum == 0){
      System.out.print(setOfNums[index]);
      return true;
    } else if (index == setOfNums.length){
      return false;
    }       
    return canMakeSumHelper2(setOfNums, targetSum - setOfNums[index], index + 1) || canMakeSumHelper(setOfNums, targetSum, index + 1);
    }
  /**
   * Return whether a subset of the numbers in nums add up to sum,
   * and print them out.
   *
   * @param nums array of integers from which a subsetsum matches target sum. 
   * @param targetSum int variable that determines the target sum for ints
   * @return a boolean
   * @pre targetSum must be less than maxvalue of Integers. 
   * @post must return a boolean. 
   */
  public static boolean printSubsetSum(int[] nums, int targetSum) {
    return canMakeSumHelper2(nums, targetSum, 0);
  }

  /*****  6b  ***************************************************/

  /**
   * Return the number of different ways elements in nums can be
   * added together to equal sum (you do not need to print them all).
   *
   * @param nums array of integers
   * @param targetSum the amount match with. 
   * @return returns number of solutions
   * @pre target sum must be < maxvalue of integers. 
   * @post value must be < maxvalue of integers.
   */
  public static int countSubsetSumSolutions(int[] nums, int targetSum) {
    return (countHelper(nums, targetSum, 0));
  }

  private static int countHelper(int [] setOfNums, int targetSum, int index) {
    int count = 0;
    if (targetSum==0){
      return 1;
    } else if( setOfNums.length == index){
      return 0;
    } else if(countHelper(setOfNums, targetSum, index + 1) == 1) {
      count += 1;
      countHelper(setOfNums, targetSum, index + 1);
    } else if(countHelper(setOfNums, targetSum - setOfNums[index], index + 1) == 1){
      count += 1;
      countHelper(setOfNums, targetSum - setOfNums[index], index + 1);
      }
      return count;
  }
 


  /***********************************************************/

  /**
   * Add testing code to main to demonstrate that each of your
   * recursive methods works properly.
   *
   * Think about the so-called corner cases!
   *
   * Remember the informal contract we are making: as long as all
   * predconditions are met, a method should return with all
   * postconditions met.
   */

  protected static void testCannonballs() {
    System.out.println("Testing cannonballs: ....");
    System.out.println(countCannonballs(3));
    System.out.println(countCannonballs(10));
  }

  protected static void testPalindrome() {
    System.out.println("Testing isPalindrome: ....");
    System.out.println(isPalindrome("mom"));
    System.out.println(isPalindrome("deeded"));
    System.out.println(isPalindrome("ablewasIereIsawelba"));
  }

  protected static void testBalanced() {
    System.out.println("Testing isBalanced: ....");
    System.out.println(isBalanced("[{[()()]}]"));
    System.out.println(isBalanced("[{[()()]}][{[()()]}]"));
    System.out.println(isBalanced("[{[()()]}{]{[()()]}]"));
  }

  protected static void testSubsequence() {
    System.out.println("Testing subsequences: ....");
    subsequences("abc");
    System.out.println();
    subsequences("CSCI136");
    System.out.println();
    subsequences("a");
    System.out.println();
    subsequences("");
    System.out.println();
  }

  protected static void testBinary() {
    System.out.println("Testing printInBinary: ....");
    printInBinary(0);
    System.out.println();
    printInBinary(30);
    System.out.println();
    printInBinary(1);
    System.out.println();
    printInBinary(110);
    System.out.println();
    printInBinary(2048);
    System.out.println();
    printInBinary(43);
    System.out.println();
      }

  protected static void testCanMakeSum() {
    System.out.println("Testing canMakeSum: ....");
    int[] numSet = {2, 5, 7, 12, 16, 21, 30};
    System.out.println(canMakeSum(numSet, 21));
    System.out.println(canMakeSum(numSet, 22));
    System.out.println(canMakeSum(numSet, 3));
    System.out.println(canMakeSum(numSet, 30));
  }

  protected static void testPrintSubsetSum() {
    System.out.println("Testing printSubsetSum: ....");
    int[] numSet = {2, 5, 7, 12, 16, 21, 30};
    System.out.println(printSubsetSum(numSet, 21));
    System.out.println(printSubsetSum(numSet, 22));
    System.out.println(printSubsetSum(numSet, 3));
    System.out.println(printSubsetSum(numSet, 30));
  }

  protected static void testCountSubsetSum() {
    System.out.println("Testing countSubsetSumSolutions: ....");
    int[] numSet = {2, 5, 7, 12, 16, 21, 30};
    System.out.println(countSubsetSumSolutions(numSet, 21));
    System.out.println(countSubsetSumSolutions(numSet, 22));
    System.out.println(countSubsetSumSolutions(numSet, 3));
    System.out.println(countSubsetSumSolutions(numSet, 30));
  }

  /**
   * Main method that calls testing methods to verify
   * the functionality of each lab exercise.
   *
   * Please supplement the testing code with additional
   * correctness tests as needed.
   */
  public static void main(String[] args) {
    testCannonballs();
    testPalindrome();
    testBalanced();
    testSubsequence();
    testBinary();
    testCanMakeSum();
    testPrintSubsetSum();
    testCountSubsetSum();
  }
}
