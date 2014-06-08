
package malha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mesh {
    protected ArrayList<Vertex> vertices = new ArrayList<>();
    protected ArrayList<Face> faces = new ArrayList<>();
    protected ArrayList<Float> polygons = new ArrayList<>();
    
    protected int nverts ;
    protected int nfaces ;
    
    public Mesh() {
    }

    public boolean loadMesh(String file) {
        try {
            Scanner scan;

            FileReader read = new FileReader(file);
            BufferedReader buf = new BufferedReader(read);

            //Testa se e é arquivo OFF
            String magic = buf.readLine();
            if (!magic.equalsIgnoreCase("OFF")) 
                return false;

            scan = new Scanner(buf.readLine());

            //Leitura do número de vertices
            int nvert = Integer.parseInt(scan.next());

            //Leitura do número de faces
            int nfaces = Integer.parseInt(scan.next());
            
            int tmp = Integer.parseInt(scan.next());
            
            for (int i = 0; i < nvert; i++) {
                scan = new Scanner(buf.readLine());

                float x = Float.parseFloat(scan.next());
                float y = Float.parseFloat(scan.next());
                float z = Float.parseFloat(scan.next());
                
                Point4D point4d = new Point4D(x,y,z,1.0f);
                
                HalfEdge hedge = new HalfEdge();
                
                hedge.f = new Face(hedge.prev());
                hedge.twin = hedge.prev();
                hedge.next = hedge.twin;
                
                Vertex vertex = new Vertex(point4d, hedge);
                hedge.origin = vertex;
                
                vertices.add(vertex);
            }

            String line;
            while ( (line = buf.readLine()) != null ) {
                scan = new Scanner(line);
                
                int x = Integer.parseInt(scan.next());
                int y = Integer.parseInt(scan.next());
                int z = Integer.parseInt(scan.next());
                int w = Integer.parseInt(scan.next());
                
                Point4D point = new Point4D(x, y, z, w);
                
                HalfEdge hedge = new HalfEdge();
                
                Vertex vertex = new Vertex(point, hedge);
                
                hedge.origin = vertex;
                hedge.f = vertex.hedge.f;
                hedge.twin = vertex.hedge.prev();
                hedge.next = vertex.hedge.next;
                
                Face f = new Face(hedge);
                
                faces.add(f);
            }
            buf.close();
            
            return true;
            
        } catch (IOException ex) {
            Logger.getLogger(Mesh.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
    }
}
