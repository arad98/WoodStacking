/**
 * class tree holds date and methods for the tree type used in lumber material and if it has been pressure treated or not
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tree {
    boolean pressureTreated = false;
    private ArrayList<String> currentTreeList = new ArrayList<String>();
    private String currentTree = "Default: fake wood";
    private double avgdensity = 0; // density of the wood type lb/ft^3

    public Tree() {
        setCurTreeFromList(0);
        updateTreeList();
    }

    public Tree(String input, double density) {
        updateTreeList();
        setCurTree(input,density);
    }

    public Tree(Object copy) {
        if(copy instanceof Tree) {
            setCurTree(((Tree) copy).getCurrentTree(),((Tree) copy).getAvgdensity());
            setPressureTreated(getPressureTreated());
        }
    }

    /**
     * getter for avg density val
     * @return int val for average density
     */
    public double getAvgdensity() {
        return avgdensity;
    }
    /**
     * setter for avg density of wood
     * @param den input for new density val lb/ft^3
     */
    public void setAvgdensity(double den) {
        this.avgdensity = den;
    }

    /**
     * setter for avg density of wood
     * @param input input for new density val lb/ft^3
     */
    public void setAvgdensity(String input) {
        String treeTxt = input;
        String[] densitySplit = treeTxt.split("-");
        double out = Double.parseDouble(densitySplit[1]);
        setAvgdensity(out);
    }

    /**
     * setter method for pressure treated value
     * @param in boolean input
     */
    public void setPressureTreated(Boolean in) {
        pressureTreated = in;
    }

    /**
     * getter method for pressure treated val
     * @return the pressure treated boolean value
     */
    public Boolean getPressureTreated() {
        return pressureTreated ;
    }

    /**
     * getter method for current tree selected
     * @return the current tree value
     */
    public String getCurrentTree() {
        return currentTree;
    }

    /**
     * method reads the treeList file and updates the current list of tree types
     */
    public void updateTreeList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader((new FileReader("src/TreeList.txt")));
            String line = reader.readLine();
            while(line != null) {
                currentTreeList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * setter method for the currently selected tree
     * @param in number that selects what tree from the list
     * @throws Exception if user selectes a nubmer greater than the list or smaller than 0 throws it
     */
    public void setCurTreeFromList(int in) {
        try {
            if (in < 0 || in > currentTreeList.size()) {
                throw new woodException("value input for new tree must be in the list");
                // todo create interface that asks for the type of wood/ knows the type of wood and can be selected
            } else {
                this.currentTree = currentTreeList.get(in);
                setAvgdensity(getCurrentTree());
            }
        } catch(Exception e) {
            System.out.print("");
        }
    }

    /**
     * setter method for the current tree also adds it to the total tree list
     * @param input string val
     */
    public void setCurTree(String input,double density) {
        if(listSize() > 0) {
            String out = input + "density-" + density + "-ft/lb^3";
            currentTreeList.add(out);
            currentTree = out;
        } else {
            setCurTreeFromList(0);
        }

    }

    /**
     * method lists the current treeList
     * @return the current treeList available
     */
    public String getList() {
        String out = "";
        for(int i = 0; i < currentTreeList.size();i++) {
            out += currentTreeList.get(i) + "\n";
        }
        return out;
    }

    /**
     * toString method for getting the tree and pressure treated status
     * @return
     */
    public String toString() {
        String out = "";
        if(pressureTreated) {
            out += "Pressure treated ";
        }
        out+= getCurrentTree();
        return out;
    }

    /**
     * method gets the current number of tree wood types in the list and available for selection
     * @return int val for the size of the tree list
     */
    public int listSize() {
        return currentTreeList.size();
    }

    public static void main(String[] args) {
        Tree test = new Tree();
        System.out.println(test.getCurrentTree());
        System.out.println(test.getList());
        System.out.println(test.listSize());
        System.out.println(test.getAvgdensity());
        test.setCurTreeFromList(14);
        System.out.println(test.getCurrentTree());
        test.setCurTreeFromList(-5);
        System.out.println(test.getCurrentTree());
        test.setCurTreeFromList(3);
        System.out.println(test.getCurrentTree());
        System.out.println(test.getAvgdensity());
        test.setCurTreeFromList(9);
        System.out.println(test.getAvgdensity());
        test.setPressureTreated(true);
        System.out.println(test.getCurrentTree());
        System.out.println(test.toString());
        System.out.println(test.getAvgdensity());
    }
}


