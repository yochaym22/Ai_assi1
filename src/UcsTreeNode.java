import java.util.ArrayList;

public class UcsTreeNode
{
    private Node node;
    private int totalWeight;
    private UcsTreeNode parent;
    private ArrayList<UcsTreeNode> sons;

    public UcsTreeNode(Node node, int totalWeight, UcsTreeNode parent, ArrayList<UcsTreeNode> sons) {
        this.node = node;
        this.totalWeight = totalWeight;
        this.parent = parent;
        this.sons = sons;
    }
    UcsTreeNode(Node node,int totalWeight){
        this.node = node;
        this.totalWeight = totalWeight;
        this.parent = null;
        this.sons = null;
    }


    public UcsTreeNode getParent() {
        return parent;
    }

    public void setParent(UcsTreeNode parent) {
        this.parent = parent;
    }

    public ArrayList<UcsTreeNode> getSons() {
        return sons;
    }

    public void setSons(ArrayList<UcsTreeNode> sons) {
        this.sons = sons;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }
}

