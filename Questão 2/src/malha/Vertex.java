package malha;

public class Vertex {
    Point4D p;
    HalfEdge hedge;
    
    public Vertex(Point4D p, HalfEdge hedge){
        this.p = p;
        this.hedge = hedge;
    }
}
