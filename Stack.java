package assign_4;

/**
 * Created by Trevor Vanderee on 2016-12-01.
 * Used to discover cycles
 */
public class Stack {
    Vertex out;
    Node nOut;
    Node p,list, q ;
    public Stack( ){
        list = new Node(null,null);
    }//stack

    public void push(Vertex in){
        list.next = new Node(in,list.next);
    }//push

    public Vertex pop( ){
        out = list.next.item;
        list.next = list.next.next;
        return out;
    }//pop

    public Vertex top( ){
        out = list.next.item;
        return out;
    }//top

    public boolean isEmpty(){
        return list.next==null;
    }//isEmpty

    //This function returns a list of the items left in the stack
    public Node getList(Vertex lim){
        p = list;
        nOut= new Node(null,null);
        q=nOut;

        while(p.next!=null) {
            p = p.next;
            q.next = new Node(p.item, null);
            q = q.next;
            if (p.item.name == lim.name)
                break;
        }
        return nOut;
    }


}
