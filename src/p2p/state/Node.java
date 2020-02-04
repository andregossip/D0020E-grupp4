package p2p.state;

public class Node{

    private int id;
    private float map;
    /**
     * 
     * @param id
     * @param map
     */
    public Node(int id, float map){
        this.id = id;
        this.map = map;
    }
    /**
     * @return Node id String
     */
    public String toString(){
        return Integer.toString(id);
    }
}