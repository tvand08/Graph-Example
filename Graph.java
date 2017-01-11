package assign_4;

/**
 * Created by Trevor Vanderee on 2016-11-29.
 * Student: Trevor Vanderee
 * 5877022
 * Assignment 4
 * Modified topological sort
 */
import BasicIO.*;

public class Graph {
    public ASCIIDataFile in;
    private Vertex[] verts;
    private int numVerts;
    private Node nOut;
    private Queue que;
    private Stack stack;
    private int con, temp;
    private int[][] A;
    private Vertex v,u;
    private int curDegree;
    public Graph( ) {
        in = new ASCIIDataFile();
        numVerts = in.readInt();
        //The array of vertices
        verts = new Vertex[numVerts];
        A = new int[numVerts][numVerts];
        que = new Queue();
        /*
        Must add nOut.next=null
            when checking for circuits
        */
        nOut = new Node(null,null);
        stack = new Stack();
        //Loading Vertices into Array
        for (int i = 0; i < numVerts; i++) {
            temp = in.readInt();
            verts[i] = new Vertex(temp, i);
        }
        //Building adjaceny table
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                A[i][j] = in.readInt();
            }
        }
        //Adding first  vertice to queue
        v = verts[0];
        v.wasVisited=true;
        con = 0;
        que.nq(v);
        Node circuit;

        //breadth first traversal
        while(!que.isEmpty()){
            v = que.dq();
            v.wasVisited=true;
            //set to null
            circuit=null;
            if(v.absIndegree==Integer.MAX_VALUE){
                if(v.indegree>con+1)
                    v.indegree = ++con;
                nOut.next=null;
                //change all vertice inCycle to false
                resetInCycle();
                circuit = getCircuit(v,v);

            }
            if(circuit!=null){
                //set Absolute indigrees equal to v indigree
                setDegrees(circuit);
            }
            for(int i=0;i<numVerts; i++){
                if(A[v.pos][i]==1){
                    //add unvisited vertices to queue
                    if(!verts[i].wasVisited)
                        que.nq(verts[i]);
                }
            }

        }
        for(int i =0; i<numVerts; i++){
            if(verts[i].absIndegree==Integer.MAX_VALUE){
                verts[i].absIndegree=verts[i].indegree;
            }
        }
        printFinal(verts);


        in.close();
        //in doesnt fully close so system.exit is required
        System.exit(0);
    }//Graph

    //Calculates any Circuits for given vertex in
    public Node getCircuit(Vertex comp,  Vertex in ){
        Vertex cur,next;
        cur = comp;
        //Add the current Vertex to The stack
        stack.push(cur);
        //nOut.next=null;
        cur.inCycle = true;
        //Loop through possible out edges for current vertex
        for(int i =0; i<numVerts; i++){

            //check if edge exists For current vertices
            if(A[cur.pos][i]==1) {
                next = verts[i];
                //Check if you have returned to the initial vertice
                if (next.name==in.name){
                    //add together common cycles
                    nOut = addList(nOut,stack.getList(next));
                    getCircuit(next,in);
                }else if (next.inCycle){

                }else{
                    getCircuit(next,in);
                    stack.pop();
                }
            }
        }
        return nOut;
    }//getCircuit

    //Changes Values of InCycle to false for all vertices
    public void resetInCycle( ){
        for(int i =0; i <numVerts; i++){
            verts[i].inCycle = false;
        }
    }//resetInCycle

    //Takes two lists Combines them
    public Node addList(Node l1, Node l2 ){
        Node p ,q;
        boolean add;
        q = l2;
        //Cycle through new list
        while(q.next!=null){
            q=q.next;
            p=l1;
            add = true;
            //Cycle through real list
            while(p.next!=null){
                p=p.next;
                //if the item exists in both lists do not add
                if(q.item.name==p.item.name){
                    add = false;
                    break;
                }
            }
            if(add){
                p.next = new Node(q.item,null);
            }
        }
        return l1;
    }//addList

    /*general prints all nodes of the list seperated by a comma
    *Used for testing purposes
    */
    public void printList(Node list){
        String z = "";
        Node w = list;
        while(w.next!=null){
            w=w.next;
            z+= (w.item.name + ", ");
        }
        System.out.println(z);
    }

    //Sets the absolute indigrees to the lowest among the list
    public void setDegrees(Node set){
        Node p;
        p=set;
        int low = Integer.MAX_VALUE;
        //find lowest indigree among vertices
        while(p.next!=null) {
            p = p.next;
            if (p.item.indegree < low)
                low = p.item.indegree;
        }
        p=set;
        //set to lowest indigree
        while(p.next!=null){
            p=p.next;
            p.item.absIndegree = low;
        }

    }//setDegrees

    //Prints out the final topological sort
    public void printFinal(Vertex[] in){
        Vertex temp;
        int count= 0;
        int degree =1;
        boolean flip;
        String out = "";
        while(count<numVerts){
            out += "{";
            flip=false;
            for(int i=0; i < numVerts; i++){
                if(verts[i].absIndegree==degree){
                    count++;
                    if(flip)
                            out+=", ";
                    out+=verts[i].name;
                    flip=true;
                }
            }
            out+="}";
            if(count!=numVerts)
                out+=", ";
            degree++;
        }
        System.out.println(out);

    }//printFinal

    public static void main(String[] args){Graph g = new Graph();}
}
