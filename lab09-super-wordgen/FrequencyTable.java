import structure5.*;
import java.util.Iterator;
import java.util.Random;

// Students, please implement this class

/**
 * A FrequencyTable stores a set of characters each of which has
 * an associated integer frequency
 */
public class FrequencyTable {
  protected Hashtable<Character, Integer> ftable;
  protected int count;

  /** Construct an empty FrequencyTable */
  public FrequencyTable() {
    this.ftable = new Hashtable<>();
    this.count = 0;
  }

  /** add(char ch)
   * If ch is in the FrequencyTable, increment its associated frequency
   * Otherwise add ch to FrequencyTable with frequency 1
   * @pre input must be a character.
   * @param ch is the String to add to the FrequencyTable
   */
  public void add(char ch) {
    if (ftable.containsKey(ch)) {
      ftable.put(ch, ftable.get(ch) + 1); //updating frequency if key found.
    } else {
      ftable.put(ch, 1); //adding new ch.
    }
    count++;
  }

  /** Selects a character from this FrequencyTable with probabality equal to its relative frequency.
   * @return a character from the FrequencyTable
   */
  public char choose() {
    int totalFreq = 0;
    Random rand = new Random();
    int randNum = rand.nextInt(count);
    Iterator<Character> iter = ftable.keys();
    while (iter.hasNext()) {
      Character ch = iter.next();
      int freq = ftable.get(ch);
      totalFreq += freq;
      if (randNum <= totalFreq) {
        return ch;
      }
    }
    return '\0';
  }

  /** Produce a string representation of the FrequencyTable
   * @return a String representing the FrequencyTable
   */
  public String toString() {
    String keys = "";
    String values = "";
    String rows = "";
    for (Character ch: ftable.keySet()) {
      keys = String.valueOf(ch);
      values = String.valueOf(ftable.get(ch));
      rows += keys + " | " + values + "\n";
    }
    return rows;
  }

  // Use main to test your FrequencyTable class
  /**
   * main Run basic tests for FrequencyTable class.
   * @param args no input from user.
   */
  public static void main(String[] args) {
    FrequencyTable ftable = new FrequencyTable();
    ftable.add('d');
    ftable.add('k');
    ftable.add('l');
    System.out.println(ftable);
    ftable.add('d'); //adding already existing character to see update
    System.out.println(ftable);
  }

}
