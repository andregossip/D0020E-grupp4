package p2p.state;

public class Node{

    private int id;
    private float map;

    public Node(int id, float map){
        this.id = id;
        this.map = map;
    }

    public String toString(){
        return Integer.toString(id);
    }
}