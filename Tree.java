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

    public Tree() {
        updateTreeList();
    }

    public Tree(String input) {
        updateTreeList();
        setCurTree(input);
    }

    public Tree(Object copy) {
        if(copy instanceof Tree) {
            setCurTree(((Tree) copy).getCurrentTree());
            setPressureTreated(getPressureTreated());
        }
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
            }
        } catch(Exception e) {
            System.out.print("");
        }
    }

    /**
     * setter method for the current tree also adds it to the total tree list
     * @param input string val
     */
    public void setCurTree(String input) {
        currentTreeList.add(input);
        currentTree = input;
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
        test.setCurTreeFromList(14);
        test.setCurTreeFromList(-5);
        test.setCurTreeFromList(3);
        System.out.println(test.getCurrentTree());
        test.setPressureTreated(true);
        System.out.println(test.getCurrentTree());
        System.out.println(test.toString());
    }
}


