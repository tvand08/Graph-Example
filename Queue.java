package assign_4;

/**
 * Created by Trevor Vanderee on 2016-11-30.
 * Used For the Breadth First Search
 */
public class Queue {
    private Node list;
    private Node p;
    public Queue( ){
        list = new Node(null,null);
    }//Queue

    public void nq(Vertex in){
        p= list;
        while(p.next!=null){
            p=p.next;
        }
        p.next = new Node(in, null);
    }//nq

    public Vertex dq( ){
        Vertex out;
        p=list;
        out=p.next.item;
        p.next=p.next.next;
        return out;
    }//dq

    public boolean isEmpty(){
        boolean out;
        out = p.next==null;
        return out;
    }//isEmpty

}
