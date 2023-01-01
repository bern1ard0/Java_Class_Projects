import structure5.*;

// Students, please implement this class

/**
* A Table holds a collection of strings, each of which has an
* associated FrequencyTable
*/
public class Table {
  protected Hashtable<String, FrequencyTable> table;

  /** Construct an empty table */
  public Table() {
    this.table = new Hashtable<>();
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyTable
  * by adding value to it
  * Otherwise, create a FrequencyTable for key and add value to it
  * @pre String parameter must be a key and character parameter must be a letter.
  * @param key is the desired k-letter sequence
  * @param value is the character to add to the FrequencyTable of key
  */
  public void add(String key, char value) {
    Assert.pre(key != null, "Key is either null or value is not a character.");
    if (table.containsKey(key)) {
      table.get(key).add(value);
    } else {
      FrequencyTable keyV = new FrequencyTable();
      keyV.add(value);
      table.put(key, keyV);
    }
  }

  /**
  * If key is in the table, return one of the characters from
  * its FrequencyTable with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  * @param key is the k-letter sequence whose frequencies we want to use
  * @return a character selected from the corresponding FrequencyTable
  */
  public char choose(String key) {
    Assert.pre(key != null, "String value is null");
    if (table.containsKey(key)) {
      return table.get(key).choose();
    }
    return '\0';
  }

  /** Produce a string representation of the Table
  * @return a String representing this Table
  */
  public String toString() {
    String keys = "";
    String values = "";
    String row = "";
    for (String st: table.keySet()) {
      keys = st;
      values = table.get(st).toString();
      row += keys + " | " + values + "\n";
    }
    return row;
  }

  // Use main to test your Table class
  /**
   * main Runs basic test for Table class.
   * @param args no input from user.
   */
  public static void main(String[] args) {
    Table table = new Table();
    table.add("ch", 'k');
    table.add("kh", 'd');
    System.out.println(table);
    table.add("ch", 'l'); //adding value to already existing key.
    System.out.println(table);
  }
}
