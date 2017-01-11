package assign_4;

/**
 * Created by Trevor Vanderee on 2016-11-30.
 * A representation of a vertex
 */
class Vertex {
    boolean wasVisited,inCycle;
    int name;
    int indegree, absIndegree;
    int pos;
    public Vertex(int n ,int p){
        name = n;
        pos = p;
        wasVisited =false;
        indegree = Integer.MAX_VALUE;
        absIndegree= Integer.MAX_VALUE;
        inCycle = false;
    }//Vertex
}
