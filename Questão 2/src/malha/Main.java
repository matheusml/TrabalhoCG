package malha;

import javax.swing.JOptionPane;
import org.lwjgl.LWJGLException;

public class Main {
    
    public static void main(String[] args) throws LWJGLException {
        Mesh mesh = new Mesh();
        if (mesh.loadMesh("malhas/kitten.off")){
            JOptionPane.showMessageDialog(null, "MODELO CARREGADO COM SUCESSO");
        } else {
            JOptionPane.showMessageDialog(null, "ERRO: MODELO NÃO FOI CARREGADO");
        }
    }
}
