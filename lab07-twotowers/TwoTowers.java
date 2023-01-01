import structure5.*;
/**
 * makes use of the SubsetIterator class to solve a problem.
 */
public class TwoTowers  {
    /**
     * @pre vec is not null
     * @param vec vector which is iterated upon to get sum of squareroots.
     * @return sum of square roots of integers in a vector
     * @post result < Integer.Max_Value
     */
    public static double vectorSqrtSum (Vector<Integer> vec) {
        Assert.pre(vec != null, "Vector is null");
        double result = 0;
        for (int i = 0; i < vec.size(); i++) {
            result += Math.sqrt(vec.get(i));
        }
        return result;
    }
    /**
     * @pre vecIterator is not null
     * @param vecIterator Parent set of subsets. 
     * @param halfHeight max num that should not be reached.
     * @return subset (vector) with sum closest to half height. 
     */
    public static Vector<Integer> getBestSubset (SubsetIterator<Integer> vecIterator, double halfHeight) {
        Assert.pre(halfHeight < Double.MAX_VALUE, "Halfheight is too large");
        double  maxSoFar = 0;
        Vector<Integer> bestSubset = new Vector<>();
        for (Vector<Integer> v : vecIterator) {
            //sum should not be above half height.
            if (vectorSqrtSum(v) > maxSoFar && vectorSqrtSum(v) < halfHeight) {
                maxSoFar = vectorSqrtSum(v);
                bestSubset = v;
            }
        }
        //vector with sum closest to halfHeight.
        return bestSubset;
    }
    /**
     * 
     * @param vecIterator parents set of subsets.
     * @param firstClosest value used to determine second best subset.
     * @param best vector to be ommitted.
     * @return subset(vector) with sum closest to firstClosest
     */
    public static Vector<Integer> getSecondSubset(SubsetIterator<Integer> vecIterator, double firstClosest, Vector<Integer> best) {
        Assert.pre(firstClosest < Double.MAX_VALUE, "Max Value exceeded");
        double  maxSoFar = 0;
        Vector<Integer> secondBestSubset = new Vector<>();
        for (Vector<Integer> v : vecIterator) {
            //making sure that vector is not the same as the first closest.
            if (vectorSqrtSum(v) > maxSoFar && vectorSqrtSum(v) <= firstClosest && !v.equals(best)) {
                maxSoFar = vectorSqrtSum(v);
                secondBestSubset = v;
            }
        }
        return secondBestSubset;
    }
    /**
     * @pre block is less that max value of int.
     * @param block number to help populate the vector used to create parent set.
     * @return vector used to create parent set.
     */
    public static Vector<Integer> vectPopulator (int block) {
        Assert.pre(block < Integer.MAX_VALUE, "Max Value exceeded");
        Vector<Integer> vect = new Vector<Integer>();
        for (int i = 1; i <= block; i++) {
            vect.add(i);
        }
        return vect;
    }
    public static void main(String[] args) {
        int block = Integer.parseInt(args[0]);
        Vector<Integer> intVector = vectPopulator(block);
        double halfHeight = vectorSqrtSum(intVector)/2;
        SubsetIterator<Integer> vectIterator = new SubsetIterator<Integer>(intVector);   
        System.out.println("Half height (h/2) is: " + halfHeight);
        Vector<Integer> subset1 = getBestSubset(vectIterator, halfHeight);
        System.out.println("The best Subset is: " + subset1 + " : " +
        vectorSqrtSum(subset1));
        double newMax = vectorSqrtSum(subset1);
        SubsetIterator<Integer> vectIterator2 = new SubsetIterator<Integer>(intVector);  
        Vector<Integer> subset2 = getSecondSubset(vectIterator2, newMax, subset1);
        System.out.println("The second best Subsbset is: "  + subset2 + " : " +
        vectorSqrtSum(subset2) );
    }  
}
