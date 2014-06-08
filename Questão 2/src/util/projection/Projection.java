package util.projection;

import util.math.FastMath;
import util.math.Matrix4f;

/**
 *
 * @author mlage
 */
public class Projection {
    
    private float fovY   = 0.0f;
    private float aspect = 0.0f;
    private float zNear  = 0.0f;
    private float zFar   = 0.0f;
    
    public Projection(float fovY, float aspect, float zNear, float zFar){
        this.fovY   = fovY;
        this.aspect = aspect;
        this.zNear  = zNear;
        this.zFar   = zFar;
    }

    public Matrix4f perspective() {
        float angle = fovY * FastMath.DEG_TO_RAD;
        float tangent = FastMath.sin(angle) / FastMath.cos(angle);

        float top    = zNear * tangent; 
        float right  = top * aspect;  

        Matrix4f tempMatrix = new Matrix4f();
        tempMatrix.m11 = zNear / right;   tempMatrix.m21 = 0.0f;         tempMatrix.m31 = 0.0f;                          tempMatrix.m41 = 0.0f;
        tempMatrix.m12 =      0.0f;       tempMatrix.m22 = zNear / top;  tempMatrix.m32 = 0.0f;                          tempMatrix.m42 = 0.0f;
        tempMatrix.m13 =      0.0f;       tempMatrix.m23 = 0.0f;         tempMatrix.m33 = -(zFar+zNear) / (zFar-zNear);  tempMatrix.m43 = -2.0f * zNear * zFar / (zFar-zNear);
        tempMatrix.m14 =      0.0f;       tempMatrix.m24 = 0.0f;         tempMatrix.m34 = -1.0f;                         tempMatrix.m44 = 0.0f;

        return tempMatrix;
    }
}
