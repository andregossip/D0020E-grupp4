package p2p.state;

public class Node{

    private int id;

    public Node(int id){
        this.id = id;
    }

    public String toString(){
        return Integer.toString(id);
    }
}