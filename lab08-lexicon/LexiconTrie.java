import structure5.*;
import java.util.Iterator;
import java.util.Scanner;

//STUDENTS: fill in the following class with your implementation.
//We have included the names of the methods that you should fill in
//as a starting point.
/**
 * Class that correctly manipulates lexiconNodes to for a try.
 */
public class LexiconTrie implements Lexicon {

    protected LexiconNode root = new LexiconNode(' ', false);
    protected Vector<LexiconNode> rootChild = new Vector<>(26);
    protected int size = 0;
    protected Vector<String> allWords = new Vector<>();
    
    /**
     * adds a word to lexicon trie.
     * @return true if word is added else, false.
     */
    public boolean addWord(String word) {
        LexiconNode node = root;
        String lowerWord = word.toLowerCase(); //convert to lowercase
        if(containsWord(lowerWord)) { //if word already contained, return false.
            return false;
        } else {
            allWords.add(lowerWord); //add word to list of all words.
            while(!lowerWord.isEmpty()) { //loop through each letter in word.
                char first = lowerWord.charAt(0);
                if(node.getChild(first) == null) { //if first letter not a child of root.
                    node.addChild(new LexiconNode(first, false)); //add first letter as child.
                }
                node = node.getChild(first); //update the value of node
                lowerWord = lowerWord.substring(1); //cut out first word and get to remaining string.
            }
            node.isWord = true; //set last letter's boolean value to true.
            size++; //update size.
            return true;
        }     
    }

    /**
     * @param filename filename to read from.
     * @return number of words added from filename.
     */
    public int addWordsFromFile(String filename) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            if(addWord(sc.next())) {
                count++;
            }
        }
        return count;
    }

    /**
     * 
     * @param word word to be removed from trie. i.e set last letter's
     *             boolean value to false.
     * @return true if successfully removed, else, false.
     */
    public boolean removeWord(String word) {
        String lowerWord = word.toLowerCase();
        all
        if(containsWord(lowerWord)) { //verify if word is in try.
            find(lowerWord, root).isWord = false;
            size--;
            return true;
        }
        return false;
    }

    /**
     * @return number of words in trie.
     */
    public int numWords() {
        return size;
    }

    /**
     * finds word in the trie.
     * @param word word to find.
     * @param first first letter of word.
     * @return lexiconNode that has letter that is equal to last letter in word.
     */
    public LexiconNode find(String word, LexiconNode first) {
        String lowerWord = word.toLowerCase();
        LexiconNode next = first.getChild(word.charAt(0));
        if (next == null) {
            return null;
        }
        if (next.getIsWord() && lowerWord.length() == 1) {
            return next;
        } 
        else{
            return find(word.substring(1), next);
        }
    }

    /**
     * @param word word to be checked if found in trie.
     * @return true if word is foudn else, returns false.
     */
    public boolean containsWord(String word){
        LexiconNode node = find(word, root);
        return node != null && node.getIsWord();
    }
    
    /**
     * @param prefix prefix to be found.
     * @return true if prefix is found, else false.
     */
    public boolean containsPrefix(String prefix){
        LexiconNode node = find(prefix, root);
        return node != null;
    }

    /**
     * @return an iterator of strings, over which words can be returned. 
     */
    public Iterator<String> iterator() {
        return allWords.iterator();
    }

    //Optional (extra credit) implementation
    public Set<String> suggestCorrections(String target, int maxDistance) {return null;}

    //Optional (extra credit) implementation
    public Set<String> matchRegex(String pattern){return null;}
}
