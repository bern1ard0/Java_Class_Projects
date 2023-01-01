import structure5.*;
import java.util.Iterator;

/*
* This class creates a LexiconNode which comprises of a
* letter and the boolean value attributed to it, determining
* if its addition to a stream of prefix/word would make an
* addition of a new word or just a new streamline of words(prefix)
*/
class LexiconNode implements Comparable<LexiconNode> {

    /* single letter stored in this node */
    public char letter;
    /* true if this node ends some path that defines a valid word */
    protected boolean isWord;
    /* a data structure for keeping track of the children of
    this LexiconNode */
    protected Vector<LexiconNode> childVector = new Vector<>(26);

   /**
    * Constructor of the LexiconNode.
    * @param letter the letter at node
    * @param isWord truth value attributed to letter/node.
    */
    LexiconNode(char letter, boolean isWord) {
        this.letter = letter;
        this.isWord = isWord;
    }

    /**
     * @return letter held in current node
     */
    public char getLetter() {
        return letter;
    }

    /**
     * 
     * @return boolean attribute of current node.
     */
    public boolean getIsWord() {
        return isWord;
    }

    /**
     * @param o LexiconNode compared to current node.
     * @return integer value determining if lexicon O is greater
     * than or less than current lexicon.
     */
    public int compareTo(LexiconNode o) { 
        return Character.compare(letter, o.getLetter());
    }
    
    /**
     * Add LexiconNode child to correct position in child data structure
     * @pre LexiconNode is not empty.
     * @param ln lexiconNode to be added.
     */
    public void addChild(LexiconNode ln) {
        int i = 0;
        for (i = 0; i < childVector.size(); i ++) {
            if (ln.getLetter() > childVector.get(i).letter) {
                break;
            }
        }
        childVector.add(i, ln);
    }

    /**
     * Get LexiconNode child for 'ch' out of child data structure
     * @pre ch is not null. 
     * @param ch character that determines the lexicon node to be returned.
     * @return returns lexiconNode pertaining to char ch.
     */
    public LexiconNode getChild(char ch) {
        for (LexiconNode child : childVector) {
            if (child.getLetter() - ch == 0) {
                return child;
            }
        }
        return null;
    }

    /**
     * 
     * @return Vector of LexiconNodes.
     */
    public Vector<LexiconNode> getChildren() {
        return childVector;
    }

    /**
     * Remove LexiconNode child for 'ch' from child data structure
     * @param ch character that determines node to be removed.
     */
    public void removeChild(char ch) {
        for (LexiconNode child : childVector) {
            if (child.getLetter() - ch == 0) {
                childVector.remove(child);
            }
        }
     }
    
    /**
     * 
     * @return An Iterator that iterates over children in alphabetical order.
     */
    public Iterator<LexiconNode> iterator() {
        Iterator<LexiconNode> iter = childVector.iterator();
        return iter; 
    }
}
