import java.util.*;

public interface SearchType {
    String search(Node startNode, Node endNode);
    boolean reachEndNode = false;
}

class DfsSearch implements SearchType {

    public String search(Node startNode, Node endNode){
        String res = runDfs(startNode, endNode, "");
        StringBuilder result = new StringBuilder(res);
        return result.reverse().toString();
    }
    boolean dfsEndReached = reachEndNode;

    private String runDfs(Node startNode, Node endNode, String path) {
        startNode.setVisited(true);
        if (dfsEndReached)
            return path + startNode.getValue();
        else {

            if (startNode.equals(endNode)) {
                dfsEndReached = true;
                return path + endNode.getValue();
            }
            else {
                for (int i = 0; i < startNode.outEdges.size(); i++) {
                    if (!startNode.outEdges.get(i).getSonNode().isVisited())
                        if(dfsEndReached)
                            break;
                        path = runDfs(startNode.outEdges.get(i).getSonNode(), endNode, path);

                }
                if (dfsEndReached)
                    return path + startNode.getValue();
                return "";
            }

        }
    }
}

class BfsSearch implements SearchType {

    Queue<Node> queue = new LinkedList<>();
    boolean reachedEndNode = false;
    String path = "";
    public String search(Node startNode, Node endNode){
        String res = runBfs(startNode, endNode);
        return res;
    }

    private String runBfs(Node start, Node end) {
        if(start.getValue() == end.getValue())
            return "";
        queue.add(start);
        start.setVisited(true);
        while ((queue.isEmpty())||(reachedEndNode))
        {
            Node curr = queue.poll();
            if(curr.getValue() == end.getValue())
                return path + curr.getValue();
            for (int i = 0;i <curr.outEdges.size();i++){
                if(!curr.outEdges.get(i).getSonNode().isVisited()){
                    if(curr.outEdges.get(i).getSonNode().getValue() == end.getValue()) {
                        reachedEndNode = true;
                        break;
                    }
                    curr.outEdges.get(i).getSonNode().setVisited(true);
                    ArrayList<Edge> newInEdgesList = new ArrayList<>();
                    newInEdgesList.add(curr.outEdges.get(i));
                    curr.outEdges.get(i).getSonNode().setInEdges(newInEdgesList);
                    queue.add(curr.outEdges.get(i).getSonNode());
                }
            }
        }
        StringBuilder res = new StringBuilder();
        Node curr = end;
        while (curr.getInEdges().size() > 0) {
            res.append(curr.getValue());
            curr = curr.inEdges.get(0).getParentNode();
        }
        if(curr.getValue()== start.getValue()) {
            res.append(curr.getValue());
            res.reverse();
            return res.toString();
        }
        else
            return res.append("no path").toString();
    }
}

class UcsSearch implements SearchType {

    ArrayList<Node> visited = new ArrayList<>();
    PriorityQueue<UcsTreeNode> priorityQueue = new PriorityQueue<>(new Comparator<UcsTreeNode>() {
        @Override
        public int compare(UcsTreeNode o1, UcsTreeNode o2) {
            return Integer.compare(o1.getTotalWeight(),o2.getTotalWeight());
        }
    });

    public String search(Node startNode, Node endNode){
        String res = runUcs(startNode, endNode);
        StringBuilder result = new StringBuilder(res);
        return result.reverse().toString();
    }

    private String runUcs(Node start, Node end) {
        if (start.equals(end))
            return start.getValue();

        UcsTreeNode ucsTreeNode = new UcsTreeNode(start,0,null,convertSonNodesToUcsTreeNodes(start.outEdges));
        expandPath(ucsTreeNode);
        visited.add(start);

        while (!priorityQueue.isEmpty()){
            UcsTreeNode node = priorityQueue.poll();
            if(node.getNode().equals(end)){
                expandPath(priorityQueue.poll());
                visited.add(node.getNode());
            }

        }

        return null;
    }
    ArrayList<UcsTreeNode> convertSonNodesToUcsTreeNodes(ArrayList<Edge> edges){
        ArrayList<UcsTreeNode> res = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
//            UcsTreeNode n = new UcsTreeNode(edges.get(i).getSonNode(),n.getTotalWeight()+edges.get(i).getWeight(),n,convertSonNodesToUcsTreeNodes())
        }
        return null;
    }
    private void expandPath(UcsTreeNode treeNode){
        for (int i = 0; i <treeNode.getNode().outEdges.size() ; i++) {
            if(!visited.contains(treeNode.getNode().outEdges.get(i).getSonNode())){
                UcsTreeNode n = new UcsTreeNode(treeNode.getNode().outEdges.get(i).getSonNode(),treeNode.getNode().outEdges.get(i).getWeight());
                priorityQueue.add(n);
            }
        }
    }
}

