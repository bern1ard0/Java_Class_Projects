import structure5.*;
/**
 * This class extends the AbstractIterator class. 
 * Hence, must implement certain methods (next, hasNext, get, reset)
 * which will make it Iterable. 
 * It is implemented over a generic type for a wider reach in 
 * functionality. 
 */
public class SubsetIterator<E> extends AbstractIterator<Vector<E>> {
    protected long val;
    protected Vector<E> vec;
    /**
     * @pre vec is not null
     * @param vec vector used to build constructor.
     */
    public SubsetIterator (Vector <E> vec) {
        this.vec = vec;
        reset();
    }
    /**
     * @pre vec is not null
     * @returns vector of current state (val)
     */
    public Vector<E> next() {
        Vector<E> a = get();
        val ++;
        return a;
    }
    /**
     * @pre vec is not null
     * @return true if another value exists, else false
     */
    public boolean hasNext() {
        return val <(1l << vec.size());
    }
    /**
     * resets current state of vector. 
     */
    public void reset() {
        val = 0;
    }
    /**
     * @return returns a vector corresponding to current subset.
     */
    public Vector<E> get() {
        Vector<E> f = new Vector<>();
        for (int i =0; i<vec.size(); i++) {
            if ((val & ((1l << i) )) != 0) {
                f.add(vec.get(i));
            }
        }
        return f;
    }
    //test
    public static void main(String[] args) {
        //creates a new vector. 
        Vector<Integer> testVec = new Vector<>();
        testVec.add(0);
        testVec.add(1);
        testVec.add(2);
        testVec.add(3);
        testVec.add(4);
        testVec.add(5);
        testVec.add(6);
        testVec.add(7);
        SubsetIterator<Integer> ci = new SubsetIterator<Integer>(testVec);
        for (Vector<Integer> c : ci) {
            System.out.println(c);
        }
    }
}

