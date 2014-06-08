package malha;

public class HalfEdge {
    public HalfEdge twin;
    public HalfEdge next;
    public Vertex origin;
    public Face f;
    
    public HalfEdge(){
    }
    
    public HalfEdge prev() {
        HalfEdge prev = this;
        if (prev.next == null) return this;
        while ( prev.next != this ) prev = prev.next;        
        return prev;
    }
}