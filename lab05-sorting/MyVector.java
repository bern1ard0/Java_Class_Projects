import structure5.*;
import java.util.Comparator;

public class MyVector<E> extends Vector<E> {
    /**
     * constructor for MyVector. 
     */
    public MyVector() {
        super();
    }
    /**
     * @pre i and j must be integers. 
    * Swaps the two elements in arr at index i and at index j.
    * @param arr A vector of type E.
    * @param i a valid index into arr.
    * @param j another valid index into arr.
    * @return Nothing, except that the two elements are swapped in-place.
    */
    public void swap(int i, int j) {
        E temp = get(i);
        set(i, get(j));
        set(j, temp);
    }
    /**
    * A sort method that performs an in-place comparison sort (bubble sort).
    * @param data A vector containing data of type E.
    * @param c A comparator for type E.
    * @return Nothing, except that data is sorted in-place.
    */
    public void sort(Comparator<E> c) {
        for (int numSorted = 0; numSorted < size(); numSorted++) {
            for (int i = 1; i < size(); i++) {
                if (c.compare(get(i - 1), get(i)) > 0) {
                    swap(i - 1, i);
                }
            }
        }
    }
}
