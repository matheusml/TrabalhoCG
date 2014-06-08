package util.shader;

/**
 *
 * @author marcoslage
 */
import java.io.BufferedReader;
import java.io.FileReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
    // OpenGL handle that will point to the executable shader program
    // that can later be used for rendering
    private int programId;

    public void init(String vertexShaderFilename, String fragmentShaderFilename) {
        // create the shader program. If OK, create vertex and fragment shaders
        programId = GL20.glCreateProgram();

        // load and compile the two shaders
        int vertShader = loadAndCompileShader(vertexShaderFilename,   GL20.GL_VERTEX_SHADER);
        int fragShader = loadAndCompileShader(fragmentShaderFilename, GL20.GL_FRAGMENT_SHADER);

        // attach the compiled shaders to the program
        GL20.glAttachShader(programId, vertShader);
        GL20.glAttachShader(programId, fragShader);

        // now link the program
        GL20.glLinkProgram(programId);

        // validate linking
        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            throw new RuntimeException("could not link shader. Reason: " + GL20.glGetProgramInfoLog(programId, 1000));
        }

        // perform general validation that the program is usable
        GL20.glValidateProgram(programId);

        if (GL20.glGetProgrami(programId, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            throw new RuntimeException("could not validate shader. Reason: " + GL20.glGetProgramInfoLog(programId, 1000));
        }
    }

    /*
     * With the exception of syntax, setting up vertex and fragment shaders
     * is the same.
     * @param the name and path to the vertex shader
     */
    private int loadAndCompileShader(String filename, int shaderType) {
        //vertShader will be non zero if succefully created
        int handle = GL20.glCreateShader(shaderType);

        if (handle == 0) {
            throw new RuntimeException("could not created shader of type " + shaderType + " for file " + filename + ". " + GL20.glGetProgramInfoLog(programId, 1000));
        }

        // load code from file into String
        String code = loadFile(filename);

        // upload code to OpenGL and associate code with shader
        GL20.glShaderSource(handle, code);

        // compile source code into binary
        GL20.glCompileShader(handle);

        // acquire compilation status
        int shaderStatus = GL20.glGetShaderi(handle, GL20.GL_COMPILE_STATUS);

        // check whether compilation was successful
        if (shaderStatus == GL11.GL_FALSE) {
            throw new IllegalStateException("compilation error for shader [" + filename + "]. Reason: " + GL20.glGetShaderInfoLog(handle, 1000));
        }

        return handle;
    }

    /**
     * Load a text file and return it as a String.
     */
    private String loadFile(String filename) {
        StringBuilder vertexCode = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                vertexCode.append(line);
                vertexCode.append('\n');
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("unable to load shader from file [" + filename + "]", e);
        }

        return vertexCode.toString();
    }

    public int getProgramId() {
        return programId;
    }
}
