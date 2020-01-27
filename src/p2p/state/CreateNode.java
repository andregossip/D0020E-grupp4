package p2p.state;

public class CreateNode{
    private int id;

    public CreateNode(){
        id = 0;
    }

    public Node newNode(){
        return new Node(id++);
    }
}