import java.text.DecimalFormat;

/**
 * class holds data and methods for creating a type of lumber wood with a width (in), depth(in) and length (ft)
 * as of 12/17/19 current implementation does not work for plywood or for non-standard cut wood and all measures are assumed to be nominal
 * not exact dimensions of the lumber as we will be using this to calc the best shape to ship the lumber in when mixed with
 * different types of lumber
 */
public class wood {

    // width and depth are usually in inches while length is in feet
    private int width = 0; //todo some wood is'nt an even inches it could be 1 and 16/33 inches long
    private int depth = 0;
    private int length = 0;
    private Tree tree = new Tree();
    private double weight = -1; // default val is -1 to show if the weight has'nt been calculated

    /**
     * constructor for a type of wood if input is in inches will convert to cm
     * @param w width
     * @param d depth
     * @param l length
     * @param t is the tree wood
     * @param density is the tree wood density val
     * @param pT boolean value if wood is pressure treated
     * @throws Exception if and input is negative
     */
    public wood(int w, int d, int l, String t, double density,boolean pT) throws woodException {
        if(w <= 0 || d <= 0 || l <= 0) {
            throw new woodException("input cannot be less than 0 or 0 for wood dimensions");
            //todo check if when this is thrown the wood object isnt ruined
        } else {
            setWidth(w);
            setDepth(d);
            setLength(l);
        }
        tree = new Tree(t);
        tree.setCurTree(t,d);
        tree.setPressureTreated(pT);
        calctWeight();
    }

    /**
     * regular no arg constructor creates a default object
     * @throws woodException if the weight of the tree is negative
     */
    public wood() throws woodException {
        setWidth(2);
        setDepth(4);
        setLength(8);
        tree = new Tree();
        tree.setCurTreeFromList(0); // douglas fir
        calctWeight();
    }

    /**
     * copy constructor for wood object
     * @param other other wood object
     */
    public wood(Object other) throws woodException {
        if(other instanceof wood) {
            width = ((wood) other).getWidth();
            depth = ((wood) other).getDepth();
            length = ((wood) other).getLength();
            tree = ((wood) other).getTree();
            //todo finish fixing wood class with updated Tree class methods, also implement the weight calculator as another data variable of wood
        } else {
            throw new woodException("Copy object must be wood object");
        }
    }

    /**
     * getter method for width
     * @return width val
     */
    public int getWidth() {
        return this.width;
    }



    /**
     * getter method for Depth
     * @return depth val
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * getter method for length
     * @return length val
     */
    public int getLength() {
        return this.length;
    }

    /**
     * converts the length of the wood in feet to inches
     * @return int val with length in inches
     */
    public int getLengthAsInches() {
        return getLength()*12;
    }

    /**
     * getter method for tree type
     * @return tree type
     */
    public Tree getTree() {
        Tree out = new Tree(tree);
        return out;
    }

    /**
     * setter for width
     * @param in new value for width
     */
    public void setWidth(int in) {
        width = in;
    }

    /**
     * setter for Depth
     * @param in new value for depth
     */
    public void setDepth(int in) {
        depth = in;
    }

    /**
     * setter for length
     * @param in new value for length
     */
    public void setLength(int in) {
        length = in;
    }

    /**
     * setter method for the weight of the wood piece
     * @param in double input
     * @throws Exception if input is a negative
     */
    public void setWeight(double in) throws woodException {
        if(in >= 0 ){
            double newWeight = Double.parseDouble(String.format("%,.2f",in));
            weight = newWeight;
        } else {
            throw new woodException("-error-Weight cant be a negative number ");
        }
    }

    /**
     * method returns the weight of the wood piece in pounds, as avg density is lb/ft^3
     * @return the value of wood weight in pounds
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * method calculates the weight of the wood piece in pounds, as avg density is lb/ft^3
     * @return the value of wood weight in pounds
     */
    public void calctWeight() throws woodException{
        double out = 0;
        double boardFootA = (getDepth() * getLengthAsInches() * getWidth())/144.0;
        double volumeFt = boardFootA/12.0;
        out = volumeFt * this.tree.getAvgdensity();
        try {
            setWeight(out);
        } catch (Exception e) {
            throw new woodException("thrown in method calcWeight somehow");
        }
    }

    public String toString() {
        Tree outTree = getTree();

        String out = "";
        out += getWidth() + " in,";
        out += getDepth() + " in,";
        out += getLength() + " ft, ";
        out += outTree.getCurrentTree() + " , ";
        return out;
    }






}
