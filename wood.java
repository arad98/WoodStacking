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

    /**
     * constructor for a type of wood if input is in inches will convert to cm
     * @param w width
     * @param d depth
     * @param l length
     * @param t is the tree wood
     * @param density is the tree wood density val
     * @param pT boolean value if wood is pressure treated
     */
    public wood(int w, int d, int l, String t, double density,boolean pT) {
        setWidth(w);
        setDepth(d);
        setLength(l);
        tree = new Tree(t);
        tree.setCurTree(t,d);
        tree.setPressureTreated(pT);
    }

    public wood() {
        setWidth(2);
        setDepth(4);
        setLength(8);
        tree = new Tree();
        tree.setCurTreeFromList(1);
    }

    /**
     * copy constructor for wood object
     * @param other other wood object
     */
    public wood(Object other) throws Exception {
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

    public String toString() {
        String out = "";
        out += getWidth() + " in,";
        out += getDepth() + " in,";
        out += getLength() + " ft, ";
        out += getTree();
        return out;

    }






}
