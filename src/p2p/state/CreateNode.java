package p2p.state;

public class CreateNode{
	/**
	 * This class creates new Nodes
	 */
    private int id;
    private int map;

    /**
     * Creates the first Node
     */
    public CreateNode(){
        id = 0;
        map = 0;
    }
    /**
     * 
     * @return new node with id++
     */
    public Node newNode(){
        return new Node(id++, 0);
    }
}